package com.servlet.includeApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/fs")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		String pname = req.getParameter("nm");
		String pqty = req.getParameter("pq");
		
		//ADD DATA TO THE SCOPE
		req.setAttribute("prdnm", pname);
		req.setAttribute("prdqty", pqty);
		
		//Chaining
		RequestDispatcher rd = req.getRequestDispatcher("ss");
		rd.include(req, resp);
		
		//PRESENTATION LOGIC
		double totalsum = (double) req.getAttribute("prdtotal");
		
		PrintWriter out= resp.getWriter();
		out.println("<html><body><h1> the toatl sum is "+totalsum+" </h1></body></html>");
		
		out.flush();
		out.close();
		
		
	}
}
