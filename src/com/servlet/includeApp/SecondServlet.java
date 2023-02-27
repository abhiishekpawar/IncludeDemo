package com.servlet.includeApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ss")
public class SecondServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		//GETTING THE DATA FROM THE SCOPE
		String pname = (String) req.getAttribute("prdnm");//downcast
		String pqty = (String) req.getAttribute("prdqty");
		int qty = Integer.parseInt(pqty);
		
		
		//BUISNESS LOGIC
		double price = 8500;
		double totalsum = price*qty;
		
		//AGIN ADD THE DATA TO THE SCOPE
		req.setAttribute("prdtotal", totalsum);
		
		//PERSISTENCE LOGIC
		Connection con = null;
		PreparedStatement pstmt = null;
		String iQry = "insert into pentagon.product values(?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstmt = con.prepareStatement(iQry);
			
			//SET VALUES BEFORE EXECUTING
			pstmt.setString(1, pname);
			pstmt.setInt(2, qty);
			pstmt.setDouble(3, totalsum);
			
			//NOW EXECUTE
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}if(pstmt!= null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
	}
}
