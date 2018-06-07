package com.db.controller;

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
import com.db.model.SqlResult;
import com.db.service.BookService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	BookService bookService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView model = new ModelAndView("manager/Manager");
		model.addObject("bookList", bookService.listBooks());
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
		System.out.println(newBook.to_String());
		SqlResult sr = bookService.addNewBook(newBook);
		if (!sr.success)
			redirectAttributes.addFlashAttribute("error", sr.msg);
		return response;
	}

	@RequestMapping(value = "/edit/{bookIsbn}/{bookTitle}", method = RequestMethod.GET)
	public ModelAndView editBook(@PathVariable Map<String,String> bookInfo) {
		ModelAndView model = new ModelAndView("manage/editBook");
		model.addObject("book", bookService.getBook());
		return model;
	}
}
