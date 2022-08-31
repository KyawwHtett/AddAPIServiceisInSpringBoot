package com.cgm.bulletin.ojt.bl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cgm.bulletin.ojt.bl.dto.UserDto;

/**
 * <h2>AuthenticationService Class</h2>
 * <p>
 * Process for Displaying AuthenticationService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface AuthenticationService {
	/**
	 * <h2>doGetLoggedInUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return UserDto
	 */
	UserDto doGetLoggedInUser();

	/**
	 * <h2>doIsLoggedIn</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return boolean
	 */
	boolean doIsLoggedIn();

	/**
	 * <h2>doLoadAuth</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return void
	 */
	void doLoadAuth(String email);

	/**
	 * <h2>doApiLoadAuth</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return String
	 */
	public String doApiLoadAuth(String email);

	/**
	 * <h2>doLogout</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @return void
	 */
	void doLogout(HttpServletRequest request);

	/**
	 * <h2>doApiLogout</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 */
	public String doApiLogout(HttpServletRequest request, HttpServletResponse response);
}