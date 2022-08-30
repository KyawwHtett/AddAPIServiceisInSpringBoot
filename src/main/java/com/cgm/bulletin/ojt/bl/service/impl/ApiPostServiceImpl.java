package com.cgm.bulletin.ojt.bl.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cgm.bulletin.ojt.bl.service.ApiPostService;
import com.cgm.bulletin.ojt.payload.request.PostRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.payload.response.PostResponse;
import com.cgm.bulletin.ojt.persistence.dao.PostDao;
import com.cgm.bulletin.ojt.persistence.dao.UserDao;
import com.cgm.bulletin.ojt.persistence.entity.Category;
import com.cgm.bulletin.ojt.persistence.entity.Post;
import com.cgm.bulletin.ojt.persistence.entity.User;
import com.cgm.bulletin.ojt.web.form.PostForm;

/**
 * <h2>PostServiceImpl Class</h2>
 * <p>
 * Process for Displaying PostServiceImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service
@Transactional
public class ApiPostServiceImpl implements ApiPostService {

	/**
	 * <h2>postDao</h2>
	 * <p>
	 * postDao
	 * </p>
	 */
	@Autowired
	private PostDao postDao;

	/**
	 * <h2>userDao</h2>
	 * <p>
	 * userDao
	 * </p>
	 */
	@Autowired
	private UserDao userDao;

	/**
	 * <h2>doSavePost</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 */
	@Override
	public PostResponse doSavePost(PostForm postForm) {
		Post post = new Post(postForm);
		post.setCreated_user_id(1);
		post.setCreated_at(new Date());
		post.setUpdated_user_id(1);
		post.setUpdated_at(new Date());
		PostResponse savePostResponse = new PostResponse(this.postDao.dbGetPostById(this.postDao.dbApiSavePost(post)));
		return savePostResponse;
	}

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
	 */
	@Override
	public Page<PostResponse> findPaginated(Pageable pageable, String search, int categoryId) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		search = (search == null) ? search : search.trim();
		List<Post> postList = this.postDao.dbGetAllPosts(search);
		List<PostResponse> posts = new ArrayList<PostResponse>();
		for (Post post : postList) {
			if (categoryId != 0) {
				if (post.getCategories().getCategory_id() == categoryId) {
					PostResponse postDto = new PostResponse(post);
					User user = this.userDao.dbGetUserById(postDto.getCreated_user_id());
					postDto.setUsername(user.getUsername());
					postDto.setPrettyTime(this.calculateTimeAgoWithPrettyTime(postDto.getUpdated_at()));
					posts.add(postDto);
				}
			} else {
				PostResponse postDto = new PostResponse(post);
				User user = this.userDao.dbGetUserById(postDto.getCreated_user_id());
				postDto.setUsername(user.getUsername());
				postDto.setPrettyTime(this.calculateTimeAgoWithPrettyTime(postDto.getUpdated_at()));
				posts.add(postDto);
			}
		}

		List<PostResponse> list;

		if (posts.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, posts.size());
			list = posts.subList(startItem, toIndex);
		}

		Page<PostResponse> postPage = new PageImpl<PostResponse>(list, PageRequest.of(currentPage, pageSize),
		        posts.size());
		return postPage;
	}

	/**
	 * <h2>doDeletePost</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param post_id
	 */
	@Override
	public ApiResponse doDeletePost(int post_id) {
		Post post = this.postDao.dbGetPostById(post_id);
		if (post != null) {
			post.setDeleted_user_id(1);
			post.setDeleted_at(new Date());
			this.postDao.dbDeletePostById(post);
			return new ApiResponse(Boolean.TRUE, "You successfully deleted post");
		} else {
			return new ApiResponse(Boolean.FALSE, "Post doesn't exist!");
		}
	}

	/**
	 * <h2>doGetPostById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param post_id
	 * @return
	 */
	@Override
	public PostResponse doGetPostById(int post_id) {
		Post post = this.postDao.dbGetPostById(post_id);
		if (post != null) {
			PostResponse postResponse = new PostResponse(post);
			return postResponse;
		}
		return null;
	}

	@Override
	public PostResponse doUpdatePost(PostRequest postRequest) {
		Post post = this.postDao.dbGetPostById(postRequest.getPost_id());
		post.setTitle(postRequest.getTitle());
		post.setDescription(postRequest.getDescription());
		post.setCategories(postRequest.getCategories());
		post.setFlag(postRequest.isFlag());
		post.setUpdated_user_id(1);
		post.setUpdated_at(new Date());
		this.postDao.dbUpdatePost(post);
		PostResponse postResponse = new PostResponse(post);
		postResponse.setCategories(post.getCategories());
		return postResponse;
	}

	/**
	 * <h2>calculateTimeAgoWithPrettyTime</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param pastTime
	 * @return
	 * @return String
	 */
	private String calculateTimeAgoWithPrettyTime(Date pastTime) {
		PrettyTime prettyTime = new PrettyTime();
		return prettyTime.format(pastTime);
	}

	/**
	 * <h2>loginUserInfo</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return UserDto
	 */
//	private UserDto loginUserInfo() {
//		UserDto userDto = (UserDto) this.session.getAttribute("LOGIN_USER");
//		return userDto;
//	}

	/**
	 * <h2>doDownloadPost</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param response
	 * @throws IOException
	 */
	@Override
	public void doDownloadPost(HttpServletResponse response) throws IOException {
		List<Post> listPost = this.postDao.dbGetAllPosts(null);
		String fileName = "post_list.xlsx";

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Post List");
		XSSFRow rowHead = sheet.createRow((short) 0);
		rowHead.createCell(0).setCellValue("ID");
		rowHead.createCell(1).setCellValue("Post Title");
		rowHead.createCell(2).setCellValue("Post Description");
		rowHead.createCell(3).setCellValue("Flag");
		rowHead.createCell(4).setCellValue("Category Name");
		rowHead.createCell(5).setCellValue("Created At");

		int count = 1;
		for (Post post : listPost) {
			XSSFRow row = sheet.createRow((short) count);
			row.createCell(0).setCellValue(count);
			row.createCell(1).setCellValue(post.getTitle());
			row.createCell(2).setCellValue(post.getDescription());
			String flag = post.isFlag() ? "Public" : "Private";
			row.createCell(3).setCellValue(flag);
			row.createCell(4).setCellValue(post.getCategories().getCategory_name());
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			row.createCell(5).setCellValue(dateformat.format(post.getCreated_at()));
			count++;
		}
		count = 0;

		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
			response.setHeader(headerKey, headerValue);
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
			IOUtils.closeQuietly(response.getOutputStream());
		}
	}

	@Override
	public List<PostResponse> doGetAllPostsByUser(int id) {
		List<Post> listPost = this.postDao.dbGetAllPostsByUser(id);
		List<PostResponse> setList = new ArrayList<PostResponse>();
		for (Post post : listPost) {
			PostResponse postResponse = new PostResponse(post);
			setList.add(postResponse);
			Category category = new Category();
			category.setCategory_id(post.getCategories().getCategory_id());
			category.setCategory_name(post.getCategories().getCategory_name());
			category.setDeleted_at(post.getCategories().getDeleted_at());
			postResponse.setCategories(category);
		}
		return setList;
	}
}