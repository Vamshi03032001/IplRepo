package com.example.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Cricketer;
import com.example.entity.Team;
import com.example.exceptions.ExceptionHandling;
import com.example.repository.CricketerRepository;
import com.example.repository.TeamRepository;

@Service
public class CricketerService {


	@Autowired
	CricketerRepository cricketerRepo;

	@Autowired
	TeamRepository teamRepo;

	public List<Cricketer> getPlayers(){
		return cricketerRepo.findAll();
	}

	public Cricketer getPlayer( int playerId) {

		try {

			return cricketerRepo.findById(playerId).orElse(null);
		}

		catch(NoSuchElementException e) {
			throw new ExceptionHandling("Player Id does not exist !!!" + e.getMessage());
		}
	}

	public void editPlayer(Cricketer player,int playerId) {


		try {
			Cricketer cObj=cricketerRepo.findById(playerId).orElse(new Cricketer());

			cObj.setPlayerName(player.getPlayerName());
			cObj.setAge(player.getAge());
			cObj.setNationality(player.getNationality());
			cObj.setSpeciality(player.getSpeciality());
			cObj.setImageUrl(player.getImageUrl());

			cricketerRepo.save(cObj);
		}catch(NoSuchElementException e){

			throw new ExceptionHandling("Player Id does not exist !!!" + e.getMessage());
		}
	}

	public void deletePlayer(int playerId) {
		try {
			cricketerRepo.deleteById(playerId);

		}catch(Exception e){

			throw new ExceptionHandling("Player Id is null . Give valid player id!!!" + e.getMessage());
		}

	}

	public void addPlayer(Cricketer player) {

		try {

			cricketerRepo.save(player);
		}catch(Exception e ){

			throw new ExceptionHandling("Error in player service layer !!!");
		}
	}

	public List<Cricketer> getPlayerByOwner(int teamId) {

		try {

			return cricketerRepo.findByTeamId(teamId);

		}catch(NoSuchElementException e){

			throw new ExceptionHandling("team Id  does not exist !!!" + e.getMessage());
		}
	}

	public List<Cricketer> getPlayerByOwner(String ownerName) {

		try {

			Team team = teamRepo.findByOwnerName(ownerName);
			return cricketerRepo.findByTeamId(team.getTeamId());

		}catch(NoSuchElementException e){

			throw new ExceptionHandling("owner name  does not exist !!!" + e.getMessage());
		}
	}

	public void removePlayer(int playerId) {

		try {

			Cricketer player=cricketerRepo.findById(playerId).orElse(null);
			player.setTeam(null);
			cricketerRepo.save(player);

		}catch(NoSuchElementException e){

			throw new ExceptionHandling("player id  does not exist !!!" + e.getMessage());
		}
	}

	public void addToTeam(String ownerName, int playerId) {

		try {

			Team team=teamRepo.findByOwnerName(ownerName);
			Cricketer player=cricketerRepo.findById(playerId).orElse(null);
			player.setTeam(team);
			cricketerRepo.save(player);

		}catch(NoSuchElementException e){

			throw new ExceptionHandling("player id  does not exist !!!" + e.getMessage());
		}
	}

}
