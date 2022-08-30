package com.cgm.bulletin.ojt.bl.dto;

import java.util.Date;

import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.persistence.entity.Post;

/**
 * <h2>PostDto Class</h2>
 * <p>
 * Process for Displaying PostDto
 * </p>
 * 
 * @author KyawHtet
 *
 */
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
	 * <h2>getPost_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getPost_id() {
		return post_id;
	}

	/**
	 * <h2>setPost_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param post_id
	 * @return void
	 */
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	/**
	 * <h2>getTitle</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <h2>setTitle</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param title
	 * @return void
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * <h2>getDescription</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <h2>setDescription</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param description
	 * @return void
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * <h2>isFlag</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return boolean
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * <h2>setFlag</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param flag
	 * @return void
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * <h2>getCreated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getCreated_user_id() {
		return created_user_id;
	}

	/**
	 * <h2>setCreated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param created_user_id
	 * @return void
	 */
	public void setCreated_user_id(int created_user_id) {
		this.created_user_id = created_user_id;
	}

	/**
	 * <h2>getCreated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getCreated_at() {
		return created_at;
	}

	/**
	 * <h2>setCreated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param created_at
	 * @return void
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	/**
	 * <h2>getUpdated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getUpdated_user_id() {
		return updated_user_id;
	}

	/**
	 * <h2>setUpdated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updated_user_id
	 * @return void
	 */
	public void setUpdated_user_id(int updated_user_id) {
		this.updated_user_id = updated_user_id;
	}

	/**
	 * <h2>getUpdated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getUpdated_at() {
		return updated_at;
	}

	/**
	 * <h2>setUpdated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updated_at
	 * @return void
	 */
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * <h2>getDeleted_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getDeleted_user_id() {
		return deleted_user_id;
	}

	/**
	 * <h2>setDeleted_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param deleted_user_id
	 * @return void
	 */
	public void setDeleted_user_id(int deleted_user_id) {
		this.deleted_user_id = deleted_user_id;
	}

	/**
	 * <h2>getDeleted_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	public Date getDeleted_at() {
		return deleted_at;
	}

	/**
	 * <h2>setDeleted_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param deleted_at
	 * @return void
	 */
	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	/**
	 * <h2>getPrettyTime</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getPrettyTime() {
		return prettyTime;
	}

	/**
	 * <h2>setPrettyTime</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param prettyTime
	 * @return void
	 */
	public void setPrettyTime(String prettyTime) {
		this.prettyTime = prettyTime;
	}

	/**
	 * <h2>getCategories</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Category
	 */
	public Category getCategories() {
		return categories;
	}

	/**
	 * <h2>setCategories</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param categories
	 * @return void
	 */
	public void setCategories(Category categories) {
		this.categories = categories;
	}

	/**
	 * <h2>getUsername</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * <h2>setUsername</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param username
	 * @return void
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * <h2>Constructor for PostDto</h2>
	 * <p>
	 * Constructor for PostDto
	 * </p>
	 */
	public PostDto() {
		super();
	}

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