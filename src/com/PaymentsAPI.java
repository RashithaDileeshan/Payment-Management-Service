package com;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.spi.dispatch.RequestDispatcher;

import model.Payments;

/**
 * Servlet implementation class PaymentsAPI
 */
@WebServlet("/PaymentsAPI")
public class PaymentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PaymentService paymentService = new PaymentService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
			String output = paymentService.insertPayments(request.getParameter("patientName"),
							request.getParameter("amount"),
							request.getParameter("paydate"),
							request.getParameter("address"),
							request.getParameter("contactNo"),
							request.getParameter("email"));
			
							response.getWriter().write(output);

}
	
	//Convert request parameter to a map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
		    scanner.close();
		    String[] params = queryString.split("&");
		 for (String param : params)
		 { 
			 String[] p = param.split("=");
			 map.put(p[0], p[1]);
		  	}
			}
			catch (Exception e)
			{
			}
			return map;
		}

		
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> param = getParasMap(request);
		
		String output = paymentService.updatePayment(param.get("hidPaymentIdSave").toString(),
				param.get("patientName").toString().replace("+", ""),
				param.get("amount").toString(),
				param.get("paydate").toString(),
				param.get("address").toString().replace("+", ""),
				param.get("contactNo").toString(),
				param.get("email").toString().replace("%40", "@"));
				
				response.getWriter().write(output);
}
		
		
		
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
			Map paras = getParasMap(request);
			String output = paymentService.deletepayment(paras.get("paymentId").toString());
			response.getWriter().write(output); 
	}
	
	
}
