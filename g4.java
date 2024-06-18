/* 
JRE System Library [JavaSE-22] 
Вариант A
Задание: 6
*/

package ch4;

import java.util.*;

public class g4 {

	public static void main(String[] args) {
		Star_system x = new Star_system();
		System.out.println(x.get_star().toString() + "\n");
		Scanner in = new Scanner(System.in);
		byte choise = 0;
		while(true) {
			System.out.print("1) О системе\n2) Планеты системы\n3) Добавить планету\n *] ВЫХОД\n:");
			choise = in.nextByte();
			System.out.println();
			switch(choise) {
				case 1:
					x.show_star();
					x.show_cnt();
					break;
				case 2:
					x.show_planets();
					break;
				case 3:
					x.add_planet();
					break;
				default:
					return;
			}
		}
	}
	
}

				//Components

/* - STAR - */
class Star{
	
	private String name;
	
	
	public String toString() {
		return ("Звезда " + this.get_name());
	}
	public int hashCode() {
		return name.hashCode();
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof Star)) { return false; }
		Star star = (Star)obj;
		return this.name.equals(star.name);
	}
	
	
	public String get_name() {
		return name;
	}
	
	
	Star() {
		name = "";
		name += (char)('A' + (int)(Math.random()*25));
		name += (char)('A' + (int)(Math.random()*25));
		name += " ";
		name += (int)(Math.random()*8999) + 1000;
	}
	
}

/* - SATELLITE - */
class Satellite{

	private String name, owner;
	
	
	public String toString() {
		return ("Спутник " + owner);
	}
	public int hashCode() {
		return owner.hashCode();
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof Satellite)) { return false; }
		Satellite sat = (Satellite)obj;
		return this.owner.equals(sat.owner);
	}
	
	
	public void connect(String planet, int cnt) {
		owner = name + " " + planet + " ";
		owner += cnt;
	}
	public void show() {
		System.out.println(owner);
	}
	
	
	{
		name = "S/";
		owner = "";
	}
	Satellite() {
		name += (int)(Math.random()*1100) + 1900;
	}
	
}

/* - PLANET - */
class Planet{
	
	private String name;
	private Satellite sats[];
	int cnt_sats;
	
	
	public String toString() {
		return ("Планета " + name);
	}
	public int hashCode() {
		return name.hashCode();
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof Planet)) { return false; }
		Planet planet = (Planet)obj;
		return this.name.equals(planet.name);
	}
	
	
	public void connect(String star, int cnt) {
		name = star + " " + (char)('b' + cnt);
		reconnect_sats();
	}
	private void reconnect_sats() {
		for(int i = 0; i < cnt_sats; i++) {
			sats[i].connect(name, i);
		}
	}
	public void show() {
		System.out.println("  Планета " + name);
	}
	public void show_sats() {
		System.out.println("Спутники:");
		if (cnt_sats == 0) {
			System.out.println("Спутников нет\n");
			return;
		}
		for(int i = 0; i < cnt_sats; i++) {
			sats[i].show();
		}
		System.out.println("\n");
	}
	
	
	{
		name = "";
		cnt_sats = 0;
	}
	Planet() {
		name += (int)(Math.random()*8999 + 1000) + "-" + (int)(Math.random()*8999 + 1000);
		cnt_sats = (int)(Math.random()*4);
		sats = new Satellite[cnt_sats];
		for(int i = 0; i < cnt_sats; i++) {
			sats[i] = new Satellite();
			sats[i].connect(name, i);
		}
	}
	
}

				//Rezult class

/* - STAR_SYSTEM - */
class Star_system{
	
	private Star star;
	private Planet planets[];
	private int cnt_planets;
	
	
	public String toString() {
		return ("Звезда системы: " + star.get_name() + "\nКоличество планет в системе: " + cnt_planets + "\n");
	}
	public int hashCode() {
		return star.hashCode() + cnt_planets;
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof Star_system)) { return false; }
		Star_system star_system = (Star_system)obj;
		return this.star.equals(star_system.star);
	}
	
	
	public void show_star() {
		System.out.println("Звезда системы: " + star.get_name());
	}
	public void show_cnt() {
		System.out.println("Количество планет в системе: " + cnt_planets + "\n");
	}
	public void show_planets() {
		System.out.println("	Планеты системы " + star.get_name() + ":");
		for (int i = 0; i < cnt_planets; i++) {
			planets[i].show();
			planets[i].show_sats();
		}
	}
	public void add_planet() {
		Planet tmp[] = new Planet[cnt_planets];
		for (int i = 0; i < cnt_planets; i++) {
			tmp[i] = planets[i];
		}
		planets = new Planet[cnt_planets + 1];
		for (int i = 0; i < cnt_planets; i++) {
			planets[i] = tmp[i];
		}
		cnt_planets++;
		planets[cnt_planets - 1] = new Planet();
		planets[cnt_planets - 1].connect(star.get_name(), cnt_planets - 1);
	}
	public Star get_star() {
		return star;
	}
	
	
	{
		cnt_planets = 0;
	}
	Star_system() {
		star = new Star();
		cnt_planets = (int)(Math.random()*8) + 1;
		planets = new Planet[cnt_planets];
		for (int i = 0; i < cnt_planets; i++) {
			planets[i] = new Planet();
			planets[i].connect(star.get_name(), i);
		}
	}
	
}















