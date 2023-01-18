package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.CricketerRepository;

@Service
public class CricketerService {
	
	@Autowired
	CricketerRepository cricRepo;

}
