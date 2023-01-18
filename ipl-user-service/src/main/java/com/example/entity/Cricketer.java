package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
public class Cricketer {
	
	@Id
	private int playerId;
	private String playerName;
	private int age;
	private String nationality;
	private String speciality;
	private String imageUrl;
	@ManyToOne
	private Team team;
	
	
	public Cricketer() {
		super();
	}


	public Cricketer(int playerId, String playerName, int age, String nationality, String speciality,String imageUrl, Team team) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.age = age;
		this.nationality = nationality;
		this.speciality = speciality;
		this.imageUrl= imageUrl;
		this.team = team;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public int getPlayerId() {
		return playerId;
	}


	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getSpeciality() {
		return speciality;
	}


	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}


	public Team getTeam() {
		return team;
	}


	public void setTeam(Team team) {
		this.team = team;
	}

}
