package com.cgm.bulletin.ojt.persistence.dao;

import java.util.List;

import com.cgm.bulletin.ojt.persistence.entity.Category;

/**
 * <h2>CategoryDao Class</h2>
 * <p>
 * Process for Displaying CategoryDao
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface CategoryDao {
	/**
	 * <h2>dbSaveCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category
	 * @return void
	 */
	void dbSaveCategory(Category category);

	/**
	 * <h2>dbGetAllCategories</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<Category>
	 */
	List<Category> dbGetAllCategories();

	/**
	 * <h2>dbGetCategoryById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category_id
	 * @return
	 * @return Category
	 */
	Category dbGetCategoryById(int category_id);

	/**
	 * <h2>dbDeleteCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category
	 * @return void
	 */
	void dbDeleteCategory(Category category);

	/**
	 * <h2>dbUpdateCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category
	 * @return void
	 */
	void dbUpdateCategory(Category category);
}