package com.cgm.bulletin.ojt.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cgm.bulletin.ojt.persistence.entity.User;

/**
 * <h2>LoginForm Class</h2>
 * <p>
 * Process for Displaying LoginForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
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
	 * <h2>getId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * <h2>setId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param id
	 * @return void
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <h2>getEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * <h2>setEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return void
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * <h2>Constructor for LoginForm</h2>
	 * <p>
	 * Constructor for LoginForm
	 * </p>
	 */
	public LoginForm() {
		super();
	}

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