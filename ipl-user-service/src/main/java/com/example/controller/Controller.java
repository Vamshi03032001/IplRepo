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
import com.example.service.CricketerService;
import com.example.service.TeamService;


@CrossOrigin("*")
@RestController
public class Controller {

	@Autowired
	RestTemplate restTemaplate;

	@Autowired
	TeamService teamService;

	@Autowired
	CricketerService cricService;

	@PostMapping("/addTeam")
	public void addTeam(@RequestBody Team team) {

		teamService.addTeam(team);
	}

	@GetMapping("/getTeams")
	public List<Team> getTeams(){
		return teamService.getTeams();
	}

	@GetMapping("/getTeam/{teamId}")
	public Team getTeam(@PathVariable int teamId) {
		return teamService.getTeam(teamId);
	}

	@PutMapping("/editTeam/{teamId}")
	public void editOwner(@RequestBody Team tObj,@PathVariable int teamId) {

		teamService.editOwner(tObj, teamId);

	}   

	@DeleteMapping("/deleteTeam/{teamId}")
	public void deleteTeam(@PathVariable int teamId) {

		teamService.deleteTeam(teamId);
	}

	@GetMapping("/getPlayers")
	public List<Cricketer> getPlayers(){
		return cricService.getPlayers();

	}


	@GetMapping("/getPlayer/{playerId}")
	public Cricketer getPlayer(@PathVariable int playerId) {
		return cricService.getPlayer(playerId);

	}

	@PutMapping("/editPlayer/{playerId}")
	public void editPlayer(@RequestBody Cricketer player,@PathVariable int playerId) {

		cricService.editPlayer(player, playerId);
	}

	@DeleteMapping("deletePlayer/{playerId}")
	public void deletePlayer(@PathVariable int playerId) {
		cricService.deletePlayer(playerId);
	}

	@PostMapping("/addPlayer")
	public void addPlayer(@RequestBody Cricketer player) {
		cricService.addPlayer(player);
	}

	@GetMapping("/getPlayerByTeam/{teamId}")
	public List<Cricketer> getPlayerByOwner(@PathVariable int teamId) {
		return cricService.getPlayerByOwner(teamId);
	}

	@GetMapping("/getPlayerByOwner/{ownerName}")
	public List<Cricketer> getPlayerByOwner(@PathVariable String ownerName) {

		return cricService.getPlayerByOwner(ownerName);
	}

	@PutMapping("/removePlayer/{playerId}")
	public void removePlayer(@PathVariable int playerId) {
		cricService.removePlayer(playerId);
	}

	@PutMapping("/addToTeam/{ownerName}/{playerId}")
	public void addToTeam(@PathVariable String ownerName,@PathVariable int playerId) {

		cricService.addToTeam(ownerName, playerId);
	}
}
