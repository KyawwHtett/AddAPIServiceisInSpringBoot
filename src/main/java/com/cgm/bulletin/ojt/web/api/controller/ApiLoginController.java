package com.cgm.bulletin.ojt.web.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.bl.service.UserService;
import com.cgm.bulletin.ojt.payload.request.LoginRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.JwtAuthenticationResponse;
import com.cgm.bulletin.ojt.persistence.entity.User;

@RestController
@RequestMapping(value = "/api/auth")
public class ApiLoginController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = { "/", "/login" })
	public List<User> init() {
		return this.userService.findAll();
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		if (!this.userService.doIsEmailExist(loginRequest.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, "Invalid Email Address"), HttpStatus.NOT_FOUND);
		}
		UserDto userDetail = this.userService.doFindUserByEmail(loginRequest.getEmail());

		boolean passwordMatch = passwordEncoder.matches(loginRequest.getPassword(), userDetail.getPassword());
		if (!passwordMatch) {
			return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, "Wrong Password!"), HttpStatus.NOT_ACCEPTABLE);
		}
		String jwt = this.authService.doApiLoadAuth(loginRequest.getEmail());
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
//    @PostMapping(value = "/login")
//    public String login(@RequestParam String email,@RequestParam String password ) {
//        LoginForm loginForm = new LoginForm();
//        loginForm.setEmail(email);
//        loginForm.setPassword(password);
//        
//        if (!this.userService.doIsEmailExist(loginForm.getEmail())) {
//			return "Email doesn't exist";
//		}
//		UserDto userDetail = this.userService.doFindUserByEmail(loginForm.getEmail());
//
//		boolean passwordMatch = passwordEncoder.matches(loginForm.getPassword(), userDetail.getPassword());
//		if (!passwordMatch) {
//			return "Invalid Login! Email and Password doesn't match!";
//		}
//        this.authService.doLoadAuth(loginForm.getEmail());
//		if (this.authService.doIsLoggedIn()) {
//            System.out.println("Login Success");
//			session.setAttribute("LOGIN_USER", this.authService.doGetLoggedInUser());
//		}
//        UserDto userDto = (UserDto) session.getAttribute("LOGIN_USER");
//        System.out.println(userDto.getEmail());
//        return "Login Success" + userDto.getEmail();
//    }
}