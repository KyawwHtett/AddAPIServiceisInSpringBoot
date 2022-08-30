package com.cgm.bulletin.ojt.payload.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.cgm.bulletin.ojt.persistence.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private int category_id;

	@NotEmpty
	private String category_name;

	private Date deleted_at;

//	private List<Post> posts;

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

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
//
//	}
	public CategoryRequest(Category category) {
		super();
		this.category_id = category.getCategory_id();
		this.category_name = category.getCategory_name();
		this.deleted_at = category.getDeleted_at();
	}
}