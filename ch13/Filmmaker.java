package ch13;

import java.sql.Date;

class Filmmaker {
	
	private int id;
	private String[] name;
	private Date birthdate;
	
	public int getId() {
		return id;
	}
	public String[] getName() {
		return name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	
	public Filmmaker(int id, String[] name, Date birthdate) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}
	
}
