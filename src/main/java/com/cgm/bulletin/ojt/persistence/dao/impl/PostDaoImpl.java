package com.cgm.bulletin.ojt.persistence.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgm.bulletin.ojt.persistence.dao.PostDao;
import com.cgm.bulletin.ojt.persistence.entity.Post;

/**
 * <h2>PostDaoImpl Class</h2>
 * <p>
 * Process for Displaying PostDaoImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Repository
@Transactional
public class PostDaoImpl implements PostDao {
	/**
	 * <h2>SELECT_POST_LIST_HQL</h2>
	 * <p>
	 * SELECT_POST_LIST_HQL
	 * </p>
	 */
	private static final String SELECT_POST_LIST_HQL = "SELECT p FROM Post p WHERE deleted_at = null ";

	/**
	 * <h2>SELECT_SEARCH_INPUT_HQL</h2>
	 * <p>
	 * SELECT_SEARCH_INPUT_HQL
	 * </p>
	 */
	private static final String SELECT_SEARCH_INPUT_HQL = "AND (p.title LIKE :search OR p.description LIKE :search) ";

	/**
	 * <h2>ORDER_BY_HQL</h2>
	 * <p>
	 * ORDER_BY_HQL
	 * </p>
	 */
	private static final String ORDER_BY_HQL = "ORDER BY p.updated_at DESC ";

	/**
	 * <h2>sessionFactory</h2>
	 * <p>
	 * sessionFactory
	 * </p>
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * <h2>dbSavePost</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param post
	 */
	@Override
	public void dbSavePost(Post post) {
		this.sessionFactory.getCurrentSession().save(post);
	}

	@Override
	public int dbApiSavePost(Post post) {
		int postId = (int) this.sessionFactory.getCurrentSession().save(post);
		return postId;
	}

	/**
	 * <h2>dbGetAllPosts</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param search
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Post> dbGetAllPosts(String search) {
		StringBuffer query = new StringBuffer(SELECT_POST_LIST_HQL);
		if (search != null) {
			query.append(SELECT_SEARCH_INPUT_HQL);
		}
		query.append(ORDER_BY_HQL);
		Query queryPostList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
		if (search != null) {
			queryPostList.setParameter("search", "%" + search + "%");
		}
		List<Post> listPost = (List<Post>) queryPostList.getResultList();
		return listPost;
	}

	/**
	 * <h2>dbGetPostById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param post_id
	 * @return
	 */
	@Override
	public Post dbGetPostById(int post_id) {
		return this.sessionFactory.getCurrentSession().get(Post.class, post_id);
	}

	/**
	 * <h2>dbDeletePostById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param post
	 */
	@Override
	public void dbDeletePostById(Post post) {
		this.sessionFactory.getCurrentSession().update(post);
	}

	/**
	 * <h2>dbUpdatePost</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param post
	 */
	@Override
	public void dbUpdatePost(Post post) {
		this.sessionFactory.getCurrentSession().update(post);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int dbGetCountByUserId(int userId) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer("SELECT COUNT(p) FROM Post p WHERE user_id = :user_id");
		javax.persistence.Query queryPostList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
		queryPostList.setParameter("user_id", userId);
		int counter = ((Number) ((Query) queryPostList).uniqueResult()).intValue();
//		int count = 0;
//		for (Iterator iterator = queryPostList.iterate(); iterator.hasNext();) {
//			int row = (int) iterator.next();
//			count = row;
//		}
		return counter;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Post> dbGetAllPostsByUser(int userId) {
		StringBuffer query = new StringBuffer(
		        "SELECT p FROM Post p WHERE user_id = :user_id and deleted_at = null ORDER BY p.updated_at DESC ");
		Query queryPostList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
		queryPostList.setParameter("user_id", userId);
		List<Post> listPost = (List<Post>) queryPostList.getResultList();
		return listPost;
	}
}