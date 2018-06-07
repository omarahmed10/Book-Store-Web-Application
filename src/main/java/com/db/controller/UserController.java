package com.db.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.db.dao.BookDao;
import com.db.model.BookInfo;
import com.db.model.SearchQuery;
import com.db.model.UserInfo;
import com.db.service.BookService;

@Controller
@RequestMapping("/user")
public class UserController {

	Connection con;

	@Autowired
	BookService bookService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String init(@ModelAttribute("con") Connection con, RedirectAttributes redirectAttributes) {
		try {
			System.out.println("TESTING _____________" + con.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.con = con;
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listBooks() {
		ModelAndView model = new ModelAndView();
		model.setViewName("user/BookList");
		model.addObject("bookList", bookService.listBooks());
		return model;
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView editAccountGET() {
		ModelAndView model = new ModelAndView("user/Account");
		UserInfo user = getUserInfo();
		model.addObject("user", user);
		return model;
	}

	private UserInfo getUserInfo() {
		UserInfo user = null;
		try {
			String username = con.getMetaData().getUserName();
			username = username.substring(0, username.indexOf('@'));
			System.out.println("INFO__________________" + username);
			ResultSet rs = con.prepareStatement("select * from Users where User_Name = '" + username + "';")
					.executeQuery();
			if (rs.next()) {
				user = new UserInfo();
				user.setEmail(rs.getString(1));
				user.setFirstname(rs.getString(4));
				user.setLastname(rs.getString(3));
				user.setAddress(rs.getString(5));
				user.setPhonenumber(rs.getString(6));
				user.setUsername(username);
				rs = con.prepareStatement(
						"select authentication_string from mysql.user where user = '" + username + "';").executeQuery();
				if (rs.next())
					user.setPassword(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editAccountPOST(@ModelAttribute("user") UserInfo user) {
		try {
			int x = con
					.prepareStatement("update Users set User_LastName = '" + user.getLastname()
							+ "', User_FirstName = '" + user.getFirstname() + "' , User_email = '" + user.getEmail()
							+ "' , User_address = '" + user.getAddress() + "' , User_phoneNumber = '"
							+ user.getPhonenumber() + "' where User_Name = '" + user.getUsername() + "';")
					.executeUpdate();
			System.out.println(user + " ___________UPDATED " + x);
			con.prepareStatement("SET PASSWORD = PASSWORD('" + user.getPassword() + "');").executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@ModelAttribute("searchQuery") SearchQuery searchQuery) throws SQLException {
		ResultSet rs = callProcedure(con, searchQuery.getAttribute() + "_Book_Search", searchQuery.getText());
		List<BookInfo> books = new ArrayList<BookInfo>();
		while (rs.next()) {
			books.add(new BookInfo(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5),
					rs.getInt(6), rs.getString(8)));
		}
		System.out.println(books.toString());
		ModelAndView model = new ModelAndView("user/BookList");
		model.addObject("bookList", books);
		return model;
	}

	private ResultSet callProcedure(Connection connection, String procName, Object... args) {
		ResultSet resultSet = null;
		try {
			// build the call upon the passed arguments
			String call = "{call " + procName + "(";
			if (args.length > 0) {
				call += "?";
				for (int i = 1; i < args.length; i++) {
					call += ",?";
				}
			}
			call += ")}";

			// Prepare a call to the stored procedure
			CallableStatement callStatment = connection.prepareCall(call);

			// pass the arguments after casting their types
			for (int i = 0; i < args.length; i++) {
				int argType = callStatment.getParameterMetaData().getParameterType(i + 1);

				if (argType == Types.VARCHAR)
					callStatment.setString(i + 1, args[i].toString());
				else if (argType == Types.INTEGER)
					callStatment.setInt(i + 1, (Integer) args[i]);
				else if (argType == Types.DATE)
					callStatment.setDate(i + 1, (Date) args[i]);
			}

			// the boolean returned , true if the query returns result set
			callStatment.execute();
			// update result set and update count
			resultSet = callStatment.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
}
