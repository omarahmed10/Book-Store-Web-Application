package com.db.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.db.model.BookInfo;
import com.db.model.SearchQuery;
import com.db.model.SqlResult;
import com.db.model.UserInfo;
import com.db.service.BookService;
import com.db.service.OrderService;
import com.db.service.UserService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView model = new ModelAndView("manager/Manager");
		model.addObject("bookList", bookService.listBooks());
		model.addObject("orderCnt", orderService.countOrders());
		return model;
	}

	/*
	 ************************** BOOK MANAGEMENT
	 */

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("searchQuery") SearchQuery searchQuery) {
		ModelAndView model = new ModelAndView("manager/Manager");
		model.addObject("bookList", bookService.searchBooks(searchQuery.getAttribute(), searchQuery.getText()));
		return model;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addNewBook(@ModelAttribute("error") String errorMsg) {
		ModelAndView model = new ModelAndView("manager/Add_Book");
		if (errorMsg != null)
			model.addObject("addingBookErrorMsg", errorMsg);
		return model;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addNewBook(@ModelAttribute("book") BookInfo newBook, RedirectAttributes redirectAttributes) {
		String response = "redirect:/manager";
		System.out.println(newBook);
		SqlResult sr = bookService.addNewBook(newBook);
		if (sr.msg != null) {
			redirectAttributes.addFlashAttribute("error", sr.msg);
			response = "redirect:/manager/add";
		}
		return response;
	}

	@RequestMapping(value = "/edit/{bookIsbn}/{bookTitle}", method = RequestMethod.GET)
	public ModelAndView editBook(@PathVariable Map<String, String> pathVars, @ModelAttribute("error") String errorMsg) {
		ModelAndView model = new ModelAndView("manager/editBook");
		model.addObject("book",
				bookService.searchBooks("Default", pathVars.get("bookIsbn"), pathVars.get("bookTitle")).get(0));
		if (errorMsg != null)
			model.addObject("edittingBookErrorMsg", errorMsg);
		return model;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute("book") BookInfo newBook, RedirectAttributes redirectAttributes) {
		String response = "redirect:/manager";
		System.out.println(newBook);
		SqlResult sr = bookService.editBook(newBook);
		if (sr.msg != null) {
			redirectAttributes.addFlashAttribute("error", sr.msg);
			response = "redirect:/manager/edit/" + newBook.getIsbn() + "/" + newBook.getTitle();
		}
		return response;
	}

	/*
	 *********************** USER MANAGEMENT
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView listUsers() {
		ModelAndView model = new ModelAndView("manager/UserList");
		model.addObject("userList", userService.listUsers());
		return model;
	}

	@RequestMapping(value = "/editUser/{userName}", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable String userName) throws SQLException {
		ModelAndView model = new ModelAndView("manager/UserList");
		UserInfo user = userService.getUser(userName);
		if (user.getRole().equals("CUSTOMER"))
			userService.giveAdminPrivileges(user);
		else
			userService.giveCustomerPrivileges(user);
		System.out.println("NEW ROLL_______" + user.getRole());

		model.addObject("userList", userService.listUsers());
		return model;
	}

	/*
	 *********************** Order MANAGEMENT
	 */

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView listOrders() {
		ModelAndView model = new ModelAndView("manager/OrderList");
		model.addObject("orderList", orderService.listOrders());
		return model;
	}

	@RequestMapping(value = "/confirmOrder/{ISBN}/{Title}", method = RequestMethod.GET)
	public ModelAndView confirmOrder(@PathVariable String ISBN, @PathVariable String Title) {
		ModelAndView model = new ModelAndView("manager/OrderList");
		orderService.confirmOrder(ISBN, Title);
		model.addObject("orderList", orderService.listOrders());
		return model;
	}
}
