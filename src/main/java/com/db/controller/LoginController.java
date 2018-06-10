package com.db.controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.db.model.UserInfo;
import com.db.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

	public static DriverManagerDataSource ds;
	public static Connection con;
	public static UserInfo admin;

	@Autowired
	UserService userService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String init(RedirectAttributes redirectAttributes) {
		System.out.println("INIT__________________________LOGIN________________________________________");
		ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/BookStore?noAccessToProcedureBodies=true");
		admin = new UserInfo();
		admin.setUsername("root");
		admin.setPassword("admin");
		admin.setRole("MANAGER");
		redirectAttributes.addFlashAttribute("errorSignIn", null);
		redirectAttributes.addFlashAttribute("errorSignUp", null);
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGET(@ModelAttribute("errorSignIn") String errorSI,
			@ModelAttribute("errorSignUp") String errorSU) {
		ModelAndView model = new ModelAndView();
		if (errorSI != null) {
			model.addObject("errorMessage", errorSI);
		}
		if (errorSU != null) {
			model.addObject("signUpErrorMessage", errorSU);
		}
		model.setViewName("login/signin");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(@ModelAttribute("user") UserInfo user, RedirectAttributes redirectAttributes) {
		String response;
		try {
			con = ds.getConnection(admin.getUsername(),admin.getPassword());
			UserInfo loginUser = userService.getUser(user.getUsername());
			con.close();
			if (loginUser.getRole().equals("MANAGER")) {
				con = ds.getConnection(user.getUsername(), user.getPassword());
				response = "redirect:/manager";
			} else {
				con = ds.getConnection(user.getUsername(), user.getPassword());
				response = "redirect:/user";
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorSignIn",
					"invalid username or password please try again or sign up.");
			redirectAttributes.addFlashAttribute("errorSignUp", null);
			response = "redirect:/login";
		}
		return response;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		try {
			if (!con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("errorSignIn", null);
		redirectAttributes.addFlashAttribute("errorSignUp", null);
		return "redirect:/login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signOutPost(@ModelAttribute("user") UserInfo user, RedirectAttributes redirectAttributes) {
		String response;
		try {
			System.out.println("SIGNUP____________________________________" + user);
			con = ds.getConnection(admin.getUsername(), admin.getPassword());
			user.setRole("CUSTOMER");
			userService.addNewUser(user);
			con.close();
			con = ds.getConnection(user.getUsername(), user.getPassword());
			response = "redirect:/user";
		} catch (SQLException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorSignIn", null);
			redirectAttributes.addFlashAttribute("errorSignUp", "user name already exists please choose another one");
			response = "redirect:/login";
		}
		return response;
	}
}