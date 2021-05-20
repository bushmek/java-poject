package server;

import java.io.Console;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Worker;
import clases.Bicycle;


//one thread

public class OneThreadServer {

	static String url="jdbc:mysql://127.0.0.1:3306/bycerent";
	static String user="";
	static String pass="";
	private static ObjectInputStream in;
	private static ObjectOutputStream  out;
	private static Connection conn;
	
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(2222);
			socket = serverSocket.accept();
			in =new ObjectInputStream(
	                   socket.getInputStream());

	        out = new ObjectOutputStream(
	                   socket.getOutputStream());
	        
	        while(true){
	        	Object command=in.readObject();
	        	System.out.println(command);
	        	if(command.equals("login")) {
	        		user=(String)in.readObject();
	        		pass=(String)in.readObject();
	        		Worker worker = connect(url,user,pass);
	        		out.writeObject(worker);
	        	}
	        	if(command.equals("select")) {
	        		 String filial=(String)in.readObject();
	        		 ArrayList<Bicycle> q=select("SELECT * FROM all_bicycles WHERE filial = '"+filial+"'");
	        		 out.writeObject(q.size());
	        		 for(int i=0;i<q.size();i++) {
	        			 out.writeObject(q.get(i));
	        		 }
	        	}
	        	if(command.equals("rent")) {
	        		 String id=(String)in.readObject();
	        		 makeRent(id);
	        	}
	        	if(command.equals("return")) {
	        		 String id=(String)in.readObject();
	        		 makeReturn(id);
	        	}
	        }
	        
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			  try {
				in.close();
				out.close();
		        socket.close();
		        serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}	
	}

	
	static private Worker connect(String url,String login,String pass){
		conn = null;
		Worker worker = new Worker();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn=DriverManager.getConnection(url,"root","root");
			System.out.println("Connection to DB succesfull!\n");
			worker = new Worker();
			String sql = "SELECT * FROM workers where login = '"+login+"' AND pass = '"+pass+"'";
			Statement statement = conn.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        while(resultSet.next()){
	             login = resultSet.getString(1);
	             pass = resultSet.getString(2);
	             String name = resultSet.getString(3);
	             String phoneNumber = resultSet.getString(4);
	             String filial = resultSet.getString(5);
	             String permission = resultSet.getString(6);
	             worker = new Worker(login,pass,name,phoneNumber,filial,permission);
	         	 }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return worker;
	}
	
	public static ArrayList<Bicycle> select(String query) {
		 ArrayList<Bicycle> bicycles = new ArrayList<Bicycle>();
		 try {
		 if(conn!=null) {
		 Statement statement = conn.createStatement();
		 ResultSet resultSet = statement.executeQuery(query);
		 while(resultSet.next()){
           String id = resultSet.getString(1);
           String type = resultSet.getString(2);
           String coordynates = resultSet.getString(3);
           String filial = resultSet.getString(4);
           String status = resultSet.getString(5);
           Bicycle bicy= new Bicycle(id, type,coordynates,filial,status);
           bicycles.add(bicy);
       	 }
			}

       }catch(Exception ex) {
      	 
       }
		return bicycles;
		}
	
	private static void makeRent(String id) {
		if(conn!=null) {
			String sql = "UPDATE all_bicycles SET is_free = 1 WHERE id = ?";
			  try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
				preparedStatement.setString(1, id);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void makeReturn(String id) {
		if(conn!=null) {
			String sql = "UPDATE all_bicycles SET is_free = 0 WHERE id = ?";
			  try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
				preparedStatement.setString(1, id);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
