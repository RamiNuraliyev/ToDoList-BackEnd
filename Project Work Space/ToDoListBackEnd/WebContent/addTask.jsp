<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page errorPage="taskError.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>New task</title>
</head>
<body>
<form action="addTask" method="get">
<br> ID  <input type = "text" name="taskId"/>
<br> Name<input type = "text" name="taskName"/>
<br> <input type = "submit" value="Add"/>
</form>
<% 
 if(request.getParameter("taskId") != null && request.getParameter("taskName") != null)
{
	 int id;
	try{
		 id = Integer.parseInt(request.getParameter("taskId"));
	}
	catch(NumberFormatException e)
	{
		throw new ServletException("Task id should be integer",e);
	}
	if(id < 0 )
	{
		throw new ServletException("Task id should be greater then 0");
	}
	else
	{
		RequestDispatcher dispatcher = null;
		dispatcher = application.getRequestDispatcher("/router/UserController/tasksList");
		dispatcher.forward(request, response);
	}

} 

%>
</body>
</html>