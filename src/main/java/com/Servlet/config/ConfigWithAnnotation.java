package com.Servlet.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Name;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfigWithAnnotation
 */
@WebServlet( urlPatterns = {"/ConfigWithAnnotation"} 
,initParams = {
		@WebInitParam(name="user",value="root"),
		@WebInitParam(name="pass",value="root")
		
})

public class ConfigWithAnnotation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con=null;
    
	public void init(ServletConfig config) {
		try {
			String user=config.getInitParameter("user");
			String pass=config.getInitParameter("pass");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PreparedStatement stmt=con.prepareStatement("select * from user");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				response.getWriter().print(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
