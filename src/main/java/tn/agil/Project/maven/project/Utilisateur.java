package tn.agil.Project.maven.project;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Scanner;

import tn.agil.Project.maven.basequery.MessageQuery;

public class Utilisateur {
	public static int id_ut;
	public int ut_id;
	private Scanner sc;
	
	public void Ajouter_ami() throws UnsupportedEncodingException, SQLException {
		System.out.println("Donner l'id de l'utlisateur à ajouter");
		
		int id = sc.nextInt();
		String msgd = Integer.toString(id) + " " + Integer.toString(this.ut_id);
		MessageQuery msg = new MessageQuery(this.ut_id);
		msg.EncryptMessage(msgd);
		//System.out.println("ajouter_ami message apres concatination" + msgd);
		
		//System.out.println(msg.toString());
		//System.out.println(msg.size());		
	}
	
	
}
