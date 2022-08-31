package com.cgm.bulletin.ojt.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.persistence.entity.Post;
import com.cgm.bulletin.ojt.persistence.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>UserForm Class</h2>
 * <p>
 * Process for Displaying UserForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
	/**
	 * <h2>id</h2>
	 * <p>
	 * id
	 * </p>
	 */
	private int id;

	/**
	 * <h2>username</h2>
	 * <p>
	 * username
	 * </p>
	 */
	@NotEmpty(message = "{Size.Person.FullName}")
	private String username;

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
	private String password;

	/**
	 * <h2>gender</h2>
	 * <p>
	 * gender
	 * </p>
	 */
	@NotEmpty
	private String gender;
	/**
	 * <h2>type</h2>
	 * <p>
	 * type
	 * </p>
	 */
	private String type;
	/**
	 * <h2>created_at</h2>
	 * <p>
	 * created_at
	 * </p>
	 */
	private Date created_at;
	/**
	 * <h2>updated_at</h2>
	 * <p>
	 * updated_at
	 * </p>
	 */
	private Date updated_at;
	/**
	 * <h2>deleted_at</h2>
	 * <p>
	 * deleted_at
	 * </p>
	 */
	private Date deleted_at;

	/**
	 * <h2>postList</h2>
	 * <p>
	 * postList
	 * </p>
	 */
	private List<Post> postList = new ArrayList<Post>();

	/**
	 * <h2>Constructor for UserForm</h2>
	 * <p>
	 * Constructor for UserForm
	 * </p>
	 * 
	 * @param user
	 */
	public UserForm(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.gender = user.getGender();
		this.type = user.getType();
		this.password = user.getPassword();
		this.created_at = user.getCreated_at();
		this.updated_at = user.getUpdated_at();
		this.deleted_at = user.getDeleted_at();
		this.postList = user.getPostList();
	}
}