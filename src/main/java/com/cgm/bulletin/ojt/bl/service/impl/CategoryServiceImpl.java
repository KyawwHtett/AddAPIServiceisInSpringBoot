package com.cgm.bulletin.ojt.bl.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgm.bulletin.ojt.bl.service.CategoryService;
import com.cgm.bulletin.ojt.persistence.dao.CategoryDao;
import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.web.form.CategoryForm;

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
public class CategoryServiceImpl implements CategoryService {
	/**
	 * <h2>categoryDao</h2>
	 * <p>
	 * categoryDao
	 * </p>
	 */
	@Autowired
	private CategoryDao categoryDao;

	/**
	 * <h2>doSaveCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param categoryForm
	 */
	@Override
	public void doSaveCategory(CategoryForm categoryForm) {
		Category category = new Category(categoryForm);
		this.categoryDao.dbSaveCategory(category);
	}

	/**
	 * <h2>doGetAllCategories</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@Override
	public List<Category> doGetAllCategories() {
		return this.categoryDao.dbGetAllCategories();
	}

	/**
	 * <h2>doDeleteCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param category_id
	 */
	@Override
	public void doDeleteCategory(int category_id) {
		Category category = this.categoryDao.dbGetCategoryById(category_id);
		category.setDeleted_at(new Date());
		this.categoryDao.dbDeleteCategory(category);
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
	public CategoryForm doGetCategoryById(int category_id) {
		Category category = this.categoryDao.dbGetCategoryById(category_id);
		if (category == null) {
			return null;
		}
		CategoryForm categoryForm = new CategoryForm(category);
		return categoryForm;
	}

	/**
	 * <h2>doUpdateCategory</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param categoryForm
	 */
	@Override
	public void doUpdateCategory(CategoryForm categoryForm) {
		Category category = new Category(categoryForm);
		this.categoryDao.dbUpdateCategory(category);
	}
}