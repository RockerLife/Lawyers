package com.manage.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.userbo.CreatePaymentIntent;

public class AndDoneResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timer timer;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndDoneResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		
		CreatePaymentIntent createPaymentIntent = new CreatePaymentIntent();
		
		String out = createPaymentIntent.getAndDoneResponse(session);
		PrintWriter pwr = response.getWriter();
		pwr.print(out);
	}
	
}
