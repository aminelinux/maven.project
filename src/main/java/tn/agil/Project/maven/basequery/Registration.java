package tn.agil.Project.maven.basequery;

import java.io.Reader;
import java.net.Proxy.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Types;

public class Registration {
	
	private String nom;
	private String prenom;
	private LocalDate localDate;
	private String login;
	private String pass;
	private String email;
	private Connection connection;
	private String sql = "";
	private Statement stmt;
	private int rs;
	private int idn;
	
	public Registration() {
		
	}
	/**
	 * Constructor with parameter
	 * 
	 * @param nom
	 * @param prenom
	 * @param localDate
	 * @param login
	 * @param pass
	 * @param email
	 */
	
	public Registration(String nom,String prenom,LocalDate localDate,String login,String pass,String email) {
			this.login = login;
			this.nom = nom;
			this.prenom = prenom;
			this.localDate = localDate;
			this.pass = pass;
			this.email = email;
		}
	/**
	 * connect to server 
	 */
	private void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basechat", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * try to insert new user if  insertion done create an empty liste ami user
	 * if not 
	 * @return return if the new utilisatuer is inserted properly into  database
	 * @throws SQLException get the msg error
	 */
	public boolean insert() throws SQLException {
		
		connect();
		rs = insertUt();
		System.out.println("rs Done " +  rs);
		System.out.println("insert Done ?" + insertDone(rs)) ;
		if(insertDone(rs)) {
			System.out.println(idRegister());
			FriendsList f = new FriendsList(idRegister());
			
		}
		connection.close();
		return insertDone(rs);
	}
	public boolean insertDone(int rs) {
		if(rs==1)
			return true;
		else
			return false;
	}
	/**
	 * insert a new user to utilisateur table 
	 * 
	 * @return if update is done or no
	 */
	public int insertUt() {
		stmt = null;
		try {
			stmt = connection.createStatement();
			System.out.println(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="INSERT INTO utilisateur (nom_ut,prenom_ut,login_ut,pass_ut,email_ut) values ('"+ nom + "','" + prenom +"','" + login +"','" + pass +"','" +email+"')";
		try {
			int rs = stmt.executeUpdate(sql);
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	/**
	 * get the id of the user who connected right now
	 * @return id of the user
	 * @throws SQLException
	 */
	private int idRegister() throws SQLException {
		ResultSet solution = null;
		int value = 0;
		
			stmt = connection.createStatement();
		
			sql="SELECT id FROM utilisateur where login_ut = '"+login+"'";
			
				solution = stmt.executeQuery(sql);
			
			
				System.out.println(" this the solution" + solution.toString());
				if(solution.next()) {
					int type = solution.getType();
					System.out.println(type);
					
					
				          value = solution.getInt("id") ;
				          System.out.println("Solution value   " + value);
					
				}
				//idn = Integer.parseInt(r.toString());
				System.out.println(value);
			
		
		return value;
	}
}
