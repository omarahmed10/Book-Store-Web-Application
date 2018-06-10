package com.db.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.db.model.BookInfo;
import com.db.model.SearchQuery;
import com.db.model.UserInfo;
import com.db.service.BookService;
import com.db.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String init() {
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listBooks() {
		ModelAndView model = new ModelAndView();
		model.setViewName("user/BookList");
		model.addObject("bookList", bookService.listBooks());
		model.addObject("isAdmin", userService.getCurrentUserInfo().getRole().equals("MANAGER"));
		return model;
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView editAccountGET() {
		ModelAndView model = new ModelAndView("user/Account");
		UserInfo user = userService.getCurrentUserInfo();
		System.out.println("/user/info_______________" + user);
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editAccountPOST(@ModelAttribute("user") UserInfo user) {
		userService.editUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@ModelAttribute("searchQuery") SearchQuery searchQuery) throws SQLException {
		List<BookInfo> books = bookService.searchBooks(searchQuery.getAttribute(), searchQuery.getText());
		ModelAndView model = new ModelAndView("user/BookList");
		model.addObject("bookList", books);
		return model;
	}

}
