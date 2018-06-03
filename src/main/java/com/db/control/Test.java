package com.db.control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.db.model.Book;
import com.db.model.SearchQuery;
import com.db.model.User;

@Controller
public class Test {

	/* DONE:
	 * search
	 * login
	 * logout
	 * sign up
	 * last video finished is #13 from https://www.youtube.com/watch?v=ZEz_0V8EJpM&list=PLBgMUB7xGcO31B2gBmy1igpZn6LK78-CJ&index=12
	 * TODO
	 * the remaining functions of the system.
	 * edit search cause it loads the page from the beginning make it only updates the table to keep the cart.
	 * use redirecting e.g. "redirect:/login" and divide the control into pieces.
	 * */
	DriverManagerDataSource ds;
	Connection con;
	User admin;
	
	int i = 0;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView LoginPageGET() throws SQLException {
		System.out.println("ASdfasdfasdfaSDF " + i++);
		System.out.println("ASdfasdfasdfaSDF " + i++);
		ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/BookStore");
		ModelAndView model = new ModelAndView("login/signin");
		admin = new User();
		admin.setUsername("root");
		admin.setPassword("admin");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView LoginPagePost(@ModelAttribute("user") User user) {
		ModelAndView model;
		try {
			System.out.println(user.getUsername());
			System.out.println(admin.getUsername());
			if (user.getUsername().equals(admin.getUsername()) && user.getPassword().equals(admin.getPassword())) {
				con = ds.getConnection(admin.getUsername(), admin.getPassword());
				model = new ModelAndView("manager/Manager");
			} else {
				con = ds.getConnection(user.getUsername(), user.getPassword());
				ResultSet rs = con.prepareStatement("select * from Book;").executeQuery();
				List<Book> books = new ArrayList<Book>();
				while (rs.next()) {
					books.add(new Book(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5),
							rs.getInt(6), rs.getString(8)));
				}
				System.out.println(books.toString());
				model = new ModelAndView("user/BookList");
				model.addObject("bookList", books);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model = new ModelAndView("login/signin");
			model.addObject("errorMessage", "invalid username or password please try again or sign up.");
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ModelAndView signOutPost(@ModelAttribute("user") User user) {
		ModelAndView model;
		try {
			con = ds.getConnection(admin.getUsername(), admin.getPassword());
			Statement s;
			s = con.createStatement();
			s.execute(
					"CREATE USER '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.executeQuery("GRANT SELECT ON BookStore.Book TO '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '"
					+ user.getPassword() + "';");
			s.executeQuery("GRANT Update ON BookStore.Users TO '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '"
					+ user.getPassword() + "';");
			s.execute("flush privileges;");
			s.execute("insert into Users values ('" + user.getEmail() + "','" + user.getUsername() + "','"
					+ user.getLastname() + "','" + user.getFirstname() + "','" + user.getAddress() + "','"
					+ user.getPhonenumber() + "');");
			con.close();
			con = ds.getConnection(user.getUsername(), user.getPassword());
			ResultSet rs = con.prepareStatement("select * from Book;").executeQuery();
			List<Book> books = new ArrayList<Book>();
			while (rs.next()) {
				books.add(new Book(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getString(8)));
			}
			model = new ModelAndView("user/BookList");
			model.addObject("bookList", books);
		} catch (SQLException e) {
			model = new ModelAndView("signin");
			model.addObject("signUpErrorMessage", "user name already exists please choose another one");
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/logout")
	public String logOutPOST() throws SQLException {
		if (!con.isClosed())
			con.close();
		/* TODO remove books in cart. */
		return "redirect:/login";
	}

	@RequestMapping(value = "/search")
	public ModelAndView search(@ModelAttribute("searchQuery") SearchQuery searchQuery) throws SQLException {
		ResultSet rs = callProcedure(con, searchQuery.getAttribute() + "_Book_Search", searchQuery.getText());
		List<Book> books = new ArrayList<Book>();
		while (rs.next()) {
			books.add(new Book(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5),
					rs.getInt(6), rs.getString(8)));
		}
		System.out.println(books.toString());
		ModelAndView model = new ModelAndView("user/BookList");
		model.addObject("bookList", books);
		return model;
	}

	public ResultSet callProcedure(Connection connection, String procName, Object... args) {
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
