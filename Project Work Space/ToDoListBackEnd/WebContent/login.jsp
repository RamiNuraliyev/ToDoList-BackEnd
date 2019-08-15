<%@ page language="java" import="il.ac.hit.todolistbackend.controller.*"
	contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page errorPage= "errorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Login Page</title>
</head>
<body>
	<form action="router/UserController/login" method="get">
		<br> User ID <input type="text" name="userId" /> <br> User
		Name<input type="text" name="userName" /> <input type="submit"
			value="login/register" />
	</form>
	<%
		if (request.getAttribute("changeUser") == "true") {
			request.setAttribute("changeUser", "false");
			response.sendRedirect("http://localhost:8080/ToDoListBackEnd/login.jsp");
		} 
		else
		{
			if (request.getParameter("userId") == null) {
				response.getWriter();
				out.print("<br>UserId= null</br>");
			} else {
				if(Integer.parseInt(request.getParameter("userId")) < 0)
				{
					
					throw new ServletException("User id should be greater then 0");
				}
				else
				{
					response.getWriter();
					out.print("<br>currentUserId= " + request.getAttribute("currentUserId") + "</br>");
					RequestDispatcher dispatcher = null;
					dispatcher = application.getRequestDispatcher("/router/UserController/tasksList");
					dispatcher.forward(request, response);	
				}

			}
		}
	%>
</body>
</html>