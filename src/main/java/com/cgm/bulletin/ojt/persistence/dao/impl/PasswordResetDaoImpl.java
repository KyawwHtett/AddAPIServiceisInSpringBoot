package com.cgm.bulletin.ojt.persistence.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgm.bulletin.ojt.persistence.dao.PasswordResetDao;
import com.cgm.bulletin.ojt.persistence.entity.PasswordReset;

/**
 * <h2>PasswordResetDaoImpl Class</h2>
 * <p>
 * Process for Displaying PasswordResetDaoImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Repository
@Transactional
public class PasswordResetDaoImpl implements PasswordResetDao {
	/**
	 * <h2>sessionFactory</h2>
	 * <p>
	 * sessionFactory
	 * </p>
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * <h2>dbGetPswResetDataByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param user_email
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public PasswordReset dbGetPswResetDataByEmail(String user_email) {
		String userHqlQuery = "SELECT pw FROM PasswordReset pw WHERE pw.user_email = :email";
		Query queryUserByEmail = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
		queryUserByEmail.setParameter("email", user_email);
		PasswordReset passwordReset = (PasswordReset) queryUserByEmail.uniqueResult();
		return passwordReset;
	}

	/**
	 * <h2>dbDeletePswResetTokenByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param user_email
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void dbDeletePswResetTokenByEmail(String user_email) {
		String userHqlQuery = "DELETE FROM PasswordReset pw WHERE pw.user_email = :email";
		Query queryUserByEmail = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
		queryUserByEmail.setParameter("email", user_email);
		queryUserByEmail.executeUpdate();
	}

	/**
	 * <h2>createPswResetToken</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param pswToken
	 */
	@Override
	public void createPswResetToken(PasswordReset pswToken) {
		this.sessionFactory.getCurrentSession().save(pswToken);
	}

	/**
	 * <h2>dbGetDataByToken</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param token
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public PasswordReset dbGetDataByToken(String token) {
		String pswHqlQuery = "SELECT pw FROM  PasswordReset pw WHERE pw.token = :token";
		Query queryDataByToken = this.sessionFactory.getCurrentSession().createQuery(pswHqlQuery);
		queryDataByToken.setParameter("token", token);
		PasswordReset passwordReset = (PasswordReset) queryDataByToken.uniqueResult();
		return passwordReset;
	}

	/**
	 * <h2>dbDeleteToken</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param token
	 */
	@Override
	public void dbDeleteToken(String token) {
		PasswordReset pswReset = this.sessionFactory.getCurrentSession().load(PasswordReset.class, token);
		this.sessionFactory.getCurrentSession().delete(pswReset);
	}

}