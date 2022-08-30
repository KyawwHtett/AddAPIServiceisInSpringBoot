package com.cgm.bulletin.ojt.bl.service;

import java.util.List;

import com.cgm.bulletin.ojt.payload.request.CategoryRequest;
import com.cgm.bulletin.ojt.payload.response.CategoryResponse;

/**
 * <h2>CategoryService Class</h2>
 * <p>
 * Process for Displaying CategoryService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface ApiCategoryService {

	/**
	 * <h2>doGetAllCategories</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<Category>
	 */
	List<CategoryResponse> doGetAllCategories();

	void doSaveCategory(CategoryRequest categoryRequest);

	CategoryResponse doGetCategoryById(int category_id);

	boolean doUpdateCategory(CategoryRequest categoryRequest);

	Boolean doDeleteCategory(int category_id);
}