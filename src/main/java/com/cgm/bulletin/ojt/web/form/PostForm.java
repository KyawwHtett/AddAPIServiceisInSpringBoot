package com.cgm.bulletin.ojt.web.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.persistence.entity.Post;
import com.cgm.bulletin.ojt.persistence.entity.User;

/**
 * <h2>PostForm Class</h2>
 * <p>
 * Process for Displaying PostForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
public class PostForm {
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
	
	private User user;

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
	 * <h2>getDeletd_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public int getDeletd_user_id() {
		return deletd_user_id;
	}

	/**
	 * <h2>setDeletd_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param deletd_user_id
	 * @return void
	 */
	public void setDeletd_user_id(int deletd_user_id) {
		this.deletd_user_id = deletd_user_id;
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
	 * <h2>getSearch</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * <h2>setSearch</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param search
	 * @return void
	 */
	public void setSearch(String search) {
		this.search = search;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * <h2>Constructor for PostForm</h2>
	 * <p>
	 * Constructor for PostForm
	 * </p>
	 */
	public PostForm() {
		super();
	}

	/**
	 * <h2>Constructor for PostForm</h2>
	 * <p>
	 * Constructor for PostForm
	 * </p>
	 * 
	 * @param post
	 */
	public PostForm(Post post) {
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
		this.user = post.getUser();
	}
}