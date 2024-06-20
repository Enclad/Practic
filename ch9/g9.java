/* 
JRE System Library [JavaSE-22] 
Вариант A
Задание: 
 В символьном файле находится информация об N числах с плавающей запятой с указанием локали каждого числа отдельно. 
 Прочитать информацию из файла.
 Проверить на корректность, то есть являются ли числа числами. 
 Преобразовать к числовымзначениями вычислить сумму и среднее значениепрочитанных чисел.
 Создать собственный класс исключения. 
 Предусмотреть обработку исключений, возникающих при нехватке памяти, 
отсутствии самого файла по заданному адресу, отсутствии или некорректности требуемой записи в файле, 
недопустимом значении числа и т.д.
*/

package ch9;

import java.util.*;
import java.io.*;

public class g9 {
	
	public static void main(String[] args) {
		
		String FILE = "C:\\Users\\Пользователь\\eclipse-workspace\\ch9\\src\\ch9\\nums.txt";
		
		try {
			Scanner in = new Scanner(new File(FILE));
			double sum = 0;
			int cnt = 0;
			
			while (in.hasNext()) {
				try {
					if (in.hasNextDouble()) {
						double num = in.nextDouble();
						if (Double.isNaN(num) || Double.isInfinite(num)) {
							throw new Exc("Некоректное число: " + num);
						}
						sum += num;
						cnt++;
					}
					else {
						String trash = in.next();
					}
				} 
				catch (Exc | NumberFormatException exc) {
					System.err.println("Ошибка чтения числа: " + exc.getMessage());
				}
			}
			if (cnt == 0) {
				System.out.println("Чисел, соответствующих требованиям к записи, в файле не найдено");
			} 
			else {
				double average = sum / cnt;
				System.out.println("Сумма = " + sum);
				System.out.println("Среднее значение = " + average);
			}
			in.close();
		} 
		catch (FileNotFoundException exc) {
			System.err.println("Файл не найден: " + FILE);
		} 
		catch (Exception exc) {
			System.err.println("ОШИБКА: " + exc.getMessage());
			exc.printStackTrace();
		}
		
	}
	
}


class Exc extends Exception {
	
    Exc(String str) {
        super(str);
    }
    
}
