package com.cgm.bulletin.ojt.bl.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.persistence.entity.User;
import com.cgm.bulletin.ojt.web.form.UserForm;

/**
 * <h2>UserService Class</h2>
 * <p>
 * Process for Displaying UserService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface UserService {
	/**
	 * <h2>doSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user
	 * @return void
	 */
	void doSaveUser(User user);

	/**
	 * <h2>doDeleteUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return void
	 */
	void doDeleteUser(int userId);

	/**
	 * <h2>findAll</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<User>
	 */
	List<User> findAll();

	/**
	 * <h2>doGetUserById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return UserForm
	 */
	UserForm doGetUserById(int userId);

	/**
	 * <h2>doUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return void
	 */
	void doUpdateUser(UserForm userForm);

	/**
	 * <h2>dbGetUserCount</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return long
	 */
	long dbGetUserCount();

	/**
	 * <h2>doFindUserByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return UserDto
	 */
	UserDto doFindUserByEmail(String email);

	/**
	 * <h2>doDownloadUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	void doDownloadUser(HttpServletResponse response) throws IOException;

	/**
	 * <h2>doIsEmailExist</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return boolean
	 */
	boolean doIsEmailExist(String email);
}