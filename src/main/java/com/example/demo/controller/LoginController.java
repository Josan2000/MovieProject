package com.example.demo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserDefinedException;
import com.example.demo.interfaces.LoginRepository;
import com.example.demo.model.Login;

@RestController
public class LoginController {
	
	@Autowired
	LoginRepository lR;
	
	@PostMapping("login")
	public ResponseEntity<Login> registerUser(@RequestBody String username, String password) {
		try {
			if(username.equals("admin")&&password.equals("admin")) {
				Login admin = new Login();
				admin.setRoles("admin");
				admin.setUsername("admin");
				admin.setPassword("admin");
				return new ResponseEntity<Login>(admin, HttpStatus.OK);
			}
			Login l=lR.getByUserName(username);
			if (l.getPassword().equals(password)) {
				
				return new ResponseEntity<Login>(l, HttpStatus.OK);
			}
			return new ResponseEntity<Login>(l, HttpStatus.NOT_FOUND);
			
		}catch(Exception e) {
			throw new UserDefinedException("UserDetails already exists");
		}
		
	}

}
