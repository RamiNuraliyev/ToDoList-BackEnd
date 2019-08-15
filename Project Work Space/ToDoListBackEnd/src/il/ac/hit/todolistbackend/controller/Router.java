package il.ac.hit.todolistbackend.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Router
 * Forward each request to its controller
 */
@WebServlet("/router/*")
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String pkg = "il.ac.hit.todolistbackend.controller";   
    
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Router() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String str = request.getPathInfo();
		String vec[] = str.split("/");
		String controller = vec[1];
		String action = vec[2];

		
		// Composing the controller class name
		String controllerClassName = controller.substring(0,1).toUpperCase()+controller.substring(1);
		try {
			Class clazz = Class.forName(pkg+"."+controllerClassName);
			Object object = clazz.newInstance();
			Method method = clazz.getMethod(action,HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(object,request,response);
			getServletContext().getRequestDispatcher("/"+action+".jsp").forward(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//sending the user to error message screen
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
