package com.cgm.bulletin.ojt.persistence.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgm.bulletin.ojt.persistence.dao.UserDao;
import com.cgm.bulletin.ojt.persistence.entity.User;

/**
 * <h2>UserDaoImpl Class</h2>
 * <p>
 * Process for Displaying UserDaoImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	/**
	 * <h2>SELECT_USER_LIST_HQL</h2>
	 * <p>
	 * SELECT_USER_LIST_HQL
	 * </p>
	 */
	private static final String SELECT_USER_LIST_HQL = "SELECT u FROM User u WHERE u.deleted_at = null";

	/**
	 * <h2>sessionFactory</h2>
	 * <p>
	 * sessionFactory
	 * </p>
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * <h2>findAll</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<User> findAll() {
		StringBuffer query = new StringBuffer(SELECT_USER_LIST_HQL);
		Query queryUserList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
		List<User> userList = (List<User>) queryUserList.getResultList();
		return userList;
	}

	/**
	 * <h2>dbSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param user
	 */
	@Override
	public void dbSaveUser(User user) {
		this.sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public int dbApiSaveUser(User user) {
		return (int) this.sessionFactory.getCurrentSession().save(user);
	}

	/**
	 * <h2>dbDeleteUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 */
	@Override
	public void dbDeleteUser(User user) {
		this.sessionFactory.getCurrentSession().update(user);
	}

	/**
	 * <h2>dbGetUserById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public User dbGetUserById(int userId) {
//		User user = this.sessionFactory.getCurrentSession().load(User.class, userId);
		String userQuery = "SELECT u FROM User u WHERE u.id = :id";
		Query query = this.sessionFactory.getCurrentSession().createQuery(userQuery);
		query.setParameter("id", userId);
		User user = (User) query.uniqueResult();
		return user;
	}

	/**
	 * <h2>dbUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param user
	 */
	@Override
	public void dbUpdateUser(User user) {
		this.sessionFactory.getCurrentSession().update(user);
	}

	/**
	 * <h2>dbGetUserCount</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public long dbGetUserCount() {
		Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(u) FROM User u");
		return (long) query.uniqueResult();
	}

	/**
	 * <h2>dbGetUserByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public User dbGetUserByEmail(String email) {
		String userSQL = "SELECT u FROM User u WHERE u.email = :email AND deleted_at = null ";
		Query query = this.sessionFactory.getCurrentSession().createQuery(userSQL);
		query.setParameter("email", email);
		User user = (User) query.uniqueResult();
		return user;
	}
}