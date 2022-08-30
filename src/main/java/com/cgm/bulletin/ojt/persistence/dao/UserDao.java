package com.cgm.bulletin.ojt.persistence.dao;

import java.util.List;

import com.cgm.bulletin.ojt.persistence.entity.User;

/**
 * <h2>UserDao Class</h2>
 * <p>
 * Process for Displaying UserDao
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface UserDao {
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
	 * <h2>dbSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user
	 * @return void
	 */
	void dbSaveUser(User user);

	/**
	 * <h2>dbDeleteUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return void
	 */
	void dbDeleteUser(User user);

	/**
	 * <h2>dbGetUserById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return User
	 */
	User dbGetUserById(int userId);

	/**
	 * <h2>dbUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user
	 * @return void
	 */
	void dbUpdateUser(User user);

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
	 * <h2>dbGetUserByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return User
	 */
	User dbGetUserByEmail(String email);

	int dbApiSaveUser(User user);
}