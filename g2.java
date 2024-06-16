/* 
JRE System Library [JavaSE-22] 
Вариант B
Задания: 1
*/
package ch2;

import java.util.*;

public class g2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Введите число для построения таблицы умножения: ");
		int n = in.nextInt();
		
		
		System.out.print("\n|");
		for (int k = 0; k <= n; k++) {
			System.out.print("-----|");
		}
		System.out.print("\n|     |");
		for (int i = 1; i <= n; i++) {
			System.out.printf("%5d|", i);
		}
		System.out.print("\n|");
		for (int k = 0; k <= n; k++) {
			System.out.print("-----|");
		}
		
		
		for (int i = 1; i <= n; i++) {
			System.out.printf("\n|%5d|", i);
			for (int j = 1; j <= n; j++) {
				System.out.printf("%5d|", (i*j));
			}
			System.out.print("\n|");
			for (int k = 0; k <= n; k++) {
				System.out.print("-----|");
			}
		}
	}
}
