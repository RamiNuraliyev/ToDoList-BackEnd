<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Delete task</title>
</head>
<body>
	<form action="deleteTask" method="get">
		<br> Delete ID <input type="text" name="taskId" /> <br> <input
			type="submit" value="Delete" />
	</form>
	<%
		if (request.getParameter("taskId") != null) {
			int id;
			try
			{
				id = Integer.parseInt(request.getParameter("taskId"));
			} 
			catch (NumberFormatException e)
			{
				throw new ServletException("Task id should be integer", e);
			}
			if (id < 0)
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