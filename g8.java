/* 
JRE System Library [JavaSE-22] 
Вариант A
Задание: 12
В каждом слове текста k-ю букву заменить заданным символом. 
Если k больше длины слова, корректировку не выполнять.
*/

package ch8;

import java.util.Scanner;

public class g8 {
	
	public static void main(String[] args) {
		final String TEXT = "ahah tgtg ahahtgtg";
		System.out.println("Изначальный текст:\n" + TEXT);
		String kostil, text = "";
		Scanner in = new Scanner(System.in);
		System.out.print("\nВведите заменяющий символ: ");
		kostil = in.next();
		char sym = kostil.charAt(0);
		System.out.print("\nВведите номер букв в словах для их замены: ");
		byte k = in.nextByte();
		in.close();
		in = new Scanner(TEXT);
		
		char mas[];
		while (in.hasNext()) {
			mas = in.next().toCharArray();
			if (k < mas.length) {
				mas[k] = sym;
			}
			text += String.valueOf(mas);
			text += " ";
		}
		System.out.println("\nИзменённый текст:\n" + text);
		in.close();
	}
	
}
