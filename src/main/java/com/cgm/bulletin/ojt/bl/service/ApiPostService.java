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

	PostResponse doSavePost(PostForm postForm);

	Page<PostResponse> findPaginated(Pageable pageable, String search, int categoryId);

	public ApiResponse doDeletePost(int post_id);

	public PostResponse doGetPostById(int post_id);

	public PostResponse doUpdatePost(PostRequest postRequest);

	public void doDownloadPost(HttpServletResponse response) throws IOException;

	List<PostResponse> doGetAllPostsByUser(int id);
}