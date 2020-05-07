package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PaymentsAPI")
public class PaymentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PaymentService paymentService = new PaymentService();
    
    public PaymentsAPI() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
			String output = paymentService.insertPayments(request.getParameter("patientName"),
							request.getParameter("amount"),
							request.getParameter("paymentdate"),
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
		
		Map paras = getParasMap(request);	
		
		
		String output = paymentService.updatePayment(paras.get("hidPaymentIdSave").toString(),
				paras.get("patientName").toString().replace("+", " "),
				paras.get("amount").toString(),
				paras.get("paymentdate").toString(),
				paras.get("address").toString().replace("+", " "),
				paras.get("contactNo").toString(),
				paras.get("email").toString().replace("%40", "@"));
				
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
