package com.cgm.bulletin.ojt.payload.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * <h2>ApiResponse Class</h2>
 * <p>
 * Process for Displaying ApiResponse
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Data
@JsonPropertyOrder({ "success", "message" })
public class ApiResponse {
	/**
	 * <h2>serialVersionUID</h2>
	 * <p>
	 * serialVersionUID
	 * </p>
	 */
	@JsonIgnore
	private static final long serialVersionUID = 7702134516418120340L;

	/**
	 * <h2>success</h2>
	 * <p>
	 * success
	 * </p>
	 */
	@JsonProperty("success")
	private Boolean success;

	/**
	 * <h2>message</h2>
	 * <p>
	 * message
	 * </p>
	 */
	@JsonProperty("message")
	private String message;

	/**
	 * <h2>status</h2>
	 * <p>
	 * status
	 * </p>
	 */
	@JsonIgnore
	private HttpStatus status;

	/**
	 * <h2>Constructor for ApiResponse</h2>
	 * <p>
	 * Constructor for ApiResponse
	 * </p>
	 */
	public ApiResponse() {

	}

	/**
	 * <h2>Constructor for ApiResponse</h2>
	 * <p>
	 * Constructor for ApiResponse
	 * </p>
	 * 
	 * @param success
	 * @param message
	 */
	public ApiResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	/**
	 * <h2>Constructor for ApiResponse</h2>
	 * <p>
	 * Constructor for ApiResponse
	 * </p>
	 * 
	 * @param success
	 * @param message
	 * @param httpStatus
	 */
	public ApiResponse(Boolean success, String message, HttpStatus httpStatus) {
		this.success = success;
		this.message = message;
		this.status = httpStatus;
	}
}