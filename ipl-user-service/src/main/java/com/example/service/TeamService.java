package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	TeamRepository teamRepo;
}
