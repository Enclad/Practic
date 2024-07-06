package ch13;

import java.util.*;
import java.sql.*;

class Task {
	
	public static List<Film> findAllFilms(Connection connection) throws SQLException {
		String sql = "SELECT * FROM films";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result_set = statement.executeQuery();
		
		List<Film> films = new ArrayList<>();
		while (result_set.next()) {
			films.add(new Film(
				result_set.getInt("id"),
				result_set.getString("name"),
				result_set.getDate("release_date"),
				result_set.getString("country")
			));
		}
		
		return films;
	}
	
	public static List<Film> findFilmsOneTwoYear(Connection connection) throws SQLException {
		String sql = "SELECT * FROM films WHERE release_date >= date('now', '-1 year')";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result_set = statement.executeQuery();
		
		List<Film> films = new ArrayList<>();
		while (result_set.next()) {
			films.add(new Film(
				result_set.getInt("id"),
				result_set.getString("name"),
				result_set.getDate("release_date"),
				result_set.getString("country")
			));
		}
		
		return films;
	}
	
	public static List<Actor> findActorsInFilm(Connection connection, int film_id) throws SQLException {
		String sql = "SELECT a.* FROM actors a JOIN film_actors fa ON a.id = fa.actor_id WHERE fa.film_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, film_id);
		ResultSet result_set = statement.executeQuery();
		
		List<Actor> actors = new ArrayList<>();
		while (result_set.next()) {
			actors.add(new Actor(
				result_set.getInt("id"),
				result_set.getString("name"),
				result_set.getDate("birthdate")
			));
		}
		
		return actors;
	}
	
	public static List<Actor> findActorsInAtLeastNFilms(Connection connection, int n) throws SQLException {
		String sql = "SELECT a.* FROM actors a JOIN film_actors fa ON a.id = fa.actor_id GROUP BY a.id HAVING COUNT(*) >= ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, n);
		ResultSet result_set = statement.executeQuery();
		
		List<Actor> actors = new ArrayList<>();
		while (result_set.next()) {
			actors.add(new Actor(
				result_set.getInt("id"),
				result_set.getString("name"),
				result_set.getDate("birthdate")
			));
		}
		
		return actors;
	}
	
	public static List<Actor> findActorsWhoAreAlsoDirectors(Connection connection) throws SQLException {
		String sql = "SELECT a.* FROM actors a JOIN directors d ON a.name = d.name";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result_set;
		result_set = statement.executeQuery();
		
		List<Actor> actors = new ArrayList<>();
		while (result_set.next()) {
			actors.add(new Actor(
				result_set.getInt("id"),
				result_set.getString("name"),
				result_set.getDate("birthdate")
			));
		}
		
		return actors;
	}
	
}
