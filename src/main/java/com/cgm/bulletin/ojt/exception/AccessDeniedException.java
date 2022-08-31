package com.cgm.bulletin.ojt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cgm.bulletin.ojt.payload.response.ApiResponse;

/**
 * <h2>AccessDeniedException Class</h2>
 * <p>
 * Process for Displaying AccessDeniedException
 * </p>
 * 
 * @author KyawHtet
 *
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends RuntimeException {
	/**
	 * <h2>serialVersionUID</h2>
	 * <p>
	 * serialVersionUID
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <h2>apiResponse</h2>
	 * <p>
	 * apiResponse
	 * </p>
	 */
	private ApiResponse apiResponse;

	/**
	 * <h2>message</h2>
	 * <p>
	 * message
	 * </p>
	 */
	private String message;

	/**
	 * <h2>Constructor for AccessDeniedException</h2>
	 * <p>
	 * Constructor for AccessDeniedException
	 * </p>
	 * 
	 * @param apiResponse
	 */
	public AccessDeniedException(ApiResponse apiResponse) {
		super();
		this.apiResponse = apiResponse;
	}

	/**
	 * <h2>Constructor for AccessDeniedException</h2>
	 * <p>
	 * Constructor for AccessDeniedException
	 * </p>
	 * 
	 * @param message
	 */
	public AccessDeniedException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * <h2>Constructor for AccessDeniedException</h2>
	 * <p>
	 * Constructor for AccessDeniedException
	 * </p>
	 * 
	 * @param message
	 * @param cause
	 */
	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * <h2>getApiResponse</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ApiResponse
	 */
	public ApiResponse getApiResponse() {
		return apiResponse;
	}

	/**
	 * <h2>setApiResponse</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param apiResponse
	 * @return void
	 */
	public void setApiResponse(ApiResponse apiResponse) {
		this.apiResponse = apiResponse;
	}

	/**
	 * <h2>getMessage</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * <h2>setMessage</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param message
	 * @return void
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}