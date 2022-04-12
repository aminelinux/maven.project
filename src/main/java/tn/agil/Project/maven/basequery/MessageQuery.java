package tn.agil.Project.maven.basequery;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageQuery {
	private int id_user;
	private String sql;
	private Connection connection;
	private Statement stmt;
	private ResultSet rs;
	public MessageQuery(int id_user) {
		this.id_user = id_user;
	}
	/**
	 * Connection to the base 
	 * 
	 */
	private void connecte() {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basechat", "root", "");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * return statement having the a blob of friend request
	 * @return the data containing friends list
	 * @throws SQLException
	 */
	private ResultSet getMessageD() throws SQLException {
		stmt = connection.createStatement();
		sql = "SELECT dajout FROM listeami where nbr_user = '"+id_user +"'";
		rs = stmt.executeQuery(sql);
		return rs;
	}
	/**
	 * get a Blob from MySQL and transform it to string
	 * 
	 * @param res resultSet as Blob from liste amie
	 * @return
	 * @throws SQLException
	 */
	private String DecryptMessage(ResultSet res) throws SQLException {
		
		Blob readBlob = res.getBlob(3);
		byte[] bytes = readBlob.getBytes(1, (int) readBlob.length());
		String str = new String(bytes);
		System.out.println("Decryptmessage");
		return str;
		
	}
	
	/**
	 * set a the new Blob request d'ajout
	 * 
	 * @param str new add msg to blob request d'ajout
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	public int EncryptMessage(String str) throws UnsupportedEncodingException, SQLException{
		byte[] bytes = str.getBytes("UTF-8");
		Blob blobData = connection.createBlob();
		blobData.setBytes((int) blobData.length(), bytes);
		
		String sql = "INSERT INTO listeami ('dajout') VALUES ('" + blobData + "')";
		PreparedStatement pat = connection.prepareStatement(sql);
		int res = pat.executeUpdate();
		return res;
	}
	
	
//	private ResultSet getMsgText() {
//	stmt = connection.createStatement();
//	sql = "SELECT dajout FROM listeami where nbr_user = '"+  +"'";
//	rs = stmt.executeQuery(sql);
//	return rs;
//}
}
