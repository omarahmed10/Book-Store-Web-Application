package com.db.controller;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.db.model.BookInfo;
import com.db.model.SearchQuery;
import com.db.model.SqlResult;
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
		model.addObject("itemsInCart", bookService.getCart(userService.getCurrentUserInfo().getUsername()).size());
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

	/*
	 * **************** Buying Books
	 */
	@RequestMapping(value = "/showBookInfo/{bookIsbn}/{bookTitle}", method = RequestMethod.GET)
	public ModelAndView showBook(@PathVariable Map<String, String> pathVars, @ModelAttribute("error") String errorMsg) {
		ModelAndView model = new ModelAndView("user/showBook");
		BookInfo b = bookService.searchBooks("Default", pathVars.get("bookIsbn"), pathVars.get("bookTitle")).get(0);
		System.out.println("ShowBOKK_________________" + b.getCategory());
		model.addObject("book", b);
		if (errorMsg != null)
			model.addObject("edittingBookErrorMsg", errorMsg);
		return model;
	}

	@RequestMapping(value = "/removeBook/{bookIsbn}/{bookTitle}", method = RequestMethod.GET)
	public String removeFromCart(@PathVariable Map<String, String> pathVars) {
		bookService.deleteFromCart(userService.getCurrentUserInfo().getUsername(), pathVars.get("bookIsbn"),
				pathVars.get("bookTitle"));
		return "redirect:/user/showCart";
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST, params = { "add" })
	public String addToCart(@ModelAttribute("book") BookInfo newBook) {
		System.out.println(newBook);
		bookService.addToCart(newBook, userService.getCurrentUserInfo().getUsername());
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST, params = { "cancel" })
	public String cancelAdding() {
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/showCart", method = RequestMethod.GET)
	public ModelAndView showCart() {
		ModelAndView model = new ModelAndView("user/checkout");
		List<BookInfo> list = bookService.getCart(userService.getCurrentUserInfo().getUsername());
		model.addObject("bookList", list);
		int totalPrice = 0;
		for (BookInfo e : list) {
			totalPrice += e.getPrice();
		}
		model.addObject("totalPrice", totalPrice);
		model.addObject("totalCnt", list.size());
		return model;
	}
}
