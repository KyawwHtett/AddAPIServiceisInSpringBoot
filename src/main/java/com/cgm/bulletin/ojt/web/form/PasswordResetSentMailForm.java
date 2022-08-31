package com.cgm.bulletin.ojt.web.form;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.payload.request.PasswordResetSendMailRequest;
import com.cgm.bulletin.ojt.persistence.entity.PasswordReset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>PasswordResetSentMailForm Class</h2>
 * <p>
 * Process for Displaying PasswordResetSentMailForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

	/**
	 * <h2>Constructor for PasswordResetSentMailForm</h2>
	 * <p>
	 * Constructor for PasswordResetSentMailForm
	 * </p>
	 * 
	 * @param sentMailForm
	 */
	public PasswordResetSentMailForm(PasswordResetSendMailRequest sentMailForm) {
		super();
		this.user_email = sentMailForm.getUser_email();
		this.token = sentMailForm.getToken();
		this.expired_at = sentMailForm.getExpired_at();
		this.created_at = sentMailForm.getCreated_at();
	}
}