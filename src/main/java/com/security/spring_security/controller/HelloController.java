package com.security.spring_security.controller;

import com.security.spring_security.model.User;
import com.security.spring_security.service.JwtService;
import com.security.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
public class HelloController {
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

    public HelloController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("login")
	public String login(@RequestBody User user){
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
		if(auth.isAuthenticated()){
			return jwtService.generateToken(user.getName());
		}
		return "LogIn Field";
	}
	@PostMapping("register")
	public String register(@RequestBody User user){
		userService.Create(user);
		return "Created";
	}
	@GetMapping("hello")
	public String greet(User usr) {
		return "Hello World ";
	}
	@GetMapping("users")
	public ResponseEntity<List<User>> getAll(){
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}
}
