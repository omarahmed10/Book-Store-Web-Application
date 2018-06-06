package com.db.controller;

import java.sql.Connection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	Connection con;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView init(@ModelAttribute("con") Connection con) {
		return new ModelAndView("manager/Manager");
	}
}
