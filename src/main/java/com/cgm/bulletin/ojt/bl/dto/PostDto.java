package com.cgm.bulletin.ojt.bl.dto;

import java.util.Date;

import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.persistence.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>PostDto Class</h2>
 * <p>
 * Process for Displaying PostDto
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	/**
	 * <h2>post_id</h2>
	 * <p>
	 * post_id
	 * </p>
	 */
	private int post_id;

	/**
	 * <h2>title</h2>
	 * <p>
	 * title
	 * </p>
	 */
	private String title;

	/**
	 * <h2>description</h2>
	 * <p>
	 * description
	 * </p>
	 */
	private String description;

	/**
	 * <h2>flag</h2>
	 * <p>
	 * flag
	 * </p>
	 */
	private boolean flag;

	/**
	 * <h2>created_user_id</h2>
	 * <p>
	 * created_user_id
	 * </p>
	 */
	private int created_user_id;

	/**
	 * <h2>created_at</h2>
	 * <p>
	 * created_at
	 * </p>
	 */
	private Date created_at;

	/**
	 * <h2>updated_user_id</h2>
	 * <p>
	 * updated_user_id
	 * </p>
	 */
	private int updated_user_id;

	/**
	 * <h2>updated_at</h2>
	 * <p>
	 * updated_at
	 * </p>
	 */
	private Date updated_at;

	/**
	 * <h2>deleted_user_id</h2>
	 * <p>
	 * deleted_user_id
	 * </p>
	 */
	private int deleted_user_id;

	/**
	 * <h2>deleted_at</h2>
	 * <p>
	 * deleted_at
	 * </p>
	 */
	private Date deleted_at;

	/**
	 * <h2>prettyTime</h2>
	 * <p>
	 * prettyTime
	 * </p>
	 */
	private String prettyTime;

	/**
	 * <h2>categories</h2>
	 * <p>
	 * categories
	 * </p>
	 */
	private Category categories;

	/**
	 * <h2>username</h2>
	 * <p>
	 * username
	 * </p>
	 */
	private String username;

	/**
	 * <h2>Constructor for PostDto</h2>
	 * <p>
	 * Constructor for PostDto
	 * </p>
	 * 
	 * @param post
	 */
	public PostDto(Post post) {
		this.post_id = post.getPost_id();
		this.title = post.getTitle();
		this.description = post.getDescription();
		this.flag = post.isFlag();
		this.created_user_id = post.getCreated_user_id();
		this.created_at = post.getCreated_at();
		this.updated_user_id = post.getUpdated_user_id();
		this.updated_at = post.getUpdated_at();
		this.deleted_user_id = post.getDeleted_user_id();
		this.deleted_at = post.getDeleted_at();
		this.categories = post.getCategories();
	}
}