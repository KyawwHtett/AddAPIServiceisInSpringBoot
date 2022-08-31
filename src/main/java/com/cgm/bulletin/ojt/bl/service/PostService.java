package com.cgm.bulletin.ojt.bl.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cgm.bulletin.ojt.bl.dto.PostDto;

/**
 * <h2>PostService Class</h2>
 * <p>
 * Process for Displaying PostService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface PostService {

	/**
	 * <h2>doSavePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return void
	 */
	void doSavePost();

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
	 * @return Page<PostDto>
	 */
	Page<PostDto> findPaginated(Pageable pageable, String search, int categoryId);

	/**
	 * <h2>findUserProfilePaginated</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param pageable
	 * @param search
	 * @param categoryId
	 * @param userId
	 * @return
	 * @return Page<PostDto>
	 */
	Page<PostDto> findUserProfilePaginated(Pageable pageable, String search, int categoryId, int userId);

	/**
	 * <h2>doDeletePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post_id
	 * @return void
	 */
	void doDeletePost(int post_id);

	/**
	 * <h2>doGetPostById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post_id
	 * @return
	 * @return PostDto
	 */
	PostDto doGetPostById(int post_id);

	/**
	 * <h2>doUpdatePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return void
	 */
	void doUpdatePost();

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
	void doDownloadPost(HttpServletResponse response) throws IOException;

	/**
	 * <h2>doGetCountByUserId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return int
	 */
	int doGetCountByUserId(int userId);
}