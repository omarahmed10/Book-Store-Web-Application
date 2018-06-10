package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db.controller.LoginController;
import com.db.model.BookInfo;
import com.db.model.SqlResult;
import com.db.model.UserInfo;
import com.db.service.SqlQueryService;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	SqlQueryService sqlService;

	public List<UserInfo> listUsers() {
		ResultSet rs = sqlService.callProcedure(LoginController.con, "List_Users").rs;
		List<UserInfo> users = new LinkedList<UserInfo>();
		try {
			while (rs.next()) {
				UserInfo user = new UserInfo();
				user.setUsername(rs.getString("User_Name"));
				if (user.getUsername().equals(LoginController.admin.getUsername()))
					continue;
				user.setEmail(rs.getString("User_email"));
				user.setAddress(rs.getString("User_address"));
				user.setFirstname(rs.getString("User_FirstName"));
				user.setLastname(rs.getString("User_LastName"));
				user.setPhonenumber(rs.getString("User_phoneNumber"));
				user.setRole(rs.getString("User_Role"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void editUser(UserInfo user) {
		int x;
		try {
			x = LoginController.con
					.prepareStatement("update Users set User_LastName = '" + user.getLastname()
							+ "', User_FirstName = '" + user.getFirstname() + "' , User_email = '" + user.getEmail()
							+ "' , User_address = '" + user.getAddress() + "' , User_phoneNumber = '"
							+ user.getPhonenumber() + "' where User_Name = '" + user.getUsername() + "';")
					.executeUpdate();
			System.out.println(user + " ___________UPDATED " + x);
			LoginController.con.prepareStatement("SET PASSWORD = PASSWORD('" + user.getPassword() + "');")
					.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addNewUser(UserInfo user) {
		Statement s;
		try {
			s = LoginController.con.createStatement();
			s.execute(
					"CREATE USER '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.execute("flush privileges;");
			s.execute("insert into Users values ('" + user.getEmail() + "','" + user.getUsername() + "','"
					+ user.getLastname() + "','" + user.getFirstname() + "','" + user.getAddress() + "','"
					+ user.getPhonenumber() + "');");
			if (user.getRole().equals("CUSTOMER")) {
				giveCustomerPrivileges(user);
				System.out.println("customer priv for ___________" + user.getUsername());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void giveCustomerPrivileges(UserInfo user) {
		Statement s;
		try {
			user.setRole("CUSTOMER");
			s = LoginController.con.createStatement();
			// deleting Admin privileges.
			s.executeQuery("REVOKE ALL ON *.* FROM '" + user.getUsername() + "'@'localhost';");
			s.execute("insert into Users_Role values('" + user.getUsername() + "','" + user.getRole()
					+ "') ON DUPLICATE KEY UPDATE User_Role = '" + user.getRole() + "';");
			s.executeQuery("GRANT SELECT ON BookStore.Book TO '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '"
					+ user.getPassword() + "';");
			s.executeQuery("GRANT SELECT ON BookStore.Users TO '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '"
					+ user.getPassword() + "';");
			s.executeQuery("GRANT Update ON BookStore.Users TO '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '"
					+ user.getPassword() + "';");
			s.executeQuery("GRANT Update ON BookStore.Cart TO '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '"
					+ user.getPassword() + "';");
			s.executeQuery("GRANT SELECT ON BookStore.Cart TO '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '"
					+ user.getPassword() + "';");
			s.executeQuery("GRANT SELECT ON mysql.user TO '" + user.getUsername() + "'@'localhost' IDENTIFIED BY '"
					+ user.getPassword() + "';");
			// GRANT EXECUTE ON PROCEDURE
			s.executeQuery("GRANT EXECUTE ON PROCEDURE BookStore.List_Books TO '" + user.getUsername()
					+ "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.executeQuery("GRANT EXECUTE ON PROCEDURE BookStore.get_User TO '" + user.getUsername()
					+ "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.executeQuery("GRANT EXECUTE ON PROCEDURE BookStore.Default_Book_Search TO '" + user.getUsername()
					+ "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.executeQuery("GRANT EXECUTE ON PROCEDURE BookStore.Title_Book_Search TO '" + user.getUsername()
					+ "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.executeQuery("GRANT EXECUTE ON PROCEDURE BookStore.Publisher_Book_Search TO '" + user.getUsername()
					+ "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.executeQuery("GRANT EXECUTE ON PROCEDURE BookStore.Category_Book_Search TO '" + user.getUsername()
					+ "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.executeQuery("GRANT EXECUTE ON PROCEDURE BookStore.Author_Book_Search TO '" + user.getUsername()
					+ "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.executeQuery("GRANT EXECUTE ON PROCEDURE BookStore.Add_To_Cart TO '" + user.getUsername()
			+ "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.executeQuery("GRANT EXECUTE ON PROCEDURE BookStore.Get_User_Cart TO '" + user.getUsername()
			+ "'@'localhost' IDENTIFIED BY '" + user.getPassword() + "';");
			s.execute("flush privileges;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void giveAdminPrivileges(UserInfo user) {
		Statement s;
		try {
			user.setRole("MANAGER");
			System.out.println("Grant __________" + user.getRole());
			s = LoginController.con.createStatement();
			s.execute("insert into Users_Role values('" + user.getUsername() + "','" + user.getRole()
					+ "') ON DUPLICATE KEY UPDATE User_Role = '" + user.getRole() + "';");
			s.executeQuery(
					"GRANT ALL PRIVILEGES ON *.* TO '" + user.getUsername() + "'@'localhost' WITH GRANT OPTION;");
			s.execute("flush privileges;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public UserInfo getCurrentUserInfo() {
		UserInfo user = new UserInfo();
		try {
			String username = LoginController.con.getMetaData().getUserName();
			username = username.substring(0, username.indexOf('@'));
			System.out.println("INFO__________________" + username);
			user = getUser(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public UserInfo getUser(String userName) throws SQLException {
		UserInfo user = new UserInfo();
		ResultSet rs = sqlService.callProcedure(LoginController.con, "get_User", userName).rs;
		if (rs.next()) {
			user.setEmail(rs.getString("User_email"));
			user.setAddress(rs.getString("User_address"));
			user.setFirstname(rs.getString("User_FirstName"));
			user.setLastname(rs.getString("User_LastName"));
			user.setPhonenumber(rs.getString("User_phoneNumber"));
			user.setRole(rs.getString("User_Role"));
			user.setUsername(userName);
			rs = LoginController.con
					.prepareStatement("select authentication_string from mysql.user where user = '" + userName + "';")
					.executeQuery();
			if (rs.next())
				user.setPassword(rs.getString(1));
		}
		return user;
	}
}
