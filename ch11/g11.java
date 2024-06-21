/* 
JRE System Library [JavaSE-22] 
Вариант A
Задание: 1
 Ввести строки из файла, записать в список. 
 Вывести строки в файл в обратном порядке.
*/

package ch11;

import java.io.*;
import java.util.*;

public class g11 {
	
	final static String SAVE = "C:\\Users\\Пользователь\\eclipse-workspace\\ch11\\src\\ch11\\save.txt";
	final static String FILE = "C:\\Users\\Пользователь\\eclipse-workspace\\ch11\\src\\ch11\\text.txt";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File(SAVE));
		new File(FILE).delete();
		new File(FILE).createNewFile();
		PrintStream out = new PrintStream(FILE);
		while(in.hasNextLine()) {
			out.println(in.nextLine());
		}
		in.close();
		
		in = new Scanner(new File(FILE));
		ArrayList<String> list = new ArrayList<String>();
		int cnt = 0;
		
		for (int i = 0; in.hasNextLine(); i++) {
			list.add(i, in.nextLine());
			cnt++;
		}
		for (int i = cnt - 1; i >= 0; i--) {
			out.println(list.get(i));
		}
		
		out.close();
		in.close();
	}
	
}