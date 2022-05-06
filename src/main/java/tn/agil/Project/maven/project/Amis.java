package tn.agil.Project.maven.project;

import java.sql.SQLException;
import tn.agil.Project.maven.basequery.FriendsList;

public class Amis {
		private FriendsList query;
		private String[] friends;
		private Boolean[] dispo;
		private int id;
		
		/**
		 * Constructor For class Amis
		 * @param id get id of user 
		 * @throws SQLException
		 */
		public Amis(int id) throws SQLException 
		{
			this.id = id;
			
			query = new FriendsList(id);
			
		}
		
		/**
		 * get a string of friends and transform it to array String of friends
		 * 
		 * @return a string of friends
		 * @throws SQLException
		 */
		public String[] getFriends() throws SQLException {
			String f =query.getFreinds();
			friends = f.split(";");
			return friends;
		}
		
		public Boolean[] getDispo() throws SQLException {
			int i = 0;
			dispo = new Boolean[friends.length];
			for(String f : friends) {
				if(query.getDisponibilite(f).equals("0"))
				dispo[i] = false ;
				else 
				dispo[i] = true;
				
				i++;
			}	
			return dispo;
		}
		
		
		
		
}
