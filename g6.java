/* 
JRE System Library [JavaSE-22] 
Вариант A
Задание: 10
*/

package ch6;

public class g6 {
	
	public static void main(String[] args) {
		System.out.println();
		 building b = new Home(); 
		 b.count_room();
		 b.count_cost();
		 b.remont();
		 b.increase_S(4);
		 b.count_cost();
		 b.arend(6);
		 System.out.println();
		 b = new Office(50000000, 250, 20);
		 b.count_room();
		 b.count_cost();
		 b.remont();
		 b.increase_S(50);
		 b.count_cost();
		 b.arend(60);
		 System.out.println();
		 b = new Market();
		 b.count_room();
		 b.count_cost();
		 b.remont();
		 b.increase_S(1000);
		 b.count_cost();
		 b.arend(15);
	}
}

interface building{
	void count_cost();
	void count_room();
	void increase_S(double s);
	void arend(int mounths);
	default void remont() {
    	System.out.println("Ремонт помещений здания проведён");
	}
}

abstract class Build {
	double cost, s;
	int rooms;
}

class Home extends Build implements building{
	
	Home() {
		cost = 3_000_000;
		s = 70;
		rooms = 6;
	}
	Home(double cost, double s, int rooms) {
		this.cost = cost;
		this.s = s;
		this.rooms = rooms;
	}
	public void count_cost() {
		System.out.println("Стоимость 1 м2 дома = " + cost/s);
	}
	public void count_room() {
		System.out.println("Количество комнат в доме = " + rooms);
	}
	public void increase_S(double s) {
		this.s += s;
		System.out.println("Площадь дома увеличена на " + s + " м2");
	}
	public void arend(int mounths) {
		System.out.println("Дом сдан в аренду сроком на " + mounths + " месяцев");
	}
	
}

class Office extends Build implements building{
	
	Office() {
		cost = 34_000_000;
		s = 200;
		rooms = 20;
	}
	Office(double cost, double s, int rooms) {
		this.cost = cost;
		this.s = s;
		this.rooms = rooms;
	}
	public void count_cost() {
		System.out.println("Стоимость 1 м2 офиса = " + cost/s);
	}
	public void count_room() {
		System.out.println("Количество помещений в офисе = " + rooms);
	}
	public void increase_S(double s) {
		this.s += s;
		System.out.println("Площадь офиса увеличена на " + s + " м2");
	}
	public void arend(int mounths) {
		System.out.println("Офис сдан в аренду сроком на " + mounths + " месяцев");
	}
	
}

class Market extends Build implements building{
	
	Market() {
		cost = 78_000_000;
		s = 2879;
		rooms = 200;
	}
	Market(double cost, double s, int rooms) {
		this.cost = cost;
		this.s = s;
		this.rooms = rooms;
	}
	public void count_cost() {
		System.out.println("Стоимость 1 м2 торгового центра = " + cost/s);
	}
	public void count_room() {
		System.out.println("Количество отделов в торговом центре = " + rooms);
	}
	public void increase_S(double s) {
		this.s += s;
		System.out.println("Площадь торгового центра увеличена на " + s + " м2");
	}
	public void arend(int mounths) {
		System.out.println("Торговый центр сдан в аренду сроком на " + mounths + " месяцев");
	}
	
}