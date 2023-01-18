package com.example.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
public class Team {
	
    @Id
	private int teamId;
    private String teamName;
    private String city;
    private String state;
    private String ownerName;
    private String teamLogo;
    @OneToMany(mappedBy="team")
    @JsonBackReference
    private List<Cricketer> players;
	
	
	public Team() {
		super();
	}


	public Team(int teamId, String teamName, String city, String state, String ownerName, String teamLogo, List<Cricketer> players) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.city = city;
		this.state = state;
		this.ownerName = ownerName;
		this.teamLogo=teamLogo;
		this.players = players;
	}


	public String getTeamLogo() {
		return teamLogo;
	}


	public void setTeamLogo(String teamLogo) {
		this.teamLogo = teamLogo;
	}


	public int getTeamId() {
		return teamId;
	}


	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
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


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public List<Cricketer> getPlayers() {
		return players;
	}


	public void setPlayers(List<Cricketer> players) {
		this.players = players;
	}
    
    
}
