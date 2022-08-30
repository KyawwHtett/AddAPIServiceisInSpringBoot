package com.cgm.bulletin.ojt.persistence.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.payload.request.UserRequest;
import com.cgm.bulletin.ojt.web.form.UserForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>User Class</h2>
 * <p>
 * Process for Displaying User
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	/**
	 * <h2>id</h2>
	 * <p>
	 * id
	 * </p>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * <h2>username</h2>
	 * <p>
	 * username
	 * </p>
	 */
	@Column(name = "username", nullable = false, length = 25)
	private String username;

	/**
	 * <h2>email</h2>
	 * <p>
	 * email
	 * </p>
	 */
	@Column(name = "email", nullable = false, unique = true, length = 45)
	private String email;

	/**
	 * <h2>password</h2>
	 * <p>
	 * password
	 * </p>
	 */
	@Column(name = "password", nullable = false, length = 64)
	private String password;

	/**
	 * <h2>gender</h2>
	 * <p>
	 * gender
	 * </p>
	 */
	@Column(name = "Gender", nullable = false, length = 10)
	private String gender;

	/**
	 * <h2>type</h2>
	 * <p>
	 * type
	 * </p>
	 */
	@Column(name = "type", nullable = false, length = 2)
	private String type;

	/**
	 * <h2>created_at</h2>
	 * <p>
	 * created_at
	 * </p>
	 */
	@Column(name = "created_at", nullable = false)
	private Date created_at;

	/**
	 * <h2>updated_at</h2>
	 * <p>
	 * updated_at
	 * </p>
	 */
	@Column(name = "updated_at", nullable = true)
	private Date updated_at;

	/**
	 * <h2>deleted_at</h2>
	 * <p>
	 * deleted_at
	 * </p>
	 */
	@Column(name = "deleted_at", nullable = true)
	private Date deleted_at;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> postList = new ArrayList<Post>();

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
	 * <h2>getUsername</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * <h2>setUsername</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param username
	 * @return void
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * <h2>getGender</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * <h2>setGender</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param gender
	 * @return void
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * <h2>getType</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * <h2>setType</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param type
	 * @return void
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * <h2>getCreated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getCreated_at() {
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
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	/**
	 * <h2>getUpdated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getUpdated_at() {
		return updated_at;
	}

	/**
	 * <h2>setUpdated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updated_at
	 * @return void
	 */
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * <h2>getDeleted_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getDeleted_at() {
		return deleted_at;
	}

	/**
	 * <h2>setDeleted_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param deleted_at
	 * @return void
	 */
	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	/**
	 * <h2>Constructor for User</h2>
	 * <p>
	 * Constructor for User
	 * </p>
	 * 
	 * @param userForm
	 */
	public User(UserForm userForm) {
		super();
		this.id = userForm.getId();
		this.username = userForm.getUsername();
		this.email = userForm.getEmail();
		this.gender = userForm.getGender();
		this.type = userForm.getType();
		this.password = userForm.getPassword();
		this.created_at = userForm.getCreated_at();
		this.updated_at = userForm.getUpdated_at();
		this.deleted_at = userForm.getDeleted_at();
		this.postList = userForm.getPostList();
	}
	
	public User(UserRequest userRequest) {
		super();
		this.id = userRequest.getId();
		this.username = userRequest.getUsername();
		this.email = userRequest.getEmail();
		this.gender = userRequest.getGender();
		this.type = userRequest.getType();
		this.password = userRequest.getPassword();
		this.created_at = userRequest.getCreated_at();
		this.updated_at = userRequest.getUpdated_at();
		this.deleted_at = userRequest.getDeleted_at();
	}

	public User(UserDto userDto) {
		super();
		this.id = userDto.getId();
		this.username = userDto.getUsername();
		this.email = userDto.getEmail();
		this.gender = userDto.getGender();
		this.type = userDto.getType();
		this.password = userDto.getPassword();
		this.created_at = userDto.getCreated_at();
		this.updated_at = userDto.getUpdated_at();
		this.deleted_at = userDto.getDeleted_at();
		this.postList = userDto.getPostList();
	}
}