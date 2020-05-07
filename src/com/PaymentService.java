package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import database.DBConnection;
import model.Payments;

public class PaymentService {

	//add payment	
			public String insertPayments(String patientName, String amount,String paydate, String address,String contactNo,String email) {
				String output = "";
				try {
					Connection con = DBConnection.connect();
					if (con == null) {
						return "Error while connecting to the database for inserting.";
					}
					
					// create a prepared statement
					String query = " insert into payservice (`paymentId`,`patientName`,`amount`,`paydate`,`address`,`contactNo`,`email`)"
							+ " values (?, ?, ?, ?, ?,?,?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, patientName);
					preparedStmt.setString(3, amount);
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
					java.util.Date date1 = sdf1.parse(paydate);
					java.sql.Date sqlStartDate = new java.sql.Date(date1.getTime());
					preparedStmt.setDate(4, sqlStartDate);
					preparedStmt.setString(5, address);
					preparedStmt.setString(6, contactNo);
					preparedStmt.setString(7, email);
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					//Create JSON Object to show successful msg
					String newPayments = readPayments();
					output = "{\"status\":\"success\", \"data\": \"" +
					newPayments + "\"}";  
				
					 
				} catch (Exception e) {
					
					//Create JSON Object to show Error msg.
					output = "{\"status\":\"error\", \"data\": "
							+ "\"Error while inserting the Payment.\"}"; 	
					System.err.println(e.getMessage());
				
				}
				return output;
			}

			
//read all payments
			public String readPayments() {
				String output = "";
				try {
					Connection con = DBConnection.connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}
					// Prepare the html table to be displayed
					output = "<table border=\"1\"><tr><th>Patient Name</th><th>Amount</th><th>Pay"
							+ "Date</th><th>Address</th><th>Contact No</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from payservice";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next()) {
						String paymentId = Integer.toString(rs.getInt("paymentId"));
						String patientName = rs.getString("patientName");
						String amount = Long.toString(rs.getLong("amount"));
						String paydate = rs.getString("paydate");
						String address = rs.getString("address");
						String contactNo = rs.getString("contactNo");
						String email = rs.getString("email");
						
						// Add into the html table
						output += "<tr><td><input id='hidPaymentIDUpdate' name='hidPaymentIDUpdate' type='hidden' value='" + paymentId + "'>" + patientName + "</td>";
						output += "<td>" + amount + "</td>";
						output += "<td>" + paydate + "</td>";
						output += "<td>" + address + "</td>";
						output += "<td>" + contactNo + "</td>";
						output += "<td>" + email + "</td>";
						
						// buttons
//						output += "<td><input name=\"btnUpdate\" type=\"button\""
//								+ " value=\"Update\" class=\"btn btn-secondary\"></td>"
//								+ "<td><form method=\"post\" action=\"payment.jsp\">"
//								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"" + " class=\"btn btn-danger\">"
//								+ "<input name=\"hidPaymentIDelete\" type=\"hidden\" value=\"" + paymentId + "\">" + "</form></td></tr>";
						
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> "
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger'  data-itemid= '" + paymentId + "'></td></tr>";
					}
					con.close();
					
					// Complete the html table
					output += "</table>";
				} catch (Exception e) {
					output = "Error while reading the payment details";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
//update payment details	
			public String updatePayment(String paymentId,String patientName, String amount,String paydate, String address,String contactNo,String email) {
				
				String output = "";
				
				try {
					
					Connection con = DBConnection.connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					
					
					// create a prepared statement
					String query = "UPDATE payservice SET patientName=?,amount=?,paydate=?,address=?,contactNo=?,email=? " 
					+ "WHERE paymentId=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setString(1, patientName);
					preparedStmt.setString(2, amount);
					preparedStmt.setString(3, paydate);
					preparedStmt.setString(4, address);
					preparedStmt.setString(5, contactNo);
					preparedStmt.setString(6, email);
					preparedStmt.setString(7, paymentId);
				
					
					// execute the statement
					
					preparedStmt.execute();
					con.close();
					
					String newPayments = readPayments();
					output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}"; 
					
					//output = "Updated successfully";
					 
				} catch (Exception e) {
					
					output = "{\"status\":\"error\", \"data\":"
							+ " \"Error while updating the payments.\"}";
					
					System.err.println(e.getMessage()); 
				}
				return output;
			}

			
//delete payment details	
			public String deletepayment(String paymentId) {
				String output = "";
				try {
					Connection con = DBConnection.connect();
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}
					// create a prepared statement
					String query = "delete from payservice where paymentId = ?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					
					preparedStmt.setInt(1, Integer.parseInt(paymentId));
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newPayments = readPayments();
					output = "{\"status\":\"success\", \"data\": \"" + 
					newPayments + "\"}";
					}
					catch (Exception e)
					{
						output = "{\"status\":\"error\", \"data\":	"
								+ "\"Error while deleting the payments.\"}";
					 System.err.println(e.getMessage());
					 }
					 return output; 
			}

			
//Display single payment detail
			public static Payments getPaymentDetails(String id) {
				Payments payment = null;
				try {

					Connection con = DBConnection.connect();

					String getSql = "SELECT * FROM payservice WHERE paymentId = ? ";

					PreparedStatement ps_getPaymentDetails = con.prepareStatement(getSql);
					ps_getPaymentDetails.setInt(1, Integer.parseInt(id));

					ResultSet rs = ps_getPaymentDetails.executeQuery();

					while (rs.next()) {

						
						payment = new Payments(rs.getInt(Integer.parseInt(id)), rs.getString(2), rs.getLong(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7));
					
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				return payment;
			}
			
			
}
