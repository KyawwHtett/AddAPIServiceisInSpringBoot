package com.cgm.bulletin.ojt.payload.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.persistence.entity.User;

import lombok.Data;

/**
 * <h2>UserResponse Class</h2>
 * <p>
 * Process for Displaying UserResponse
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Data
public class UserResponse {
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
	@NotBlank
	private String username;

	/**
	 * <h2>email</h2>
	 * <p>
	 * email
	 * </p>
	 */
	@NotBlank
	@Email
	private String email;

	/**
	 * <h2>password</h2>
	 * <p>
	 * password
	 * </p>
	 */
	@NotBlank
	private String password;

	/**
	 * <h2>gender</h2>
	 * <p>
	 * gender
	 * </p>
	 */
	@NotBlank
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
	 * <h2>listPost</h2>
	 * <p>
	 * listPost
	 * </p>
	 */
	private List<PostResponse> listPost = new ArrayList<PostResponse>();

	/**
	 * <h2>Constructor for UserResponse</h2>
	 * <p>
	 * Constructor for UserResponse
	 * </p>
	 */
	public UserResponse() {
		super();
	}

	/**
	 * <h2>Constructor for UserResponse</h2>
	 * <p>
	 * Constructor for UserResponse
	 * </p>
	 * 
	 * @param user
	 */
	public UserResponse(User user) {
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
	}

	/**
	 * <h2>Constructor for UserResponse</h2>
	 * <p>
	 * Constructor for UserResponse
	 * </p>
	 * 
	 * @param loginUser
	 */
	public UserResponse(UserDto loginUser) {
		super();
		this.id = loginUser.getId();
		this.username = loginUser.getUsername();
		this.email = loginUser.getEmail();
		this.gender = loginUser.getGender();
		this.type = loginUser.getType();
		this.password = loginUser.getPassword();
		this.created_at = loginUser.getCreated_at();
		this.updated_at = loginUser.getUpdated_at();
		this.deleted_at = loginUser.getDeleted_at();
	}
}