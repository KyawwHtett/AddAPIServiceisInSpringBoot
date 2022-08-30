package com.cgm.bulletin.ojt.bl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.ApiUserService;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;
import com.cgm.bulletin.ojt.payload.request.UserRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.UserResponse;
import com.cgm.bulletin.ojt.persistence.dao.UserDao;
import com.cgm.bulletin.ojt.persistence.entity.User;

@Service
public class ApiUserServiceImpl implements ApiUserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<UserResponse> findAll() {
		List<User> users = this.userDao.findAll();
		List<UserResponse> userResList = new ArrayList<UserResponse>();
		for (User user : users) {
			UserResponse userResponse = new UserResponse(user);
			userResList.add(userResponse);
		}
		return userResList;
	}

	@Override
	public UserResponse doApiGetUserById(int userId) {
		UserDto userDto = this.authService.doGetLoggedInUser();
		if (userDto.getId() == userId || userDto.getType() == "1") {
			User user = this.userDao.dbGetUserById(userId);
			if (user == null) {
				return null;
			}
			UserResponse userResponse = new UserResponse(user);
			return userResponse;
		}
		return null;
	}

	@Override
	public boolean doApiIsEmailExist(String email) {
		boolean result = false;
		User user = this.userDao.dbGetUserByEmail(email);
		result = (user == null) ? result : true;
		return result;
	}

	@Override
	public UserResponse doApiSaveUser(UserRequest userRequest) {
		userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		userRequest.setCreated_at(new Date());
		User user = new User(userRequest);
		return new UserResponse(this.userDao.dbGetUserById(this.userDao.dbApiSaveUser(user)));
	}

	@Override
	public UserResponse doApiUpdateUser(UserRequest userRequest) {
		User user = this.userDao.dbGetUserById(userRequest.getId());
		if (user != null) {
			user.setUsername(userRequest.getUsername());
			user.setEmail(userRequest.getEmail());
			user.setGender(userRequest.getGender());
			user.setType(userRequest.getType());
			user.setUpdated_at(new Date());
			this.userDao.dbUpdateUser(user);
			return new UserResponse(user);
		}
		return null;
	}

	@Override
	public ApiResponse doDeleteUser(int userId) {
		User user = this.userDao.dbGetUserById(userId);
		if (user != null) {
			user.setDeleted_at(new Date());
			this.userDao.dbDeleteUser(user);
			return new ApiResponse(Boolean.TRUE, "You successfully deleted User");
		} else {
			return new ApiResponse(Boolean.FALSE, "User doesn't exist!");
		}
	}
}