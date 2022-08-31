package com.cgm.bulletin.ojt.bl.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cgm.bulletin.ojt.payload.request.PostRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.PostResponse;
import com.cgm.bulletin.ojt.web.form.PostForm;

/**
 * <h2>PostService Class</h2>
 * <p>
 * Process for Displaying PostService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface ApiPostService {

	/**
	 * <h2>doSavePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postForm
	 * @return
	 * @return PostResponse
	 */
	PostResponse doSavePost(PostForm postForm);

	/**
	 * <h2>findPaginated</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param pageable
	 * @param search
	 * @param categoryId
	 * @return
	 * @return Page<PostResponse>
	 */
	Page<PostResponse> findPaginated(Pageable pageable, String search, int categoryId);

	/**
	 * <h2>doDeletePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post_id
	 * @return
	 * @return ApiResponse
	 */
	public ApiResponse doDeletePost(int post_id);

	/**
	 * <h2>doGetPostById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post_id
	 * @return
	 * @return PostResponse
	 */
	public PostResponse doGetPostById(int post_id);

	/**
	 * <h2>doUpdatePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param postRequest
	 * @return
	 * @return PostResponse
	 */
	public PostResponse doUpdatePost(PostRequest postRequest);

	/**
	 * <h2>doDownloadPost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param response
	 * @throws IOException
	 * @return void
	 */
	public void doDownloadPost(HttpServletResponse response) throws IOException;

	/**
	 * <h2>doGetAllPostsByUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param id
	 * @return
	 * @return List<PostResponse>
	 */
	List<PostResponse> doGetAllPostsByUser(int id);
}