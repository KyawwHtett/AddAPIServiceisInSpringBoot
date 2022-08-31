package com.cgm.bulletin.ojt.bl.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cgm.bulletin.ojt.bl.service.ApiCategoryService;
import com.cgm.bulletin.ojt.payload.request.CategoryRequest;
import com.cgm.bulletin.ojt.payload.response.CategoryResponse;
import com.cgm.bulletin.ojt.persistence.dao.CategoryDao;
import com.cgm.bulletin.ojt.persistence.entity.Category;

/**
 * <h2>CategoryServiceImpl Class</h2>
 * <p>
 * Process for Displaying CategoryServiceImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service
@Transactional
public class ApiCategoryServiceImpl implements ApiCategoryService {
	/**
	 * <h2>categoryDao</h2>
	 * <p>
	 * categoryDao
	 * </p>
	 */
	@Autowired
	private CategoryDao categoryDao;

	/**
	 * <h2>sessionFactory</h2>
	 * <p>
	 * sessionFactory
	 * </p>
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * <h2>doGetAllCategories</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@Override
	public List<CategoryResponse> doGetAllCategories() {
		List<Category> categoryList = this.categoryDao.dbGetAllCategories();
		List<CategoryResponse> catResponseList = new ArrayList<CategoryResponse>();
		for (Category category : categoryList) {
			CategoryResponse categoryResponse = new CategoryResponse(category);
			catResponseList.add(categoryResponse);
		}
		return catResponseList;
	}

	/**
	 * <h2>doSaveCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param categoryRequest
	 */
	@Override
	public void doSaveCategory(@Valid CategoryRequest categoryRequest) {
		Category category = new Category(categoryRequest);
		this.categoryDao.dbSaveCategory(category);
	}

	/**
	 * <h2>doGetCategoryById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param category_id
	 * @return
	 */
	@Override
	public CategoryResponse doGetCategoryById(int category_id) {
		Category category = this.categoryDao.dbGetCategoryById(category_id);
		if (category == null) {
			return null;
		}
		CategoryResponse categoryResponse = new CategoryResponse(category);
		return categoryResponse;
	}

	/**
	 * <h2>doUpdateCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param categoryRequest
	 * @return
	 */
	@Override
	public boolean doUpdateCategory(CategoryRequest categoryRequest) {
		Category category = new Category(categoryRequest);
		Category ifExist = this.categoryDao.dbGetCategoryById(category.getCategory_id());
		if (ifExist == null) {
			return false;
		}
		this.categoryDao.dbUpdateCategory(category);
		return true;
	}

	/**
	 * <h2>doDeleteCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param category_id
	 * @return
	 */
	@Override
	public Boolean doDeleteCategory(int category_id) {
		Category category = this.categoryDao.dbGetCategoryById(category_id);
		if (category == null) {
			return false;
		}
		category.setDeleted_at(new Date());
		this.categoryDao.dbDeleteCategory(category);
		return true;
	}

	/**
	 * <h2>doImportCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@Override
	public String doImportCategory(MultipartFile file) throws IOException {
		String errorMsg = this.ValidateFile(file);
		if (errorMsg != null) {
			return errorMsg;
		}
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			Category category = new Category();
			Row row = sheet.getRow(i);
			Cell cellUsername = row.getCell(1);
			category.setCategory_name(cellUsername.getStringCellValue());
			this.categoryDao.dbSaveCategory(category);
		}
		session.getTransaction().commit();
		session.close();
		workbook.close();
		return "File Upload Successful";
	}

	/**
	 * <h2>ValidateFile</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @return String
	 */
	@SuppressWarnings("resource")
	private String ValidateFile(MultipartFile file) throws IOException {
		// check if file is null
		if (file.isEmpty()) {
			return "No file is selected";
		}
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheetAt(0);
		// check extension
		if (!extension.equals("xlsx")) {
			return "File Extension Wrong!";
		}
		// check null
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				return "File is null";
			}
		}
		// check file Type
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			// Category Name String
			Cell cellUsername = row.getCell(1);
			if (cellUsername == null || (cellUsername.getCellType() != CellType.STRING)) {
				return "Wrong Data Type in this file Category Name";
			}
		}
		// check file has no data
		if (sheet.getFirstRowNum() == sheet.getLastRowNum()) {
			return "No Data in the File";
		}
		return null;
	}
}