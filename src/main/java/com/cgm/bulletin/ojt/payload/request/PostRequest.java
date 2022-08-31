package com.cgm.bulletin.ojt.payload.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.persistence.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <h2>PostRequest Class</h2>
 * <p>
 * Process for Displaying PostRequest
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Data
@AllArgsConstructor
public class PostRequest {
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
	@NotEmpty
	private String title;

	/**
	 * <h2>description</h2>
	 * <p>
	 * description
	 * </p>
	 */
	@NotEmpty
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
	 * <h2>deletd_user_id</h2>
	 * <p>
	 * deletd_user_id
	 * </p>
	 */
	private int deletd_user_id;

	/**
	 * <h2>deleted_at</h2>
	 * <p>
	 * deleted_at
	 * </p>
	 */
	private Date deleted_at;

	/**
	 * <h2>categories</h2>
	 * <p>
	 * categories
	 * </p>
	 */
	private Category categories;

	/**
	 * <h2>search</h2>
	 * <p>
	 * search
	 * </p>
	 */
	private String search;

	/**
	 * <h2>username</h2>
	 * <p>
	 * username
	 * </p>
	 */
	private String username;

	public PostRequest() {
		super();
	}

	/**
	 * <h2>Constructor for PostRequest</h2>
	 * <p>
	 * Constructor for PostRequest
	 * </p>
	 * 
	 * @param post
	 */
	public PostRequest(Post post) {
		this.post_id = post.getPost_id();
		this.title = post.getTitle();
		this.description = post.getDescription();
		this.flag = post.isFlag();
		this.created_user_id = post.getCreated_user_id();
		this.created_at = post.getCreated_at();
		this.updated_user_id = post.getUpdated_user_id();
		this.updated_at = post.getUpdated_at();
		this.deletd_user_id = post.getDeleted_user_id();
		this.deleted_at = post.getDeleted_at();
		this.categories = post.getCategories();
	}
}