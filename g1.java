/* 
JRE System Library [JavaSE-22] 
Вариант B
Задания: 1-7
*/

package ch1;

import java.util.Arrays;
import java.util.Scanner;

public class g1 {
	
	public static int n;
	public static int mas[];
	
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		
		System.out.println("Программа по заданиям главы 1 (Вариант B)");
		System.out.print("Задайте количество последующих далее чисел для корректной работы программы: ");
		n = cin.nextInt();
		if (n <= 0) {
			System.out.print("Работа программы заверешна");
			cin.close();
			return;
		}
		mas = new int[n];
		System.out.println("Введите ряд целых чисел:");
		
		for (int i = 0; i < n; i++) {
			mas[i] = cin.nextInt();
		}
		chetnost();
		minmax();
		del3or9();
		del5and7();
		razn123();
		simple();
		sort();
		cin.close();
	}
	
	
	static void chetnost() {
		System.out.println("\n- - - - * - - - -\n");
		int i = 0, j = 0;
		
		int mas0[] = new int[n];
		Arrays.fill(mas0, 1);
		int mas1[] = new int[n];
		for(int x : mas) {
			if(x%2 == 0) {
				mas0[i] = x;
				i++;
			}
			else {
				mas1[j] = x;
				j++;
			}
		}
		
		System.out.println("Чётные числа:");
		for(int x : mas0) {
			if(x == 1) {
				break;
			}
			System.out.print(x + " ");
		}
		System.out.println("\n\nНечётные числа:");
		for(int x : mas1) {
			if(x == 0) {
				break;
			}
			System.out.print(x + " ");
		}
		System.out.print("\n");
	}
	
	static void minmax() {
		System.out.println("\n- - - - * - - - -\n");
		int mn, mx;
		mn = mx = mas[0];
		for(int x : mas) {
			if (x > mx) {
				mx = x;
			}
			if (x < mn) {
				mn = x;
			}
		}
		System.out.println("Максимальное число в массиве - " + mx + ", минимальное - " + mn);
	}
	
	static void del3or9() {
		System.out.println("\n- - - - * - - - -\n");
		int mas3[] = new int[n];
		Arrays.fill(mas3, 1);
		int i = 0;
		
		for(int x : mas) {
			if (x%3 == 0) {
				mas3[i] = x;
				i++;
			}
		}
		
		System.out.println("Числа, делящиеся на 3 или на 9:");
		for(int x : mas3) {
			if (x == 1) {
				break;
			}
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	static void del5and7() {
		System.out.println("\n- - - - * - - - -\n");
		int mas57[] = new int[n];
		Arrays.fill(mas57, 1);
		int i = 0;
		
		for(int x : mas) {
			if ((x%5 == 0) && (x%7 == 0)) {
				mas57[i] = x;
				i++;
			}
		}
		
		System.out.println("Числа, делящиеся на 5 и на 7:");
		for(int x : mas57) {
			if (x == 1) {
				break;
			}
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	static void razn123() {
		System.out.println("\n- - - - * - - - -\n");
		int mas123[] = new int[n];
		Arrays.fill(mas123, 1);
		int i = 0;
		
		for(int x : mas) {
			if ((x > 101) && (x < 989)) {
				if ((x%10 != (x/10)%10) && (x%10 != x/100)) {
					mas123[i] = x;
					i++;
				}
			}
		}
		System.out.println("Трехзначные числа, в десятичной записи которых нет одинаковых цифр:");
		for(int x : mas123) {
			if (x == 1) {
				break;
			}
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	static void simple() {
		System.out.println("\n- - - - * - - - -\n");
		int mas_simple[] = new int[n];
		Arrays.fill(mas_simple, -1);
		int i = 0, tmp = 0;
		boolean smp;
		
		for (int x : mas) {
			if (x < 1) {
				continue;
			}
			tmp = x/2;
			smp = true;
			for (int n = 2; n < tmp; n++) {
				if ((x%n == 0) && (x > n)) {
					smp = false;
					break;
				}
			}
			if(smp) {
				mas_simple[i] = x;
				i++;
			}
		}
		
		System.out.println("Простые числа в массиве:");
		for(int x : mas_simple) {
			if (x == -1) {
				break;
			}
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	static void sort() {
		System.out.println("\n- - - - * - - - -\n");
		int mas_sort[] = new int[n];
		int tmp;
		
		for(int i = 0; i < n; i++) {
			mas_sort[i] = mas[i];
		}
		for(int j = 0; j < n - 1; j++) {
			for(int i = 0; i < n - 1; i++) {
				if (mas_sort[i] > mas_sort[i + 1]) {
					tmp = mas_sort[i];
					mas_sort[i] = mas_sort[i + 1];
					mas_sort[i + 1] = tmp;
				}
			}
		}
		
		System.out.println("Отсортированный массив:");
		for(int x : mas_sort) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	//...
}
