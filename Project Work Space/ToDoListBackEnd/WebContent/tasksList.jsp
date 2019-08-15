<%@page import="java.util.Iterator"%>
<%@ page language="java" import = "il.ac.hit.todolistbackend.controller.*" import="java.util.List" import = "il.ac.hit.todolistbackend.model.*" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>Tasks Page</title>
</head>
<body> 
<br/>Task List </br>
<%
	int currId = (int)(session.getAttribute("currentUserId"));
	out.print("<br>Your id =" +currId +"</br>");
	List<Task> tasks = (List<Task>)request.getAttribute("taskList");
	out.print("<br>All tasks =" +tasks.size() +"</br>");
	
	out.print("<table border= 1>");
	out.print("<tr><td>ID</td><td>To Do</td></tr>");
	for(Task task : tasks)
	{
		if(task.getUserId() == currId)
		{
			//out.print("<br>"+"id = "+task.getId()+"name = "+task.getName()+"</br>");
			out.print("<tr><td>"+task.getId()+"</td><td>"+task.getName()+"</td></tr>");

			
		}
	}
	out.print("</table>");
 	
%>

<a href="addTask">Add</a>
<a href="deleteTask">Delete</a>
<a href="login"><br/>Change user</br></a>


</body>
</html>