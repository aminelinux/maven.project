package tn.agil.Project.maven.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentification {
	
	private String userName;
	private String userPass;
	Connection connection;
	String sql="";
	
	public Authentification(String name,String pass) throws ClassNotFoundException {
		userName = name;
		userPass = pass;
		connecte();
	}
		
	private void connecte() throws ClassNotFoundException {
		
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basechat", "root", "");
			//System.out.println(connection.getAutoCommit());	
			Statement stmt = connection.createStatement();
			sql="Select * from utilisateur where nom='" + userName + "' and userpass='" + userPass + "'";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs);
			if(rs.next()) {
				System.out.println("Biingo True");
			}else {
				System.out.println("Biingo False");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
