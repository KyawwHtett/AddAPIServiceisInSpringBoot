package com.cgm.bulletin.ojt.web.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * <h2>PasswordResetForm Class</h2>
 * <p>
 * Process for Displaying PasswordResetForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
public class PasswordResetForm {
	/**
	 * <h2>password</h2>
	 * <p>
	 * password
	 * </p>
	 */
	@NotEmpty
	@Size(min = 6, max = 12)
	private String password;

	/**
	 * <h2>getPassword</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <h2>setPassword</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param password
	 * @return void
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * <h2>getToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getToken() {
		return token;
	}

	/**
	 * <h2>setToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param token
	 * @return void
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * <h2>token</h2>
	 * <p>
	 * token
	 * </p>
	 */
	private String token;

	/**
	 * <h2>Constructor for PasswordResetForm</h2>
	 * <p>
	 * Constructor for PasswordResetForm
	 * </p>
	 */
	public PasswordResetForm() {
		super();
	}
}