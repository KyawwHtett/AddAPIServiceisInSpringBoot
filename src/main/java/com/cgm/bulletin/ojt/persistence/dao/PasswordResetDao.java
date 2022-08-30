package com.cgm.bulletin.ojt.persistence.dao;

import com.cgm.bulletin.ojt.persistence.entity.PasswordReset;

/**
 * <h2>PasswordResetDao Class</h2>
 * <p>
 * Process for Displaying PasswordResetDao
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface PasswordResetDao {
	/**
	 * <h2>dbGetPswResetDataByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user_email
	 * @return
	 * @return PasswordReset
	 */
	public PasswordReset dbGetPswResetDataByEmail(String user_email);

	/**
	 * <h2>dbDeletePswResetTokenByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user_email
	 * @return void
	 */
	public void dbDeletePswResetTokenByEmail(String user_email);

	/**
	 * <h2>createPswResetToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param pswToken
	 * @return void
	 */
	public void createPswResetToken(PasswordReset pswToken);

	/**
	 * <h2>dbGetDataByToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param token
	 * @return
	 * @return PasswordReset
	 */
	public PasswordReset dbGetDataByToken(String token);

	/**
	 * <h2>dbDeleteToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param token
	 * @return void
	 */
	public void dbDeleteToken(String token);

}