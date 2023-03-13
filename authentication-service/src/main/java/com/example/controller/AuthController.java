package com.example.controller;

import com.example.dto.LoginResponse;

import com.example.dto.LoginRequest;
import com.example.dto.UserRequest;
import com.example.entity.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.security.jwt.JwtUtils;
import com.example.security.services.UserDetailsImpl;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtUtils jwtTokenUtil;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepo;

    @PostMapping("/signin")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateJwtToken(authentication);
        
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        List<String> role = user.getAuthorities().stream()
                .map(item->item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(loginRequest.getUsername());
        loginResponse.setToken(token);
        loginResponse.setRole(role);

        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping ("/signup")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username Already Taken");

        } else if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Taken");

        } else {
            User user = new User();
            user = userService.save(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }
    
    @PostMapping("/deleteUser/username")
    public void gotIt(@PathVariable String username) {
    	
    	System.out.println(username);
    	User user = userRepository.findByUsername(username);
    	roleRepo.deleteById(user.getId());
    	userRepository.delete(user);
    	
    }
 
}
