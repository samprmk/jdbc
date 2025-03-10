<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
 String url = "jdbc:oracle:thin:@localhost:1521:xe";
 String user = "1mcab32";
 String password = "iii";
 Connection conn = null;
 Statement stmt = null;
 ResultSet rs = null;
 String message = "";

 try {
 Class.forName("oracle.jdbc.driver.OracleDriver");
 conn = DriverManager.getConnection(url, user, password);
 stmt = conn.createStatement();

 String action = request.getParameter("action");
 if ("insert".equals(action)) {
 String name = request.getParameter("name");
 String email = request.getParameter("email");
 String phone = request.getParameter("phone");
 stmt.executeUpdate("INSERT INTO users (name, email, phone)
VALUES ('" + name + "', '" + email + "', '" + phone + "')");
 message = "User added successfully.";
 } else if ("update".equals(action)) {
 int id = Integer.parseInt(request.getParameter("id"));
 String name = request.getParameter("name");

String email = request.getParameter("email");
 String phone = request.getParameter("phone");
 stmt.executeUpdate("UPDATE users SET name='" + name + "',
email='" + email + "', phone='" + phone + "' WHERE id=" + id);
 message = "User updated successfully.";
 } else if ("delete".equals(action)) {
 int id = Integer.parseInt(request.getParameter("id"));
 stmt.executeUpdate("DELETE FROM users WHERE id=" + id);
 message = "User deleted successfully.";
 }

 rs = stmt.executeQuery("SELECT * FROM users");
 } catch (Exception e) {
 message = "Error: " + e.getMessage();
 }
