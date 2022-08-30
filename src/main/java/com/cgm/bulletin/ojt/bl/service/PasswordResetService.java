package com.cgm.bulletin.ojt.bl.service;

import com.cgm.bulletin.ojt.web.form.PasswordResetSentMailForm;

/**
 * <h2>PasswordResetService Class</h2>
 * <p>
 * Process for Displaying PasswordResetService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface PasswordResetService {
	/**
	 * <h2>createPswResetToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user_email
	 * @return
	 * @return PasswordResetSentMailForm
	 */
	public PasswordResetSentMailForm createPswResetToken(String user_email);

	/**
	 * <h2>doGetDataByToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param token
	 * @return
	 * @return PasswordResetSentMailForm
	 */
	public PasswordResetSentMailForm doGetDataByToken(String token);

	/**
	 * <h2>doUpdatePassword</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param newPasswordResetForm
	 * @return void
	 */
	public void doUpdatePassword(PasswordResetSentMailForm newPasswordResetForm);

	/**
	 * <h2>doDeleteToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param token
	 * @return void
	 */
	public void doDeleteToken(String token);

}