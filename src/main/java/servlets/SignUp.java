package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String login=request.getParameter("login");
		String pass=request.getParameter("pass");
		String repeatPass = request.getParameter("repeatPass");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		session.setAttribute("msg", "");
		if(pass.equals(repeatPass)) {
			register(login,pass,name,email);
			session.setAttribute("msg", "Successfully");
		}
		else 
			session.setAttribute("msg", "Pass is incorect");
			
		String path = request.getContextPath() + "/signup.jsp";
		response.sendRedirect(path);
	}

	private void register(String login, String pass,String name,String email) {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bycerent?serverTimezone=Europe/Moscow&useSSL=false", "root", "root");
			System.out.print("succ");
			String sql = "INSERT INTO users (login, pass, name, email) Values (?, ?, ?,?)";
			try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
					preparedStatement.setString(1, login);
					preparedStatement.setString(2, pass);
					preparedStatement.setString(3, name);
					preparedStatement.setString(4, email);
					preparedStatement.executeUpdate();
					}
			}
			catch(Exception ex) {
				System.out.print("err"+ex);
			}
	}
	
}
