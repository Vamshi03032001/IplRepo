package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Cricketer;
import com.example.entity.Team;
import com.example.repository.CricketerRepository;
import com.example.repository.TeamRepository;


@CrossOrigin("*")
@RestController
public class Controller {
	
	@Autowired
	RestTemplate restTemaplate;
	
	@Autowired
	TeamRepository teamRepo;
	
	@Autowired
	CricketerRepository cricketerRepo;
	
	@PostMapping("/addTeam")
	public void addTeam(@RequestBody Team team) {
		
		teamRepo.save(team);
	}
	
	@GetMapping("/getTeams")
	public List<Team> getTeams(){
		return teamRepo.findAll();
	}
	
	@GetMapping("/getTeam/{teamId}")
	public Team getTeam(@PathVariable int teamId) {
		return teamRepo.findById(teamId).orElse(null);
	}

	@PutMapping("/editTeam/{teamId}")
	public void editOwner(@RequestBody Team tObj,@PathVariable int teamId) {
		
		Team team=teamRepo.findById(teamId).orElse(null);
		team.setOwnerName(tObj.getOwnerName());
		team.setTeamName(tObj.getTeamName());
		team.setCity(tObj.getCity());
		team.setState(tObj.getState());
		team.setTeamLogo(tObj.getTeamLogo());		teamRepo.save(team);

	}   
	
	@DeleteMapping("/deleteTeam/{teamId}")
	public void deleteTeam(@PathVariable int teamId) {
		
		List<Cricketer> cricketers=cricketerRepo.findByTeamId(teamId);
		for(Cricketer cricketer: cricketers) {
			cricketer.setTeam(null);
			cricketerRepo.save(cricketer);
		}
		restTemaplate.postForObject("http://localhost:8081/api/auth/deleteUser",teamRepo.findById(teamId).get().getOwnerName(),String.class);
		teamRepo.deleteById(teamId);
	}
	
	@GetMapping("/getPlayers")
	public List<Cricketer> getPlayers(){
		return cricketerRepo.findAll();
	}
	
	
	@GetMapping("/getPlayer/{playerId}")
	public Cricketer getPlayer(@PathVariable int playerId) {
		return cricketerRepo.findById(playerId).orElse(null);
	}
	
	@PutMapping("/editPlayer/{playerId}")
	public void editPlayer(@RequestBody Cricketer player,@PathVariable int playerId) {
		
		Cricketer cObj=cricketerRepo.findById(playerId).orElse(new Cricketer());
		cObj.setPlayerName(player.getPlayerName());
		cObj.setAge(player.getAge());
		cObj.setNationality(player.getNationality());
		cObj.setSpeciality(player.getSpeciality());
		cObj.setImageUrl(player.getImageUrl());
		cricketerRepo.save(cObj);
	}
	
	@DeleteMapping("deletePlayer/{playerId}")
	public void deletePlayer(@PathVariable int playerId) {
		cricketerRepo.deleteById(playerId);
	}
	
	@PostMapping("/addPlayer")
	public void addPlayer(@RequestBody Cricketer player) {
		cricketerRepo.save(player);
	}

}
