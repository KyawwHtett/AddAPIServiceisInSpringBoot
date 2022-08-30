package com.cgm.bulletin.ojt.bl.service;

import java.util.List;

import com.cgm.bulletin.ojt.payload.request.UserRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.UserResponse;

public interface ApiUserService {
	
	public List<UserResponse> findAll();

	public UserResponse doApiGetUserById(int userId);
	
	boolean doApiIsEmailExist(String email);

	public UserResponse doApiSaveUser(UserRequest userRequest);

	public UserResponse doApiUpdateUser(UserRequest userRequest);

	public ApiResponse doDeleteUser(int userId);

}