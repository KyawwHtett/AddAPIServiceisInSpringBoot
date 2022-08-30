package com.cgm.bulletin.ojt.web.form;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.payload.request.PasswordResetSendMailRequest;
import com.cgm.bulletin.ojt.persistence.entity.PasswordReset;

/**
 * <h2>PasswordResetSentMailForm Class</h2>
 * <p>
 * Process for Displaying PasswordResetSentMailForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
public class PasswordResetSentMailForm {
	/**
	 * <h2>user_email</h2>
	 * <p>
	 * user_email
	 * </p>
	 */
	@Email
	@NotEmpty
	private String user_email;

	/**
	 * <h2>password</h2>
	 * <p>
	 * password
	 * </p>
	 */
	private String password;

	/**
	 * <h2>token</h2>
	 * <p>
	 * token
	 * </p>
	 */
	private String token;

	/**
	 * <h2>created_at</h2>
	 * <p>
	 * created_at
	 * </p>
	 */
	private Timestamp created_at;

	/**
	 * <h2>expired_at</h2>
	 * <p>
	 * expired_at
	 * </p>
	 */
	private Timestamp expired_at;

	/**
	 * <h2>getUser_email</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getUser_email() {
		return user_email;
	}

	/**
	 * <h2>setUser_email</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user_email
	 * @return void
	 */
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

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
	 * <h2>getCreated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Timestamp
	 */
	public Timestamp getCreated_at() {
		return created_at;
	}

	/**
	 * <h2>setCreated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param created_at
	 * @return void
	 */
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	/**
	 * <h2>getExpired_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Timestamp
	 */
	public Timestamp getExpired_at() {
		return expired_at;
	}

	/**
	 * <h2>setExpired_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param expired_at
	 * @return void
	 */
	public void setExpired_at(Timestamp expired_at) {
		this.expired_at = expired_at;
	}

	/**
	 * <h2>Constructor for PasswordResetSentMailForm</h2>
	 * <p>
	 * Constructor for PasswordResetSentMailForm
	 * </p>
	 */
	public PasswordResetSentMailForm() {
		super();
	}

	/**
	 * <h2>Constructor for PasswordResetSentMailForm</h2>
	 * <p>
	 * Constructor for PasswordResetSentMailForm
	 * </p>
	 * 
	 * @param passwordReset
	 */
	public PasswordResetSentMailForm(PasswordReset passwordReset) {
		super();
		this.user_email = passwordReset.getUser_email();
		this.token = passwordReset.getToken();
		this.expired_at = passwordReset.getExpired_at();
		this.created_at = passwordReset.getCreated_at();
	}

	public PasswordResetSentMailForm(PasswordResetSendMailRequest sentMailForm) {
		super();
		this.user_email = sentMailForm.getUser_email();
		this.token = sentMailForm.getToken();
		this.expired_at = sentMailForm.getExpired_at();
		this.created_at = sentMailForm.getCreated_at();
	}
}