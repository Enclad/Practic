/* 
JRE System Library [JavaSE-22] 
Вариант C
Задание: 16
 Создать новую директорию и файл.
 В файле содержится совокупность строк. 
 Найти номера строк, совпадающих с заданной строкой. 
 Имя файла и строка для поиска — аргументы командной строки. 
 Вывести строки файла и номера строк, совпадающих с заданной
 
 Примечание: параметры командной строки - file.txt ahah
*/

package ch10;

import java.io.*;
import java.util.Scanner;

public class g10 {
	
	final static String DIR_NAME = "C://g10";
	
	public static void main(String[] args) throws IOException {
		
		if (args.length < 2) {
			System.out.println("Ошибка при запуске программы: недостаточно аргументов запуска (требуется: 2)");
			return;
		}
		else if (args.length > 2) {
			for (int i = 2; i < args.length; i++) {
				args[1] += " " + args[i];
			}
		}
		
		new File(DIR_NAME).mkdir();
		final File DIR = new File(DIR_NAME);
		new File(DIR, args[0]).createNewFile();
		final File FILE = new File(DIR, args[0]);
		Scanner in = new Scanner(FILE);
		
		try(PrintStream out = new PrintStream(FILE)) {
			for (char a1 = 'a', a2 = 'a', a3 = 'a', a4 = 'a';; a4++) {
				if (a4 > 'z') {
					a4 = 'a';
					a3++;
				}
				if (a3 > 'z') {
					a3 = 'a';
					a2++;
				}
				if (a2 > 'z') {
					a2 = 'a';
					a1++;
				}
				if (a1 > 'z') break;
				out.print("" + (char)a1 + (char)a2 + (char)a3 + (char)a4 + "\n");
			}
		}
		catch(java.io.IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		String str;
		
		System.out.println("Совпадающие запросу строки:");
		for (int i = 0; in.hasNextLine(); i++) {
			str = in.nextLine();
			if (str.equals(args[1])) {
				System.out.println(i + ") " + str);
			}
		}
		
		in.close();
		FILE.delete();
		DIR.delete();
	}
	
}
