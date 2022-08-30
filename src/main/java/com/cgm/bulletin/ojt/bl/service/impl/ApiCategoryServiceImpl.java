package com.cgm.bulletin.ojt.bl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public void doSaveCategory(@Valid CategoryRequest categoryRequest) {
		Category category = new Category(categoryRequest);
		this.categoryDao.dbSaveCategory(category);
	}

	@Override
	public CategoryResponse doGetCategoryById(int category_id) {
		Category category = this.categoryDao.dbGetCategoryById(category_id);
		if (category == null) {
			return null;
		}
		CategoryResponse categoryResponse = new CategoryResponse(category);
		return categoryResponse;
	}

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
}