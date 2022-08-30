package com.cgm.bulletin.ojt.payload.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.persistence.entity.User;

import lombok.Data;

@Data
public class UserResponse {
	private int id;

	@NotBlank
	private String username;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String gender;

	private String type;

	private Date created_at;

	private Date updated_at;

	private Date deleted_at;
	
	private List<PostResponse> listPost = new ArrayList<PostResponse>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	public UserResponse() {
		super();
	}

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