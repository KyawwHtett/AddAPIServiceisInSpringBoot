package com.cgm.bulletin.ojt.bl.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.UserService;
import com.cgm.bulletin.ojt.persistence.dao.UserDao;
import com.cgm.bulletin.ojt.persistence.entity.User;
import com.cgm.bulletin.ojt.web.form.UserForm;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserServiceImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	/**
	 * <h2>userDao</h2>
	 * <p>
	 * userDao
	 * </p>
	 */
	@Autowired
	private UserDao userDao;

	/**
	 * <h2>passwordEncoder</h2>
	 * <p>
	 * passwordEncoder
	 * </p>
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * <h2>doSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param user
	 */
	@Override
	public void doSaveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreated_at(new Date());
		this.userDao.dbSaveUser(user);
	}

	/**
	 * <h2>doDeleteUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 */
	@Override
	public void doDeleteUser(int userId) {
		User user = this.userDao.dbGetUserById(userId);
		if (user != null) {
			user.setDeleted_at(new Date());
			this.userDao.dbDeleteUser(user);
		}
	}

	/**
	 * <h2>findAll</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@Override
	public List<User> findAll() {
		return this.userDao.findAll();
	}

	/**
	 * <h2>doGetUserById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public UserForm doGetUserById(int userId) {
		User user = this.userDao.dbGetUserById(userId);
		if (user == null) {
			return null;
		}
		UserForm userForm = new UserForm(user);
		return userForm;
	}

	/**
	 * <h2>doUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userForm
	 */
	@Override
	public void doUpdateUser(UserForm userForm) {
		User user = this.userDao.dbGetUserById(userForm.getId());
		if (user != null) {
			user.setUsername(userForm.getUsername());
			user.setEmail(userForm.getEmail());
			user.setGender(userForm.getGender());
			user.setType(userForm.getType());
			user.setUpdated_at(new Date());
			this.userDao.dbUpdateUser(user);
		}
	}

	/**
	 * <h2>dbGetUserCount</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@Override
	public long dbGetUserCount() {
		return this.userDao.dbGetUserCount();
	}

	/**
	 * <h2>doFindUserByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public UserDto doFindUserByEmail(String email) {
		User user = this.userDao.dbGetUserByEmail(email);
		if (user == null) {
			return null;
		}
		return new UserDto(user);
	}

	/**
	 * <h2>doDownloadUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param response
	 * @throws IOException
	 */
	@Override
	public void doDownloadUser(HttpServletResponse response) throws IOException {
		List<User> listUser = this.userDao.findAll();
		String fileName = "user_list.xlsx";

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("User List");
		XSSFRow rowHead = sheet.createRow((short) 0);
		rowHead.createCell(0).setCellValue("ID");
		rowHead.createCell(1).setCellValue("Name");
		rowHead.createCell(2).setCellValue("Email");
		rowHead.createCell(3).setCellValue("Gender");
		rowHead.createCell(4).setCellValue("Type");

		int count = 1;
		for (User user : listUser) {
			XSSFRow row = sheet.createRow((short) count);
			row.createCell(0).setCellValue(count);
			row.createCell(1).setCellValue(user.getUsername());
			row.createCell(2).setCellValue(user.getEmail());
			row.createCell(3).setCellValue(user.getGender());
			String type = user.getType().equals("0") ? "User" : "Admin";
			row.createCell(4).setCellValue(type);
			count++;
		}
		count = 0;

		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
			response.setHeader(headerKey, headerValue);
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
			IOUtils.closeQuietly(response.getOutputStream());
		}
	}

	/**
	 * <h2>doIsEmailExist</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public boolean doIsEmailExist(String email) {
		boolean result = false;
		User user = this.userDao.dbGetUserByEmail(email);
		result = (user == null) ? result : true;
		return result;
	}
}