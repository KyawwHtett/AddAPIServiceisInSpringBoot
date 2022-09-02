package com.cgm.bulletin.ojt.web.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.bl.service.UserService;
import com.cgm.bulletin.ojt.payload.request.LoginRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.JwtAuthenticationResponse;

/**
 * <h2>ApiLoginController Class</h2>
 * <p>
 * Process for Displaying ApiLoginController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@RestController
@RequestMapping(value = "/api/auth")
public class ApiLoginController {
	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	private UserService userService;

	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	private AuthenticationService authService;

	/**
	 * <h2>passwordEncoder</h2>
	 * <p>
	 * passwordEncoder
	 * </p>
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * <h2>authenticateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param loginRequest
	 * @return
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @ModelAttribute LoginRequest loginRequest) {
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
}