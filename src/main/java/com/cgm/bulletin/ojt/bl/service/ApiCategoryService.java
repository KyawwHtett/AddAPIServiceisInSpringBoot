package com.cgm.bulletin.ojt.bl.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

	/**
	 * <h2>doSaveCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param categoryRequest
	 * @return void
	 */
	void doSaveCategory(CategoryRequest categoryRequest);

	/**
	 * <h2>doGetCategoryById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category_id
	 * @return
	 * @return CategoryResponse
	 */
	CategoryResponse doGetCategoryById(int category_id);

	/**
	 * <h2>doUpdateCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param categoryRequest
	 * @return
	 * @return boolean
	 */
	boolean doUpdateCategory(CategoryRequest categoryRequest);

	/**
	 * <h2>doDeleteCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param category_id
	 * @return
	 * @return Boolean
	 */
	Boolean doDeleteCategory(int category_id);

	/**
	 * <h2>doImportCategory</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @return String
	 */
	public String doImportCategory(MultipartFile file) throws IOException;
}