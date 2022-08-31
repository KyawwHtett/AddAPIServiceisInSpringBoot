package com.cgm.bulletin.ojt.payload.response;

import lombok.Data;

/**
 * <h2>JwtAuthenticationResponse Class</h2>
 * <p>
 * Process for Displaying JwtAuthenticationResponse
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Data
public class JwtAuthenticationResponse {
	/**
	 * <h2>accessToken</h2>
	 * <p>
	 * accessToken
	 * </p>
	 */
	private String accessToken;
	/**
	 * <h2>tokenType</h2>
	 * <p>
	 * tokenType
	 * </p>
	 */
	private String tokenType = "Bearer";

	/**
	 * <h2>Constructor for JwtAuthenticationResponse</h2>
	 * <p>
	 * Constructor for JwtAuthenticationResponse
	 * </p>
	 * 
	 * @param accessToken
	 */
	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}