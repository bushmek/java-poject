package servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.User;


/**
 * Servlet implementation class Login
 */

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		try {
			User user=authorization(login,pass);
			if (user.getLogin()==null || !user.getLogin().equals(login)) {
				session.setAttribute("msg", "Не вірно введені дані");
			}
			else 
				session.setAttribute("msg", "Привіт "+user.getName());
				
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String path = request.getContextPath() + "/login.jsp";
		response.sendRedirect(path);
	}

	private User authorization(String login,String pass) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Connection conn=null;
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bycerent?serverTimezone=Europe/Moscow&useSSL=false", "root", "root");
			System.out.print("succ");
			User user = new User();
			String sql = "SELECT * FROM users where login = '"+login+"' AND pass = '"+pass+"'";
			Statement statement = conn.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        while(resultSet.next()){
	             login = resultSet.getString(2);
	             pass = resultSet.getString(3);
	             String name = resultSet.getString(4);
	             user = new User(login,pass,name);
	         	 }
	        return user;	
			
	}
	
}
