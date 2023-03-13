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
public class TeamService {

	@Autowired
	TeamRepository teamRepo;

	@Autowired
	CricketerRepository cricketerRepo;

	public void addTeam(Team team) {

		teamRepo.save(team);
	}

	public List<Team> getTeams(){
		return teamRepo.findAll();
	}

	public Team getTeam( int teamId) {


		try {

			return teamRepo.findById(teamId).orElse(null);

		}catch(NoSuchElementException e){

			throw new ExceptionHandling("team id  does not exist !!!" + e.getMessage());
		}

	}

	public void editOwner(Team tObj, int teamId) {


		try {

			Team team=teamRepo.findById(teamId).orElse(null);
			team.setOwnerName(tObj.getOwnerName());
			team.setTeamName(tObj.getTeamName());
			team.setCity(tObj.getCity());
			team.setState(tObj.getState());
			team.setTeamLogo(tObj.getTeamLogo());
			teamRepo.save(team);

		}catch(NoSuchElementException e){

			throw new ExceptionHandling("team id  does not exist !!!" + e.getMessage());
		}

	}   

	public void deleteTeam(int teamId) {


		try {

			List<Cricketer> cricketers=cricketerRepo.findByTeamId(teamId);
			for(Cricketer cricketer: cricketers) {
				cricketer.setTeam(null);
				cricketerRepo.save(cricketer);
			}
			teamRepo.deleteById(teamId);


		}catch(NoSuchElementException e){

			throw new ExceptionHandling("team id  does not exist !!!" + e.getMessage());
		}

	}
}

