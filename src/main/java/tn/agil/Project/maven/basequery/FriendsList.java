package tn.agil.Project.maven.basequery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.text.Text;

public class FriendsList {
	
	private int nbr_user;
	private int id;
	private String ami = "" ;
	
	private Connection connection;
	private Statement stmt;
	private ResultSet sc;
	private String sql;
	
	
	
	
	
	public FriendsList(int id) throws SQLException{
		
		this.id = id;
		//this.initFreinds();
		
	}
	/**
	 * create a connection to the data base 
	 */
	private void connecte() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basechat", "root", "");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void close() throws SQLException {
		 if (isOpened ()){
	            connection.close ();
	            connection = null;
	        }
	}
	
	public boolean isOpened (){
        return connection != null;
    }
	
	/**
	 * 
	 * get a the friends of user by his present id 
	 * @return the friends as a well defined String (id;id;id;)
	 * @throws SQLException
	 */
	public String getFriends() throws SQLException {
		connecte();
		String description = null;
		final String sql = "SELECT ami FROM listeami where nbr_user ="+Integer.toString(id)+"";
		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			description = rs.getString("ami");
		        System.out.println("# " + description);
		    }
		    return description;
	}
	/**
	 * get the dispo of a user from the table utilisateur  by getting a string of (0 = false or 1=True)
	 * @param id User identificator
	 * @return 0,1 respectively for false or true
	 * @throws SQLException
	 */
	
	public String getDisponibilite(String id) throws SQLException {
		connecte();
		String description = null;
		final String sql = "SELECT dispo FROM utilisateur where id ="+id+"";
		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			description = rs.getString("dispo");
		        System.out.println("# " + description);
		    }
		    return description;
	}
	
	
	public void setFriends() {
		try {
			nbr_user = id;
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="INSERT INTO listeami (id_list,ami,nbr_user) values ('"+ ami + "','" + nbr_user + "')'";
		try {
			sc = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * @throws SQLException
	 */
	private void initFriends() throws SQLException {
		connecte();
		nbr_user=id;
		System.out.println("add a friend id = " + id );
		stmt = connection.createStatement();	
		sql="INSERT INTO listeami (ami,nbr_user) values ('"+ ami + "','" + nbr_user + "')";
		stmt.executeUpdate(sql);
		
	}
	
	
		
	
	
	
}
