/* 
JRE System Library [JavaSE-22] 
Вариант A
Задание: 8
*/

package ch5;

import java.util.Scanner;

public class g5 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		City city = new City();
		byte choise = 0;
		char ch = 0;
		String str = "";
		int id = 0;
		boolean ft = false;
		
		city.show_about();
		System.out.println();
		while(true) {
			System.out.print("1) Изменение информации\n2) Вывод информации\n *] ВЫХОД\n:");
			ch = 0;
			ch += in.nextShort();
			System.out.println();
			switch(ch) {
			case 1:
				while(true) {
					System.out.print("1) Изменение информации города\n2) Изменение информации части города\n3) Изменение частей города\n *] Назад\n:");
					ch = 0;
					ch += in.nextShort();
					if (ch == 1) {
						System.out.print("\nВведите новое значение для названия города, затем страны через пробел\n:");
						str = in.next();
						city.change_name(str);
						str = in.nextLine();
						city.change_strana(str);
					}
					else if (ch == 2) {
						System.out.print("\nВведите номер части города в списке вывода, затем через пробел введите новое название для неё\n:");
						id = in.nextInt();
						str = in.nextLine();
						city.change_name_part(str, id);
						System.out.print("Определите тип части города:\n1 - Улица\n2 - Проспект\n3 - Площадь\n:");
						choise = in.nextByte();
						city.change_type_part(choise, id);
					}
					else if (ch == 3) {
						while(true) {
							System.out.print("\n1) Создать часть города\n2) Присоединить часть города (односторонее действие)\n *] Назад\n:");
							ch = 0;
							ch += in.nextShort();
							switch(ch) {
							case 1:
								System.out.print("\nВведите название для новой части города\n:");
								str = in.next();
								System.out.print("\nОпределите тип новой части города:\n1 - Улица\n2 - Проспект\n3 - Площадь\n:");
								choise = in.nextByte();
								city.add_part(str, choise);
								continue;
							case 2:
								System.out.print("\nfalse) Отсоединить часть\ntrue) Присоединить часть\n:");
								ft = in.nextBoolean();
								System.out.print("Введите номер части города в списке вывода\n:");
								id = in.nextInt();
								System.out.print("Введите название части города, с которой происходит присоединение/отсоединение\n:");
								str = in.next();
								city.dis_connect(str, id, ft);
								continue;
							default:
								break;
							}
							break;
						}
					}
					else {
						break;
					}
				}
				break;
			case 2:
				city.show_about();
				city.show_parts();
				System.out.print("Для вывода более подробной информации введите номер часть города из списка, либо введите ноль '0' для возврата\n:");
				while(true) {
					id = in.nextInt();
					if (id <= 0) {
						System.out.println();
						break;
					}
					city.show_part(id);
				}
				break;
			default:
				in.close();
				return;
			}
		}
		
	}
}


class City {
	
	private String name, strana;
	private Part parts[];
	
	
	public void change_strana(String strana) {
		this.strana = strana;
	}
	public void change_name(String name) {
		this.name = name;
	}
	public void change_name_part(String name, int id) {
		id--;
		if (id >= parts.length || id <= 0) {
			System.out.println("Данной части города не существует\n");
			return;
		}
		parts[id].name = name;
	}
	public void change_type_part(byte type, int id) {
		id--;
		if (id >= parts.length || id <= 0) {
			System.out.println("Данной части города не существует\n");
			return;
		}
		if (type == 1) {
			parts[id].type = "Улица";
		}
		else if (type == 2) {
			parts[id].type = "Проспект";
		}
		else {
			parts[id].type = "Площадь";
		}
		
	}
	public void dis_connect(String name, int id, boolean d_c) {  //d - false, c - true
		id--;
		if (id >= parts.length || id <= 0) {
			System.out.println("Данной части города не существует\n");
			return;
		}
		if (d_c) {
			parts[id].connect(name);
		}
		else {
			parts[id].disconnect(name);
		}
	}
	public void add_part(String name, byte type) {
		Part tmp[] = new Part[parts.length];
		for (int i = 0; i < parts.length; i++) {
			tmp[i] = parts[i];
		}
		parts = new Part[tmp.length + 1];
		for (int i = 0; i < tmp.length; i++) {
			parts[i] = tmp[i];
		}
		parts[parts.length - 1] = new Part(name);
		change_type_part(type, parts.length - 1);
	}
	public void show_about() {
		System.out.println(strana + ", город " + name);
	}
	public void show_parts() {
		System.out.println("Улицы, проспекты и площади города:");
		if (parts.length == 0) {
			System.out.println("Информации нет\n");
			return;
		}
		System.out.println("(Тип, Название)");
		for (int i = 0; i < parts.length; i++) {
			System.out.println((i + 1) + ") " + parts[i].type + ", " + parts[i].name);
		}
		System.out.println();
	}
	public void show_part(int id) {
		id--;
		if (id >= parts.length || id <= 0) {
			System.out.println("Данной части города не существует\n");
			return;
		}
		System.out.println(parts[id].type + " " + parts[id].name + "соединен(а) с:");
		for (int i = 0; i < parts[id].connect_parts.length; i++) {
			if (i == parts[id].connect_parts.length - 1) {
				System.out.println(parts[id].connect_parts[i]);
				return;
			}
			System.out.print(parts[id].connect_parts[i] + ", ");
		}
	}
	
	
	City() {
		name = strana = "default";
		parts = new Part[0];
	}
	
	
	class Part {
		
		String name, type;
		String connect_parts[];
		
		
		void connect(String part) 
		{
			String tmp[] = new String[this.connect_parts.length];
			for (int i = 0; i < this.connect_parts.length; i++) {
				tmp[i] = this.connect_parts[i];
			}
			this.connect_parts = new String[tmp.length + 1];
			for (int i = 0; i < tmp.length; i++) {
				this.connect_parts[i] = tmp[i];
			}
			this.connect_parts[this.connect_parts.length - 1] = part;
		}
		void disconnect(String part) 
		{
			int cnt = 0;
			for (int i = 0; i < this.connect_parts.length; i++) {
				if (this.connect_parts[i] == part) {
					cnt++;
				}
			}
			
			String tmp[] = new String[this.connect_parts.length];
			for (int i = 0; i < this.connect_parts.length; i++) {
				tmp[i] = this.connect_parts[i];
			}
			this.connect_parts = new String[tmp.length - cnt];
			for (int i = 0, j = 0; i < tmp.length; i++) {
				if (tmp[i] != part) {
					this.connect_parts[j] = part;
					j++;
				}
			}
		}
		
		
		{
			name = "default";
			type = "default";
			connect_parts = new String[0];
		}
		Part() {};
		Part(String name) {
			this.name = name;
		}
		
	}
}
