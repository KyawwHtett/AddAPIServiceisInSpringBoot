package com.cgm.bulletin.ojt.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cgm.bulletin.ojt.persistence.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>LoginForm Class</h2>
 * <p>
 * Process for Displaying LoginForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
	/**
	 * <h2>id</h2>
	 * <p>
	 * id
	 * </p>
	 */
	private int id;

	/**
	 * <h2>email</h2>
	 * <p>
	 * email
	 * </p>
	 */
	@NotEmpty
	@Email
	private String email;

	/**
	 * <h2>password</h2>
	 * <p>
	 * password
	 * </p>
	 */
	@NotEmpty
	@Size(min = 3, max = 8)
	private String password;

	/**
	 * <h2>Constructor for LoginForm</h2>
	 * <p>
	 * Constructor for LoginForm
	 * </p>
	 * 
	 * @param user
	 */
	public LoginForm(User user) {
		super();
		this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}
}