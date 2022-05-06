package tn.agil.Project.maven.basequery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.text.Text;

public class FriendsList {
	private Connection connection;
	private Statement stmt;
	private String sql;
	private int id;
	private ResultSet sc;
	private String ami = "" ;
	private int nbr_user;
	
	public FriendsList(int id) throws SQLException{
		
		this.id = id;
		//this.initFreinds();
		
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
	
	private void connecte() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basechat", "root", "");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initFreinds() throws SQLException {
		connecte();
		nbr_user=id;
		System.out.println("add a friend id = " + id );
		stmt = connection.createStatement();	
		sql="INSERT INTO listeami (ami,nbr_user) values ('"+ ami + "','" + nbr_user + "')";
		stmt.executeUpdate(sql);
		
	}
	public String getFreinds() throws SQLException {
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
	
	
		
	
	
	
}
