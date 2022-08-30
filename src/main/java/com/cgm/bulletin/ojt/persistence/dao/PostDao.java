package com.cgm.bulletin.ojt.persistence.dao;

import java.util.List;

import com.cgm.bulletin.ojt.persistence.entity.Post;

/**
 * <h2>PostDao Class</h2>
 * <p>
 * Process for Displaying PostDao
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface PostDao {

	/**
	 * <h2>dbSavePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post
	 * @return void
	 */
	void dbSavePost(Post post);
	
	public int dbApiSavePost(Post post);

	/**
	 * <h2>dbGetAllPosts</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param search
	 * @return
	 * @return List<Post>
	 */
	List<Post> dbGetAllPosts(String search);

	/**
	 * <h2>dbGetPostById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post_id
	 * @return
	 * @return Post
	 */
	Post dbGetPostById(int post_id);

	/**
	 * <h2>dbDeletePostById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post
	 * @return void
	 */
	void dbDeletePostById(Post post);

	/**
	 * <h2>dbUpdatePost</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post
	 * @return void
	 */
	void dbUpdatePost(Post post);

	int dbGetCountByUserId(int userId);

	List<Post> dbGetAllPostsByUser(int userId);
}