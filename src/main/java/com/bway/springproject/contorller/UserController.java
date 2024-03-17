package com.bway.springproject.contorller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.User;
import com.bway.springproject.service.UserService;
import com.bway.springproject.serviceimpl.UserServiceImpl;
import com.bway.springproject.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping({ "/", "/login" })
	public String getLogin() {

		return "LoginForm";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user, Model model, HttpSession session,
			@RequestParam("g-recaptcha-response") String recCode) throws IOException {

		if (VerifyRecaptcha.verify(recCode)) {

			user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			User usr = userService.userLogin(user.getEmail(), user.getPassword());

			if (usr != null) {

				log.info("------user login success--------");

				session.setAttribute("activeuser", usr);
				session.setMaxInactiveInterval(120);// session expire time

				// model.addAttribute("uname",usr.getFname());

				return "Home";
			}else {
				log.info("------------- login failed--------------");
				model.addAttribute("message", "user not found");
				return "LoginForm";
			}
		}

		log.info("------------- login failed--------------");
		model.addAttribute("message", "are you ROBOT?");
		return "LoginForm";
	}
	
	@GetMapping("/signup")
	public String getSignup() {

		return "SignupForm";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user) {

		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userService.userSignup(user);

		return "LoginForm";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		log.info("-------user logout success ------------");

		session.invalidate(); // session kill
		return "LoginForm";
	}

	@GetMapping("/profile")
	public String profile() {

		return "Profile";
	}

}
