package com.cgm.bulletin.ojt.bl.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgm.bulletin.ojt.bl.service.PasswordResetService;
import com.cgm.bulletin.ojt.common.TokenGenerator;
import com.cgm.bulletin.ojt.persistence.dao.PasswordResetDao;
import com.cgm.bulletin.ojt.persistence.dao.UserDao;
import com.cgm.bulletin.ojt.persistence.entity.PasswordReset;
import com.cgm.bulletin.ojt.persistence.entity.User;
import com.cgm.bulletin.ojt.web.form.PasswordResetSentMailForm;

/**
 * <h2>PasswordResetServiceImpl Class</h2>
 * <p>
 * Process for Displaying PasswordResetServiceImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service
@Transactional
public class PasswordResetServiceImpl implements PasswordResetService {
	/**
	 * <h2>psw_token_length</h2>
	 * <p>
	 * psw_token_length
	 * </p>
	 */
	public static final int psw_token_length = 20;
	/**
	 * <h2>psw_token_expired_minute</h2>
	 * <p>
	 * psw_token_expired_minute
	 * </p>
	 */
	public static final int psw_token_expired_minute = 3;
	/**
	 * <h2>now</h2>
	 * <p>
	 * now
	 * </p>
	 */
	private Timestamp now = new Timestamp(new Date(System.currentTimeMillis()).getTime());

	/**
	 * <h2>pswResetDao</h2>
	 * <p>
	 * pswResetDao
	 * </p>
	 */
	@Autowired
	private PasswordResetDao pswResetDao;

	/**
	 * <h2>passwordEncoder</h2>
	 * <p>
	 * passwordEncoder
	 * </p>
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * <h2>userDao</h2>
	 * <p>
	 * userDao
	 * </p>
	 */
	@Autowired
	private UserDao userDao;

	/**
	 * <h2>createPswResetToken</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param user_email
	 * @return
	 */
	@Override
	public PasswordResetSentMailForm createPswResetToken(String user_email) {
		if (isEmailExist(user_email)) {
			pswResetDao.dbDeletePswResetTokenByEmail(user_email);
		}
		String token = new TokenGenerator(psw_token_length).getToken();
		Timestamp expired = new Timestamp(
		        new Date(System.currentTimeMillis() + psw_token_expired_minute * 60 * 1000).getTime());
		System.out.println(expired);
		PasswordResetSentMailForm passwordResetForm = new PasswordResetSentMailForm();
		passwordResetForm.setUser_email(user_email);
		passwordResetForm.setToken(token);
		passwordResetForm.setCreated_at(now);
		passwordResetForm.setExpired_at(expired);
		this.pswResetDao.createPswResetToken(this.getPswToken(passwordResetForm));
		return passwordResetForm;
	}

	/**
	 * <h2>isEmailExist</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user_email
	 * @return
	 * @return boolean
	 */
	private boolean isEmailExist(String user_email) {
		PasswordReset pswReset = this.pswResetDao.dbGetPswResetDataByEmail(user_email);
		boolean result = false;
		result = pswReset != null ? true : result;
		return result;
	}

	/**
	 * <h2>getPswToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param passwordResetForm
	 * @return
	 * @return PasswordReset
	 */
	private PasswordReset getPswToken(PasswordResetSentMailForm passwordResetForm) {
		PasswordReset passwordReset = new PasswordReset(passwordResetForm);
		return passwordReset;
	}

	/**
	 * <h2>doGetDataByToken</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param token
	 * @return
	 */
	@Override
	public PasswordResetSentMailForm doGetDataByToken(String token) {
		try {
			PasswordResetSentMailForm passwordResetSentMailForm = new PasswordResetSentMailForm(
			        this.pswResetDao.dbGetDataByToken(token));
			return passwordResetSentMailForm;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * <h2>doUpdatePassword</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param newPasswordResetForm
	 */
	@Override
	public void doUpdatePassword(PasswordResetSentMailForm newPasswordResetForm) {
		newPasswordResetForm.setPassword(passwordEncoder.encode(newPasswordResetForm.getPassword()));
		User user = this.userDao.dbGetUserByEmail(newPasswordResetForm.getUser_email());
		user.setPassword(newPasswordResetForm.getPassword());
		user.setUpdated_at(new Date());
		this.userDao.dbUpdateUser(user);
	}

	/**
	 * <h2>doDeleteToken</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param token
	 */
	@Override
	public void doDeleteToken(String token) {
		this.pswResetDao.dbDeleteToken(token);
	}

}