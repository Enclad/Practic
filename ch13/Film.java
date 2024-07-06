package ch13;

import java.sql.Date;

class Film {
	
	private int id;
	private String name;
	private Date release_date;
	private String country;
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return name;
	}
	public Date getReleaseDate() {
		return release_date;
	}
	public String getCountry() {
		return country;
	}
	
	public Film(int id, String name, Date releaseDate, String country) {
		this.id = id;
		this.name = name;
		this.release_date = releaseDate;
		this.country = country;
	}
	
}
