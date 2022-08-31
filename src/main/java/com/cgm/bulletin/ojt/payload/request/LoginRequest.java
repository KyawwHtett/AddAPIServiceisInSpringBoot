package com.cgm.bulletin.ojt.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * <h2> LoginRequest Class</h2>
 * <p>
 * Process for Displaying LoginRequest
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Data
public class LoginRequest {
	/**
	 * <h2> email</h2>
	 * <p>
	 * email
	 * </p>
	 */
	@NotBlank
	private String email;

	/**
	 * <h2> password</h2>
	 * <p>
	 * password
	 * </p>
	 */
	@NotBlank
	private String password;
}