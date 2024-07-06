/* 
JRE System Library [JavaSE-22] 
Вариант А
 В каждом из заданий необходимо выполнить следующие действия:
  Организацию соединения с базой данных вынести в отдельный класс, метод которого возвращает соединение;
  Создать БД. Привести таблицы к одной из нормальных форм;
  Создать класс для выполнения запросов на извлечение информации из БД с использованием компилированных запросов;
  Создать класс на модификацию информации.
  
Задание: 2
 Видеотека. 
 В БД хранится информация о домашней видеотеке: фильмы, актеры, режиссеры.
 
 Для фильмов необходимо хранить:
  Название; имена актеров; дату выхода; страну, в которой выпущен фильм.
 Для актеров и режиссеров необходимо хранить:
  ФИО; дату рождения.
  
 Найти все фильмы, вышедшие на экран в текущем и прошлом году.
 Вывести информацию об актерах, снимавшихся в заданном фильме.
 Вывести информацию об актерах, снимавшихся как минимум в N фильмах.
 Вывести информацию об актерах, которые были режиссерами хотя бы одного из фильмов.
 Удалить все фильмы, дата выхода которых была более заданного числа лет назад.
*/

package ch13;

import java.util.*;
import java.sql.*;

public class g13 {
	
	public static void main(String[] args) throws SQLException {
		String choise;
		Scanner in = new Scanner(System.in);
		Connection connection = Connecting.getConnection();
		List<Film> films = null;
		List<Actor> actors = null;
		try {
			System.out.println("База данных фильмов");
			System.out.println("Выберите:"
					+ "\n 1) Вывод фильмов"
					+ "\n 2) Вывод актёров"
					+ "\n 3) Удаление фильмов"
					+ "\n0] ВЫХОД"
			);
			choise = in.next();
			switch(choise) {
			case "1":
				System.out.println("Вывести все фильмы или фильмы, вышедшие за последние 2 года?");
				System.out.println("(1/2) :");
				choise = in.next();
				switch(choise) {
				case "1":
					films = Task.findAllFilms(connection);
					System.out.println("Список фильмов в базе:");
					for (Film film : films) {
						System.out.println(film);
					}
					break;
				default:
					films = Task.findFilmsOneTwoYear(connection);
					System.out.println("Фильмы, вышедшие в этом и прошлом году:");
					for (Film film : films) {
						System.out.println(film);
					}
					break;
				}
				System.out.println();
				break;
				
			case "2":
				System.out.println("Вывод актёров");
				System.out.println("Выберите:"
						+ "\n 1) Вывод всех актёров в фильме"
						+ "\n 2) Вывод актёров, снявшихся в N и более фильмах"
						+ "\n 3) Вывод актёров - режиссёров"
				);
				choise = in.next();
				switch(choise) {
				case "1":
					System.out.println("Введите номер фильма в списке фильмов: ");
					int filmId = in.nextInt();
					actors = Task.findActorsInFilm(connection, filmId);
					System.out.println("Актёры фильма под номером " + filmId + ":");
					for (Actor actor : actors) {
						System.out.println(actor);
					}
					break;
				case "2":
					System.out.println("Введите количество фильмов для установки минимума: ");
					int n = in.nextInt();
					actors = Task.findActorsInAtLeastNFilms(connection, n);
					System.out.println("Актёры, снявшиеся в как минимум " + n + " фильмах(е):");
					for (Actor actor : actors) {
						System.out.println(actor);
					}
					break;
				default:
					actors = Task.findActorsWhoAreAlsoDirectors(connection);
					System.out.println("Актёры - режиссёры:");
					for (Actor actor : actors) {
						System.out.println(actor);
					}
					break;
				}
				System.out.println();
				break;
				
			case "3":
				System.out.print("Введите минимальное количество лет, прошедших с момента выхода удаляемых фильмов: ");
				int years_ago = in.nextInt();
				Mod.deleteFilmsReleasedBeforeDate(connection, years_ago);
				System.out.println("Удалены все фильмы, вышедшие более " + years_ago + " лет назад");
				System.out.println();
				break;
				
			default:
				System.exit(0);
			}
		}
		catch(Exception e) {
			System.err.println("ОШИБКА: " + e);
		}
		finally {
			in.close();
			connection.close();
		}
	}
	
}