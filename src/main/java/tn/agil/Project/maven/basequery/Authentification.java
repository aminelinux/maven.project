package tn.agil.Project.maven.basequery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentification {
	
	private String userName;
	private String userPass;
	private Statement stmt;
	Connection connection;
	String sql="";
	ResultSet sc;
	private boolean status = false;
	
	public Authentification(String name,String pass) throws ClassNotFoundException {
		userName = name;
		userPass = pass;
		connecte();
	}
	private void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basechat", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private void connecte() throws ClassNotFoundException {
		
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basechat", "root", "");
			//System.out.println(connection.getAutoCommit());
			connect();
			stmt = connection.createStatement();
			sql="Select * from utilisateur where login_ut='" + userName + "' and pass_ut='" + userPass + "'";
			sc = stmt.executeQuery(sql);
			System.out.println(sc);
			
			if(sc.next()) 
				status = true;
			else 
				status = false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
		public boolean state() {
			if(status)
				return true;
			else
				return false;
		}
		public int idUT() throws SQLException {
			ResultSet rs = this.sc;
			return Integer.parseInt(rs.getString(1));
		}
	
}
