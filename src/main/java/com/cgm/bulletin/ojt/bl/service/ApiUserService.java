package com.cgm.bulletin.ojt.bl.service;

import java.util.List;

import com.cgm.bulletin.ojt.payload.request.UserRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.UserResponse;

/**
 * <h2>ApiUserService Class</h2>
 * <p>
 * Process for Displaying ApiUserService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface ApiUserService {

	/**
	 * <h2>findAll</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<UserResponse>
	 */
	public List<UserResponse> findAll();

	/**
	 * <h2>doApiGetUserById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return UserResponse
	 */
	public UserResponse doApiGetUserById(int userId);

	/**
	 * <h2>doApiIsEmailExist</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return boolean
	 */
	boolean doApiIsEmailExist(String email);

	/**
	 * <h2>doApiSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userRequest
	 * @return
	 * @return UserResponse
	 */
	public UserResponse doApiSaveUser(UserRequest userRequest);

	/**
	 * <h2>doApiUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userRequest
	 * @return
	 * @return UserResponse
	 */
	public UserResponse doApiUpdateUser(UserRequest userRequest);

	/**
	 * <h2>doDeleteUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return ApiResponse
	 */
	public ApiResponse doDeleteUser(int userId);

}