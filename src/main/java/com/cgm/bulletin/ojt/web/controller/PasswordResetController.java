package com.cgm.bulletin.ojt.web.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.bulletin.ojt.bl.service.PasswordResetService;
import com.cgm.bulletin.ojt.bl.service.UserService;
import com.cgm.bulletin.ojt.web.form.PasswordResetForm;
import com.cgm.bulletin.ojt.web.form.PasswordResetSentMailForm;

/**
 * <h2>PasswordResetController Class</h2>
 * <p>
 * Process for Displaying PasswordResetController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Controller
public class PasswordResetController {
	/**
	 * <h2>messageSource</h2>
	 * <p>
	 * messageSource
	 * </p>
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * <h2>mailSender</h2>
	 * <p>
	 * mailSender
	 * </p>
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	private UserService userService;

	/**
	 * <h2>pswResetService</h2>
	 * <p>
	 * pswResetService
	 * </p>
	 */
	@Autowired
	private PasswordResetService pswResetService;

	/**
	 * <h2>passwordResetMail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping(value = "/password/reset")
	public ModelAndView passwordResetMail() {
		ModelAndView mv = new ModelAndView("/user/password/resetPassword");
		PasswordResetSentMailForm sentMailForm = new PasswordResetSentMailForm();
		mv.addObject("sentMailForm", sentMailForm);
		return mv;
	}

	/**
	 * <h2>sentMail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param sentMailForm
	 * @param result
	 * @param request
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/password/reset/sendMail", method = RequestMethod.POST)
	public ModelAndView sentMail(@Valid @ModelAttribute("sentMailForm") PasswordResetSentMailForm sentMailForm,
	        BindingResult result, HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView("/user/password/resetPassword");
		if (result.hasErrors()) {
			return modelView;
		} else if (!this.userService.doIsEmailExist(sentMailForm.getUser_email())) {
			modelView.addObject("errorMsg", this.messageSource.getMessage("R001", null, null));
			return modelView;
		}
		sentMailForm = this.pswResetService.createPswResetToken(sentMailForm.getUser_email());
		String url = getBaseUrl(request) + request.getServletPath() + "/" + sentMailForm.getToken();
		this.sendMail(url, sentMailForm);
		modelView.addObject("successMsg", messageSource.getMessage("R002", null, null));
		return modelView;
	}

	/**
	 * <h2>resetPasswordForm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param token
	 * @return
	 * @return ModelAndView
	 */
	@GetMapping(value = "/password/reset/sendMail/{token}")
	public ModelAndView resetPasswordForm(@PathVariable String token) {
		System.out.println(token);
		ModelAndView model = new ModelAndView("/user/password/resetPasswordForm");
		PasswordResetSentMailForm passwordResetForm = this.pswResetService.doGetDataByToken(token);
		if (passwordResetForm == null) {
			model.addObject("errorMsg", messageSource.getMessage("R003", null, null));
			return model;
		} else if (isTokenExpired(passwordResetForm.getExpired_at())) {
			model.addObject("errorMsg", messageSource.getMessage("R004", null, null));
			return model;
		}
		PasswordResetForm passwordChangeResetForm = new PasswordResetForm();
		passwordChangeResetForm.setToken(token);
		model.addObject("passwordResetForm", passwordChangeResetForm);
		return model;
	}

	/**
	 * <h2>resetPassword</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param passwordResetForm
	 * @param result
	 * @return
	 * @return ModelAndView
	 */
	@PostMapping(value = "/password/changeReset")
	public ModelAndView resetPassword(@Valid @ModelAttribute("passwordResetForm") PasswordResetForm passwordResetForm,
	        BindingResult result) {
		PasswordResetSentMailForm expired = this.pswResetService.doGetDataByToken(passwordResetForm.getToken());
		if (expired == null || isTokenExpired(expired.getExpired_at())) {
			ModelAndView model = new ModelAndView("/user/password/resetPasswordForm");
			model.addObject("errorMsg", messageSource.getMessage("R004", null, null));
			return model;
		}
		if (result.hasErrors()) {
			return new ModelAndView("/user/password/resetPasswordForm");
		}
		String userEmail = this.pswResetService.doGetDataByToken(passwordResetForm.getToken()).getUser_email();
		PasswordResetSentMailForm newPasswordResetForm = new PasswordResetSentMailForm();
		newPasswordResetForm.setUser_email(userEmail);
		newPasswordResetForm.setPassword(passwordResetForm.getPassword());
		this.pswResetService.doUpdatePassword(newPasswordResetForm);
		this.pswResetService.doDeleteToken(passwordResetForm.getToken());
		ModelAndView mv = new ModelAndView("/user/password/resetPassword");
		mv.addObject("successMsg", messageSource.getMessage("R005", null, null));
		return mv;
	}

	/**
	 * <h2>isTokenExpired</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param expired_at
	 * @return
	 * @return boolean
	 */
	private boolean isTokenExpired(Timestamp expired_at) {
		Timestamp now = new Timestamp(new Date().getTime());
		return now.after(expired_at);
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
	private void sendMail(String url, @Valid PasswordResetSentMailForm sentMailForm) {
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