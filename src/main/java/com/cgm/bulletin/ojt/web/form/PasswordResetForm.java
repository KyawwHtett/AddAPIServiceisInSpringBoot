package com.cgm.bulletin.ojt.web.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>PasswordResetForm Class</h2>
 * <p>
 * Process for Displaying PasswordResetForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	 * <h2>token</h2>
	 * <p>
	 * token
	 * </p>
	 */
	private String token;
}