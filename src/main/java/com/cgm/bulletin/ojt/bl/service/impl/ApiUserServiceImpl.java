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

/**
 * <h2>ApiUserServiceImpl Class</h2>
 * <p>
 * Process for Displaying ApiUserServiceImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service
public class ApiUserServiceImpl implements ApiUserService {
	/**
	 * <h2>userDao</h2>
	 * <p>
	 * userDao
	 * </p>
	 */
	@Autowired
	private UserDao userDao;

	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	private AuthenticationService authService;

	/**
	 * <h2>passwordEncoder</h2>
	 * <p>
	 * passwordEncoder
	 * </p>
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * <h2>findAll</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
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

	/**
	 * <h2>doApiGetUserById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 * @return
	 */
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

	/**
	 * <h2>doApiIsEmailExist</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public boolean doApiIsEmailExist(String email) {
		boolean result = false;
		User user = this.userDao.dbGetUserByEmail(email);
		result = (user == null) ? result : true;
		return result;
	}

	/**
	 * <h2>doApiSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userRequest
	 * @return
	 */
	@Override
	public UserResponse doApiSaveUser(UserRequest userRequest) {
		userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		userRequest.setCreated_at(new Date());
		User user = new User(userRequest);
		return new UserResponse(this.userDao.dbGetUserById(this.userDao.dbApiSaveUser(user)));
	}

	/**
	 * <h2>doApiUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userRequest
	 * @return
	 */
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

	/**
	 * <h2>doDeleteUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 * @return
	 */
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