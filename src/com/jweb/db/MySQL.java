package com.jweb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jweb.beans.Client;


public class MySQL {

	private List<String> messages = new ArrayList<String>();

	public List<String> getClients() {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch ( ClassNotFoundException e ) {
	        messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
	                + e.getMessage() );
	        return messages;
	    }

	    String url = "jdbc:mysql://localhost:3306/JWeb";
	    String utilisateur = "java";
	    String motDePasse = "admin";
	    Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
	    
	    messages.clear();
	    try {
	        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

	        statement = connexion.createStatement();
	        resultat = statement.executeQuery("SELECT id, name, firstName, email FROM Clients;");
	 
	        while (resultat.next()) {
	            int idUtilisateur = resultat.getInt("id");
	            String name = resultat.getString("name");
	            String firstName = resultat.getString("firstName");
	            String email = resultat.getString("email");

	            messages.add("Client: id = " + idUtilisateur + ", name = " + name
	                    + ", first name = " + firstName + ", email = " + email);
	        }
	    } 
	    catch (SQLException e) {
	        messages.add("Erreur lors de la connexion : <br/>" + e.getMessage());
	    } 
	    finally {
	        if (resultat != null) {
	            try {
	                resultat.close();
	            }
	            catch (SQLException ignore) {
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } 
	            catch (SQLException ignore) {
	            }
	        }
	        if (connexion != null) {
	            try {
	                connexion.close();
	            } 
	            catch (SQLException ignore) {
	            }
	        }
	    }
	    return messages;
	}
	
	public boolean auth(Client c) {
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } 
		catch (ClassNotFoundException e) {
	        return false;
	    }

	    String url = "jdbc:mysql://localhost:3306/JWeb";
	    String utilisateur = "java";
	    String motDePasse = "admin";
	    Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
	    
	    try {
	        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
	        statement = connexion.createStatement();
	        resultat = statement.executeQuery("SELECT login, password FROM Admin;");
	 
	        while (resultat.next()) {
	            String login = resultat.getString("login");
	            String passwd = resultat.getString("password");
	            
	            if (login.equals(c.getName()) && passwd.equals(c.getEmail())) {
	            	return true;
	            }
	        }
	    } 
	    catch (SQLException e) {
	        messages.add("Erreur lors de la connexion : <br/>" + e.getMessage());
	        return false;
	    } 
	    finally {
	        if (resultat != null) {
	            try {
	                resultat.close();
	            }
	            catch (SQLException ignore) {
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } 
	            catch (SQLException ignore) {
	            }
	        }
	        if (connexion != null) {
	            try {
	                connexion.close();
	            } 
	            catch (SQLException ignore) {
	            }
	        }
	    }
		return false;
	}
}
