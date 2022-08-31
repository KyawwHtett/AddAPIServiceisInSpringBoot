package com.cgm.bulletin.ojt.payload.request;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.cgm.bulletin.ojt.persistence.entity.PasswordReset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>PasswordResetSendMailRequest Class</h2>
 * <p>
 * Process for Displaying PasswordResetSendMailRequest
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetSendMailRequest {

	/**
	 * <h2>user_email</h2>
	 * <p>
	 * user_email
	 * </p>
	 */
	@Email
	@NotBlank
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
	 * <h2>Constructor for PasswordResetSendMailRequest</h2>
	 * <p>
	 * Constructor for PasswordResetSendMailRequest
	 * </p>
	 * 
	 * @param passwordReset
	 */
	public PasswordResetSendMailRequest(PasswordReset passwordReset) {
		super();
		this.user_email = passwordReset.getUser_email();
		this.token = passwordReset.getToken();
		this.expired_at = passwordReset.getExpired_at();
		this.created_at = passwordReset.getCreated_at();
	}
}