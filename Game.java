/* 
JRE System Library [JavaSE-22] 
Вариант B
Задание: 2
 Игра по сети «Го». *{Не "Го"}*
 Крестики­нолики на безразмерном (большом) поле. *{Не в безразмерном и в не шибко большом (до 33 ячеек на сторону)}*
 Для победы необходимо выстроить пять в один ряд.
 
ВАЖНО: Программа писалась для использования на разных устройствах
*/


/*  DIR
 * 7 8 1
 * 6 ● 2
 * 5 4 3
 */



package ch14_go;

import java.io.*;
import java.net.*;
import java.util.*;

public class Game {
	static String VERSION = "0.4";
	
	static final char BLACK = '○'; 	// Первый игрок
	static final char WHITE = '●'; 	// Второй игрок
	static final String SYMS = " " + BLACK + WHITE + "?";
	static final String ALPH = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
	
	static final int GAME_PORT = 60000;
	static byte PLAYER = 1;
	static byte ENEMY = 2;
	
	static final short SIZE = 20; //Можно менять
	static byte desk[][] = new byte[SIZE][SIZE]; // [y][x] - [v][>]
	
	static Scanner scanner = null;
	static DataInputStream dis = null;
	static DataOutputStream dos = null;
	
	
		//Показать текущее состояние игровой доски
	public static void showDesk() {
		
		System.out.println();
		System.out.print("\s\s|");
		for (short i = 0; i < SIZE; i++) {
			System.out.printf("%2s", ALPH.charAt(i));
		}
		System.out.println();
		System.out.print("- + ");
		for (short i = 0; i < SIZE; i++) {
			System.out.print("- ");
		}
		System.out.println();
		for (short y = 0; y < SIZE; y++) {
			System.out.printf("%2d| ", y + 1);
			for (short x = 0; x < SIZE; x++) {
				System.out.print(SYMS.charAt(desk[y][x]) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
		//Установка камня(фишки) игрока
	public static boolean taskSetPlayer() throws IOException {
		short x, y;
		System.out.println("	Ход " + (PLAYER == 1 ? BLACK : WHITE));
		System.out.print("Введите координаты свободной ячейки в формате \"А 0\"\n:");
		x = (short)ALPH.indexOf(scanner.next());
		y = scanner.nextShort();
		if (x == -1 || y < 1 || y > SIZE || desk[y][x] != 0) {
			dos.writeUTF("- 0");
			dos.flush();
			return false; //Камень не установлен - игрок сдался
		}
		setStone(PLAYER, y, x);
		dos.writeUTF(ALPH.charAt(x) + " " + y);
		dos.flush();
		return true; //Камень установлен
	}
		//Установка камня(фишки) соперника
	public static boolean taskSetEnemy() throws IOException {
		short x, y;
		System.out.println("	Ход " + (ENEMY == 1 ? BLACK : WHITE));
		String str = dis.readUTF();
		Scanner temp_in = new Scanner(str);
		if (temp_in.next().equals("-")) {
			temp_in.close();
			return false; //Камень не установлен - соперник сдался
		}
		else if (temp_in.next().equals("skip")) {
			System.out.println("Переход хода Первому игроку\n");
			temp_in.close();
			return true; //Чтобы первый игрок не ждал, а сразу делал свой ход
		}
		temp_in.close();
		temp_in = new Scanner(str);
		x = (short)ALPH.indexOf(temp_in.next());
		y = temp_in.nextShort();
		temp_in.close();
		setStone(ENEMY, y, x);
		return true; //Камень установлен
	}
	//Установка камня(фишки) на поле
 	private static void setStone(byte type_stone, short y, short x) {
		desk[y - 1][x] = type_stone;
		System.out.println("Камень установлен на " + ALPH.charAt(x) + y);
	}
	
	
	//Опрос клеток вокруг и начало проверки последовательности камней(фишек)
	private static boolean checkArea(byte type_stone, short y, short x) {
		byte cnt;
		short temp_x, temp_y;
		for (byte i = 1; i < 9; i++) {
			cnt = 1;
			temp_x = x;
			temp_y = y;
			while (checkDir(type_stone, i, temp_y, temp_x)) {
				cnt++;
				Xy xy = new Xy(temp_x, temp_y);
				changeXy(i, xy);
				temp_x = xy.x;
				temp_y = xy.y;
			}
			if (cnt >= 5) { //Проверка победы (кол-во камней подряд)
				return true;
			}
		}
		desk[y][x] = 3; //Отметка о прошедшей проверке для камня(фишки) для небольшой оптимизации(?)
		return false;
	}
	//Опрос клетки по направлению
	private static boolean checkDir(byte type_stone, byte dir, short y, short x) {
		switch(dir) {
		case 1:
			if (y > 0 && x + 1 < SIZE && desk[y - 1][x + 1] == type_stone) {
				return true;
			}
			break;
		case 2:
			if (x + 1 < SIZE && desk[y][x + 1] == type_stone) {
				return true;
			}
			break;
		case 3:
			if (y + 1 < SIZE && x + 1 < SIZE && desk[y + 1][x + 1] == type_stone) {
				return true;
			}
			break;
		case 4:
			if (y + 1 < SIZE && desk[y + 1][x] == type_stone) {
				return true;
			}
			break;
		case 5:
			if (y + 1 < SIZE && x > 0 && desk[y + 1][x - 1] == type_stone) {
				return true;
			}
			break;
		case 6:
			if (x > 0 && desk[y][x - 1] == type_stone) {
				return true;
			}
			break;
		case 7:
			if (y > 0 && x > 0 && desk[y - 1][x - 1] == type_stone) {
				return true;
			}
			break;
		case 8:
			if (y > 0 && desk[y - 1][x] == type_stone) {
				return true;
			}
			break;
		}
		return false;
	}
	//Шаг по направлениюв для опроса последовательности камней(фишек)
	private static void changeXy(byte dir, Xy xy) {
		switch(dir) {
		case 1:
			if (xy.y > 0 && xy.x + 1 < SIZE) {
				xy.y -= 1;
				xy.x += 1;
			}
			break;
		case 2:
			if (xy.x + 1 < SIZE) {
				xy.x += 1;
			}
			break;
		case 3:
			if (xy.y + 1 < SIZE && xy.x + 1 < SIZE) {
				xy.y += 1;
				xy.x += 1;
			}
			break;
		case 4:
			if (xy.y + 1 < SIZE) {
				xy.y += 1;
			}
			break;
		case 5:
			if (xy.y + 1 < SIZE && xy.x > 0) {
				xy.y += 1;
				xy.x -= 1;
			}
			break;
		case 6:
			if (xy.x > 0) {
				xy.x -= 1;
			}
			break;
		case 7:
			if (xy.y > 0 && xy.x > 0) {
				xy.y -= 1;
				xy.x -= 1;
			}
			break;
		case 8:
			if (xy.y > 0) {
				xy.y -= 1;
			}
			break;
		}
	}
		//Проверить победу игрока
	public static boolean checkWin(byte type_stone) {
		boolean win = false;
		for (short y = 0; y < SIZE; y++) {
			for (short x = 0; x < SIZE; x++) {
				if (desk[y][x] == type_stone) {
					win = checkArea(type_stone, y, x); //Здесь определяется, победил ли соответствующий участик игры
					break;
				}
			}
			if (win) {
				break;
			}
		}
		for (short y = 0; y < SIZE; y++) {
			for (short x = 0; x < SIZE; x++) {
				if (desk[y][x] == 3) {
					desk[y][x] = type_stone;
				}
			}
		}
		return win;
	}
	
	
		//Сброс игрового поля
	public static void nulled() {
		desk = new byte[SIZE][SIZE];
	}
		//Итерация цикла игры
	public static byte gameLoop() throws IOException {
		taskSetEnemy();
		showDesk();
		if (checkWin(ENEMY)) {
			return ENEMY;
		}
		taskSetPlayer();
		showDesk();
		if (checkWin(PLAYER)) {
			return PLAYER;
		}
		return 0;
	}
	
	
	public static void main(String[] args) throws IOException {
		try {
			String choise; //Костыль
			String address_str;
			byte winner = 0;
			
			scanner = new Scanner(System.in);
			ServerSocket server = null;
			Socket client = null;
			
			System.out.println("			Безразмерные крестики нолики (Го) v" + VERSION);
			System.out.println("Правила игры:"
					+ "\n Поле " + SIZE + "x" + SIZE
					+ "\n Игроки по очереди выставляют свои камни с целью составить ряд из 5 камней"
					+ "\n Игрок, первый составивший ряд, побеждает"
					+ "\n Ничьей быть не может, поэтому одному из игроков, возможно, придётся сдаться"
					+ "\n Запрещается ставить камни на другие камни или на не существующие поля игрового поля"
					+ "\n Попытка нарушить запрет считается сдачей соответствующего игрока и его поражением"
					+ "\n Любая неисправность, появившаяся по ходу игры, также считается нарушением и оставляет всю ответственность на игрока"
					+ "\nПриятной игры\n");
			System.out.println("1. Создать игру (Первый игрок)");
			System.out.println("2. Присоединиться к игре (Второй игрок)");
			System.out.print("Введите номер варианта для его выбора: ");
			choise = scanner.next();
			
			System.out.println();
			
			if (choise.charAt(0) == '1') {
				PLAYER = 1;
				ENEMY = 2;
				server = new ServerSocket(GAME_PORT, 1, InetAddress.getLocalHost());
				System.out.println("Ваш id: " + InetAddress.getLocalHost().getHostAddress());
				System.out.println("Сообщите его другому игроку и ожидайте его подключения");
				client = server.accept();
				System.err.println(" Второй игрок присоединился");
			}
			else if (choise.charAt(0) == '2') {
				PLAYER = 2;
				ENEMY = 1;
				System.out.println("Введите id первого игрока в том же виде, как он (id) записан у него:");
				address_str = scanner.next();
				client = new Socket(InetAddress.getByName(address_str), GAME_PORT);
			}
			else {
				System.err.println("Неккоректный ввод => выход");
				scanner.close();
				System.exit(0);
			}
			
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			
			System.out.println("\n				НАЧАЛО ИГРЫ \n");
			if (PLAYER == 2) {
				dos.writeUTF("* skip");
				dos.flush();
			}
			while (winner == 0) {
				winner = gameLoop();
			}
			
			System.out.println("\n				ИГРА ОКОНЧЕНА");
			System.out.println("Победа " + (winner == 1 ? BLACK : WHITE));
			System.out.println(" Вы " + (PLAYER == winner ? "победили" : "проиграли"));
			
			scanner.close();
			client.close();
			if (PLAYER == 1) {
				server.close();
			}
			System.exit(0);
		}
		catch (Exception e) {
			System.out.println("Exception : " + e);
		}
	}
	
}

															//InetAddress.getLocalHost().getHostAddress() - ip текущего ПК

class Xy {
	public short x, y;
	
	Xy() {
		x = y = 0;
	}
	Xy(short x, short y) {
		this.x = x;
		this.y = y;
	}
}
