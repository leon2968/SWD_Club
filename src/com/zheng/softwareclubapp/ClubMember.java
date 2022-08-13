package com.zheng.softwareclubapp;

public class ClubMember {

	//name, city, state, language
	private String name, city, state, favlanguage;
	
	/*
	 * Constructor
	 */
	public ClubMember(String name, String city, String state, String favLanguage) {
		this.name = name;
		this.city = city;
		this.state = state;
		this.favlanguage = favLanguage;
	}

	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFavlanguage() {
		return favlanguage;
	}

	public void setFavlanguage(String favlanguage) {
		this.favlanguage = favlanguage;
	}


	//toString() method
	@Override
	public String toString() {
		return "ClubMember [name=" + name + ", city=" + city + ", state=" + state + ", favlanguage=" + favlanguage
				+ "]";
	}
	
}
