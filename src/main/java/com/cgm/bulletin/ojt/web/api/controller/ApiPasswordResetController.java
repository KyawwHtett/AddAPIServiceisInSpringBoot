package com.cgm.bulletin.ojt.web.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.bulletin.ojt.bl.service.ApiUserService;
import com.cgm.bulletin.ojt.bl.service.PasswordResetService;
import com.cgm.bulletin.ojt.payload.request.PasswordResetSendMailRequest;
import com.cgm.bulletin.ojt.payload.response.ApiResponse;
import com.cgm.bulletin.ojt.web.form.PasswordResetSentMailForm;

/**
 * <h2>ApiPasswordResetController Class</h2>
 * <p>
 * Process for Displaying ApiPasswordResetController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@RestController
@RequestMapping("/api/auth")
public class ApiPasswordResetController {
	/**
	 * <h2>apiUserService</h2>
	 * <p>
	 * apiUserService
	 * </p>
	 */
	@Autowired
	private ApiUserService apiUserService;

	/**
	 * <h2>mailSender</h2>
	 * <p>
	 * mailSender
	 * </p>
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * <h2>pswResetService</h2>
	 * <p>
	 * pswResetService
	 * </p>
	 */
	@Autowired
	private PasswordResetService pswResetService;

	/**
	 * <h2>sentMail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param sentMailForm
	 * @param request
	 * @return
	 * @return ResponseEntity<?>
	 */
	@PostMapping(value = "/password/reset/sendMail")
	public ResponseEntity<?> sentMail(@Valid @RequestBody PasswordResetSendMailRequest sentMailForm,
	        HttpServletRequest request) {
		if (!this.apiUserService.doApiIsEmailExist(sentMailForm.getUser_email())) {
			System.out.println(sentMailForm.getUser_email());
			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Invalid Email Address");
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
		PasswordResetSentMailForm pswResetSentMailForm = new PasswordResetSentMailForm(sentMailForm);
		pswResetSentMailForm = this.pswResetService.createPswResetToken(pswResetSentMailForm.getUser_email());
		String subUrl = getBaseUrl(request) + request.getServletPath() + "/" + pswResetSentMailForm.getToken();
		String url = subUrl.replace("/api/auth", "");
		this.sendMail(url, pswResetSentMailForm);
		return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "Email has been sent."), HttpStatus.OK);
	}

	/**
	 * <h2>getBaseUrl</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @return
	 * @return String
	 */
	private String getBaseUrl(HttpServletRequest request) {
		String url = request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() != 0) {
			url = url + ":" + request.getServerPort();
		}
		url = url + request.getContextPath();
		return url;
	}

	/**
	 * <h2>sendMail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param url
	 * @param sentMailForm
	 * @return void
	 */
	private void sendMail(String url, PasswordResetSentMailForm sentMailForm) {
		String sender = "cgm.kyawhtet@gmail.com";
		String subject = "Reset Your Password";
		String body = "Reset your password from following url : \n";
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(sentMailForm.getUser_email());
		email.setFrom(sender);
		email.setSubject(subject);
		email.setText(body + url);
		this.mailSender.send(email);
	}
}