/* 
JRE System Library [JavaSE-22] 
Вариант A
Задание: 12
Создать массив целых чисел. 
Убрать все четные элементы из массива и заполнить в конце нулями до прежнего размера массива.
*/

package ch7;

public class g7 {
	
	public static void main(String[] args) {
		final int SIZE = 10;
		int mas[] = new int[SIZE];
		for(int i = 0; i < SIZE; i++) {
			mas[i] = i;
			System.out.print(mas[i] + " ");
		}
		System.out.println();
		int id_end = 0;
		for (int i = 0, j = 1; j < SIZE; i++, j += 2) {
			mas[i] = mas[j];
			id_end = i;
		}
		for (int i = id_end + 1; i < SIZE; i++) {
			mas[i] = 0;
		}
		for (int i = 0; i < SIZE; i++) {
			System.out.print(mas[i] + " ");
		}
	}
	
}
