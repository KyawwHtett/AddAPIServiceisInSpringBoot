package com.cgm.bulletin.ojt.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cgm.bulletin.ojt.web.form.PasswordResetSentMailForm;

/**
 * <h2>PasswordReset Class</h2>
 * <p>
 * Process for Displaying PasswordReset
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Entity
@Table(name = "password_reset")
public class PasswordReset {
	/**
	 * <h2>user_email</h2>
	 * <p>
	 * user_email
	 * </p>
	 */
	@Column(name = "user_email")
	private String user_email;

	/**
	 * <h2>token</h2>
	 * <p>
	 * token
	 * </p>
	 */
	@Id
	@Column(name = "token", length = 64)
	private String token;

	/**
	 * <h2>created_at</h2>
	 * <p>
	 * created_at
	 * </p>
	 */
	@Column(name = "created_at")
	private Timestamp created_at;

	/**
	 * <h2>expired_at</h2>
	 * <p>
	 * expired_at
	 * </p>
	 */
	@Column(name = "expired_at")
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
	 * <h2>Constructor for PasswordReset</h2>
	 * <p>
	 * Constructor for PasswordReset
	 * </p>
	 */
	public PasswordReset() {
		super();
	}

	/**
	 * <h2>Constructor for PasswordReset</h2>
	 * <p>
	 * Constructor for PasswordReset
	 * </p>
	 * 
	 * @param passwordResetSentMailForm
	 */
	public PasswordReset(PasswordResetSentMailForm passwordResetSentMailForm) {
		super();
		this.user_email = passwordResetSentMailForm.getUser_email();
		this.token = passwordResetSentMailForm.getToken();
		this.created_at = passwordResetSentMailForm.getCreated_at();
		this.expired_at = passwordResetSentMailForm.getExpired_at();
	}
}