package com.example.dto;

import com.example.entity.User;


public class UserRequest {
	
    private String username;
    private String password;
    private String email;
    
    

    public User getUserFromUserRequest(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        
        return user;
    }



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
    
    

}
