/* 
JRE System Library [JavaSE-22] 
Вариант A
Задание: 2
*/

package ch3;

import java.util.*;

public class g3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Customer custs[];
		System.out.print("Введите размер списка покупателей для генерации: ");
		int a = in.nextInt();
		custs = new Customer[a];
		for (int i = 0; i < a; i++) {
			custs[i] = new Customer();
		}
		int mas[][] = new int[a][2];
		for(int i = 0; i < a; i++) {
			mas[i][0] = i;
			mas[i][1] = (custs[i].get_n1()).charAt(0) == 'В' ? 1 : ((custs[i].get_n1()).charAt(0) == 'Н' ? 2 : 3);
		}
		System.out.println("Список покупателей в почти алфавитном порядке:");
		System.out.println("+-------+-------+-------+---------------------+---------------------+---------------------+--------------------+");
		System.out.println("|id cust|id card|id scor|    first    name    |    second   name    |    third    name    |       addres       |");
		System.out.println("+-------+-------+-------+---------------------+---------------------+---------------------+--------------------+");
		for(int i = 0; i < a; i++) {
			if(mas[i][1] == 1) {
				custs[mas[i][0]].show();
				System.out.println("+-------+-------+-------+---------------------+---------------------+---------------------+--------------------+");
			}
		}
		for(int i = 0; i < a; i++) {
			if(mas[i][1] == 2) {
				custs[mas[i][0]].show();
				System.out.println("+-------+-------+-------+---------------------+---------------------+---------------------+--------------------+");
			}
		}
		for(int i = 0; i < a; i++) {
			if(mas[i][1] == 3) {
				custs[mas[i][0]].show();
				System.out.println("+-------+-------+-------+---------------------+---------------------+---------------------+--------------------+");
			}
		}
		System.out.println("Так как всё происходит и распологается в неизвестной деревушке, ни у кого банков нет");
		in.close();
	}
}


class Customer{
	
	static int cnt = 0;
	int id_customer, id_card, id_score;
	String name1, name2, name3, addres;
	Scanner in = new Scanner(System.in);
	
	void set() {
		System.out.print("Введите id (текущий " + id_customer + "): ");
		id_customer = in.nextInt();
		System.out.print("Введите номер банковской карты (при отсутствии '0'): ");
		id_card = in.nextInt();
		System.out.print("Введите номер банковского счёта (при отсутствии '0'): ");
		id_score = in.nextInt();
		System.out.print("Введите Имя: ");
		name1 = in.next();
		System.out.print("Введите Фамилию: ");
		name2 = in.next();
		System.out.print("Введите Отчество (при отсутствии '-'): ");
		name3 = in.next();
		System.out.print("Введите Адрес: ");
		addres = in.nextLine();
	}
	
	int get_id() { return id_customer; }
	int get_idc() { return id_card; }
	int get_ids() { return id_score; }
	String get_n1() { return name1; }
	String get_n2() { return name2; }
	String get_n3() { return name3; }
	String get_adr() { return addres; }
	
	void show() {
		System.out.printf("|%7d|%7d|%7d|%21s|%21s|%21s|%20s|\n", id_customer, id_card, id_score, name1, name2, name3, addres);
	}
	
	String ToString() {
		long A = 8_800_555_35_35L;
		return String.valueOf(A);
	}
	
	{
		id_customer = id_card = id_score =  -1;
		name1 = name2 = name3 = addres = "Не инициализированно";
	}
	Customer() {
		id_customer = ++cnt;
		id_card = id_score =  0;
		switch((int)(Math.random()*2 + 0.1)) {
		
			case 0:
				name1 = "Вячеслав";
				break;
			case 1:
				name1 = "НеВячеслав";
				break;
			default:
				name1 = "ТритийДляРазнообразия";
				
		}
		switch((int)(Math.random()*3)) {
		
			case 0:
				name2 = "Первый";
				break;
			case 1:
				name2 = "Второй";
				break;
			case 2:
				name2 = "Третий";
				break;
			case 3:
				name2 = "Четвёртый";
				break;
				
		}
		
		switch((int)(Math.random()*4)) {
		
			case 0:
				name3 = "Евгеньевич";
				break;
			case 1:
				name3 = "Павлович";
				break;
			case 2:
				name3 = "Баторович";
				break;
			case 3:
				name3 = "Саныч";
				break;
			case 4:
				name3 = "Dtkbrbq";
				break;
			
		}
		addres = "Unknown";
	}
	Customer(int i) {
		id_score = i;
		id_customer = ++cnt;
	}
	Customer(String str) {
		name1 = str;
		id_customer = ++cnt;
	}
	Customer(String str1, String str2) {
		name1 = str1;
		name2 = str2;
		id_customer = ++cnt;
	}
	Customer(int idc, int ids, String str1, String str2, String str3, String adr) {
		id_customer = ++cnt;
		id_card = idc;
		id_card = ids;
		name1 = str1;
		name2 = str2;
		name3 = str3;
		addres = adr;
	}
	
}