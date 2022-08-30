package com.cgm.bulletin.ojt.payload.request;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.cgm.bulletin.ojt.persistence.entity.PasswordReset;

import lombok.Data;

@Data
public class PasswordResetSendMailRequest {
	
	@Email
	@NotBlank
	private String user_email;

	private String password;

	private String token;

	private Timestamp created_at;

	private Timestamp expired_at;

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getExpired_at() {
		return expired_at;
	}

	public void setExpired_at(Timestamp expired_at) {
		this.expired_at = expired_at;
	}

	public PasswordResetSendMailRequest() {
		super();
	}

	public PasswordResetSendMailRequest(PasswordReset passwordReset) {
		super();
		this.user_email = passwordReset.getUser_email();
		this.token = passwordReset.getToken();
		this.expired_at = passwordReset.getExpired_at();
		this.created_at = passwordReset.getCreated_at();
	}
}