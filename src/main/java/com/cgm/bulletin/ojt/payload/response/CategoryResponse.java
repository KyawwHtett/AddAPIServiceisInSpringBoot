package com.cgm.bulletin.ojt.payload.response;

import java.io.Serializable;
import java.util.Date;

import com.cgm.bulletin.ojt.persistence.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>CategoryResponse Class</h2>
 * <p>
 * Process for Displaying CategoryResponse
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse implements Serializable {
	/**
	 * <h2>serialVersionUID</h2>
	 * <p>
	 * serialVersionUID
	 * </p>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <h2>category_id</h2>
	 * <p>
	 * category_id
	 * </p>
	 */
	private int category_id;

	/**
	 * <h2>category_name</h2>
	 * <p>
	 * category_name
	 * </p>
	 */
	private String category_name;

	/**
	 * <h2>deleted_at</h2>
	 * <p>
	 * deleted_at
	 * </p>
	 */
	private Date deleted_at;

//	private List<Post> posts;

//	public List<Post> getPosts() {
//		return posts == null ? Collections.emptyList() : new ArrayList<Post>();
//	}
//
//	public void setPosts(List<Post> posts) {
//		if (posts == null) {
//			this.posts = null;
//		} else {
//			this.posts = Collections.unmodifiableList(posts);
//		}
//	}

	/**
	 * <h2>Constructor for CategoryResponse</h2>
	 * <p>
	 * Constructor for CategoryResponse
	 * </p>
	 * 
	 * @param category
	 */
	public CategoryResponse(Category category) {
		super();
		this.category_id = category.getCategory_id();
		this.category_name = category.getCategory_name();
		this.deleted_at = category.getDeleted_at();
	}
}