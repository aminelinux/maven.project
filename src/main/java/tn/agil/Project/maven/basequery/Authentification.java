package tn.agil.Project.maven.basequery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentification {
	
	protected static int idUt;
	private String userName;
	private String userPass;
	private boolean status = false;
	
	private Statement stmt;
	private Connection connection;
	private ResultSet sc;
	private String sql="";
	
	
	/**
	 * Constructor Authentifiaction
	 * @param name
	 * @param pass
	 * @throws ClassNotFoundException
	 */
	public Authentification(String name,String pass) throws ClassNotFoundException {
		userName = name;
		userPass = pass;
		connecte();
	}
	/**
	 * Constructor For exit
	 * @param id
	 * @throws SQLException 
	 */
	public Authentification(int id) throws SQLException {
		switchDispoToOff(id);
	}
	
	/**
	 * connection to the base using jdbc getConnection
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
	 * Connection to the data base 
	 * @throws ClassNotFoundException
	 */
	private void connecte() throws ClassNotFoundException {
		
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			connect();
			stmt = connection.createStatement();
			sql="Select * from utilisateur where login_ut='" + userName + "' and pass_ut='" + userPass + "'";
			sc = stmt.executeQuery(sql);
			System.out.println(sc);
			
			if(sc.next()) {
				status = true;
				switchDispoToOn();
			}
			else 
				status = false;
			idUtulisateur();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	/**
	 * get back if the conneciion is done coorectly or not 
	 * 
	 * @return true:connected false:error connection 
	 */
	
		public boolean state() {
			if(status)
				return true;
			else
				return false;
		}
		/**
		 * 
		 * get the id of user connected to the base
		 * @return
		 * @throws SQLException
		 */
		public int idUT() throws SQLException {
			ResultSet rs = this.sc;
			return Integer.parseInt(rs.getString(1));
		}
		
		/**
		 * get the id utilisateur form a resultset and traoform it to a static int
		 * @throws SQLException 
		 */
		private void idUtulisateur() throws SQLException {
			idUt=idUT();
		}
		/**
		 * set the disponibilite to on 1:true
		 * @throws SQLException
		 */
		private void switchDispoToOn() throws SQLException {
			connect();
			final String sql = "update utilisateur set dispo='"+1+"'where id='"+idUT()+"'";
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		}
		/**
		 * set the disponibilite to 0:false
		 * @param id user id
		 * @throws SQLException
		 */
		private void switchDispoToOff(int id) throws SQLException {
			connect();
			final String sql = "update utilisateur set dispo='"+0+"'where id='"+id+"'";
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		}

	
}
