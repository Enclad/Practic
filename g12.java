/* 
JRE System Library [JavaSE-22] 
Вариант A
Задание: 9
 На торги выставляется несколько лотов. 
 Участники аукциона делают заявки. 
 Заявку можно корректировать в сторону увеличения несколько раз за торги одного лота. 
 Аукцион определяет победителя и переходит к следующему лоту. 
 Участник, не заплативший за лот в заданный промежуток времени, отстраняется на несколько лотов от торгов.
*/

package ch12;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class g12 {
	
	static boolean auction = true;
	
	public static void main(String[] args) throws InterruptedException {
		ReentrantLock locker = new ReentrantLock();
		
		int cnt_member = (int)(Math.random()*5 + 5);
		for (int i = 0; i < cnt_member; i++) {
			locker.lock();
			new Member().start();
			locker.unlock();
		}
		
		Thread.sleep(1000);
		
		Member.Auction auction = new Member.Auction();
		auction.start();
		
		try { auction.join(); }
		catch (InterruptedException e) { 
			System.out.println("Аукцион был прерван");
		}
	}
	
}





class Member extends Thread {
	
	public byte wins, id;
	public byte type; //0 - Спокойный, 1 - Ренди_Случай, 2 - Безумный
	public volatile static byte cnt;
	public int start_money, moneys;
	public boolean useful, pay;
	
	public void increase_cost() { 	// Попытка увеличить текущую ставку
		if (Auction.current_winner == id) {
			return;
		}
		int offer[] = new int[2];
		switch(type) {
		case 0: 	// Ставит, если лот нужен
			if (useful) {
				if ((Auction.current_cost + Auction.min_increase) < (start_money * 0.2) && (Auction.current_cost + Auction.min_increase) < moneys * 0.3) {
					offer[0] = Auction.current_cost + Auction.min_increase;
					break;
				}
			}
			else return;
			break;
			
		case 1: 	// Ставит, пока лот нужен
			if (useful) {
				if ((Auction.current_cost + Auction.min_increase) < (start_money * 0.2)) {
					offer[0] = Auction.current_cost + (int)(Math.random()*(start_money * 0.2 - Auction.min_increase) + Auction.min_increase);
					break;
				}
				useful = false;
			}
			else return;
			break;
			
		case 2: 	// Ставит всегда. Рзмер зависит от нужности лота
			if (useful) {
				if ((Auction.current_cost + Auction.min_increase) < (start_money * 0.25) && (Auction.current_cost + Auction.min_increase + moneys * 0.1) < moneys * 0.4) {
					offer[0] = Auction.current_cost + (int)(Math.random()*(moneys * 0.1) + Auction.min_increase);
					break;
				}
			}
			else {
				if ((Auction.current_cost + Auction.min_increase) < (start_money * 0.08) && (Auction.current_cost + Auction.min_increase + moneys * 0.02) < moneys * 0.05) {
					offer[0] = Auction.current_cost + (int)(Math.random()*(moneys * 0.02) + Auction.min_increase);
					break;
				}
			}
			break;
		}
		offer[1] = (int)id;
		Auction.tasks.offer(offer);
	}
	public boolean buy() { 	// Оплата лота при победе
		if (moneys < Auction.current_cost) {
			System.out.println("Участник " + id + " не способен оплатить лот и отсраняется от аукциона\n");
			return false;
		}
		else if (type == 1) {
			if ((int)Math.random() == 0) {
				moneys -= Auction.current_cost;
				System.out.println("Участник " + id + " купил лот за " + Auction.current_cost + "\n");
				wins++;
				return true;
			}
			else {
				System.out.println("Участник " + id + " отказывается оплатить лот и отсраняется от аукциона\n");
				return false;
			}
		}
		moneys -= Auction.current_cost;
		System.out.println("Участник " + id + " купил лот за " + Auction.current_cost + "\n");
		wins++;
		return true;
	}
	public void new_lot() { 	// Определения полезности нового лота
		int rez_chanse = (int)(Math.random() * 100);
		if (rez_chanse < 41 || type == 1) {
			useful = true;
			System.out.println("Участник " + id + " интересуется в получении лота\n");
			return;
		}
		System.out.println("Участнику " + id + " не интересен текущий лот\n");
		useful = false;
	}
	public void show_rezult() { 	// Вывод результатов участника аукциона
		String type_str = "";
		switch(type) {
		case 0:
			type_str = "Спокойный";
			break;
		case 1:
			type_str = "Случайный";
			break;
		case 2:
			type_str = "Безумный";
			break;
		}
		System.out.println(type_str + " участник " + id + " выкупил " + wins + " лотов\n" + "Общие траты за аукцион: " + (start_money - moneys) + "\n");
	}
	
	static {
		cnt = 0;
	}
	{
		wins = id = 0;
		useful = pay = false;
		type = 1;
		moneys = 0;
	}
	Member() {
		super("Member " + (++cnt));
		id = cnt;
		start_money = moneys = (int)(Math.random()*450_000 + 50_000);
		type = (byte)((int)(Math.random()*9)/3);
		
	}
	
	
	public void run() {
		System.out.println("Участник " + id + " готов к началу аукциона\n");
		
		while (true) {
			if (Auction.active) {
				this.new_lot();
				pay = false;
			}
			while(Auction.active) { 	// Цикл торгов
				if (type == 1 && useful) {
					int chanse = (int)(Math.random()*99 + 1);
					useful = (chanse > 50) ? true : false;
				}
				this.increase_cost();
				try { Thread.sleep(500); }
				catch (InterruptedException e) { System.out.println("Участника схватил инфаркт\n"); }
			}
			if(!Auction.active) { 	// Оплата (перерыв)
				this.useful = false;
				if (Auction.current_winner == this.id && !pay) {
					if (!this.buy()) {
						this.show_rezult();
						return;
					}
					pay = true;
				}
			}
			if (!Auction.get_auction()) {
				this.show_rezult();
				return;
			}
		}
		
	}
	
	
																																				
																																				
																																				
	
	
	public static class Auction extends Thread {
		
		public static LinkedBlockingQueue<int[]> tasks = new LinkedBlockingQueue<int[]>();
		public volatile static int start_cost, current_cost, min_increase;
		public volatile static byte current_winner, cnt_lots;
		public static boolean active;
		
		void update_cost() { 	// Обновление текущей ставки и лидера исходя из входящих запросов, возможно завершение аукциона
			if (tasks.peek() != null) {
				int offer[] = tasks.poll();
				if (offer[0] - current_cost >= min_increase) {
					current_cost = offer[0];
					current_winner = (byte)offer[1];
					System.out.println("Участник " + offer[1] + " предложил " + offer[0] + "\n");
				}
			}
			else {
				active = false;
			}
		}
		void nulled() { 	// Установка переменных "по умолчанию" перед началом новых торгов
			current_winner = 0;
			current_cost = start_cost = (int)(Math.random()*19500 + 500);
			min_increase = (start_cost / 100) * 2;
			while(tasks.poll() != null);
		}
		void show_lot(int lot) { 	// Информация о текущем(новом) лоте
			System.out.println("+ - - - - - ЛОТ " + lot + " - - - - - +");
			System.out.println("  НАЧАЛЬНАЯ ЦЕНА: " + start_cost);
			System.out.println("  МИНИМАЛЬНАЯ СТАВКА: " + min_increase + "\n");
		}
		static boolean get_auction() {
			return g12.auction;
		}
		
		{
			start_cost = current_cost = min_increase = 0;
			current_winner = cnt_lots = 0;
			active = false;
		}
		Auction() {
			super("Auction");
			cnt_lots = (byte)(Math.random()*10 + 10);
		}
		
		
		public void run() {
			System.out.println("		Аукцион начинается\n");
			for (int lot = 1; lot < cnt_lots; lot++) {
				nulled();
				show_lot(lot);
				active = true;
				try { Thread.sleep(300); }
				catch (InterruptedException e) { System.out.println("Аукцион подвергся теракту\n"); }
				while(active) {
					update_cost();
					try { Thread.sleep(100); }
					catch (InterruptedException e) { System.out.println("Аукцион подвергся теракту\n"); }
				}
				try { Thread.sleep(3000); }
				catch (InterruptedException e) { System.out.println("Аукцион подвергся теракту\n"); }
			}
			System.out.println("		Аукцион завершён, лоты распроданы\n");
			g12.auction = false;
			try { Thread.sleep(1000); }
			catch (InterruptedException e) { System.out.println("Аукцион подвергся теракту\n"); }
		}
	}



}