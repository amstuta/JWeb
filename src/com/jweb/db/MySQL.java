package com.jweb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jweb.beans.Client;
import com.jweb.beans.Comment;
import com.jweb.beans.News;


public class MySQL {

	private List<String> messages = new ArrayList<String>();
	static private final String url = "jdbc:mysql://localhost:3306/JWeb";
    static private final String utilisateur = "java";
    static private final String motDePasse = "admin";

    
    public List<News> getLastNews() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	}
    	catch (ClassNotFoundException e) {
    		return null;
    	}
    	
    	List<News> result = new ArrayList<News>();
    	Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
    	
    	try {
	        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

	        statement = connexion.createStatement();
	        resultat = statement.executeQuery("SELECT title, body FROM News ORDER BY date DESC;");
	 
	        while (resultat.next()) {
	        	String title = resultat.getString("title");
	            String body = resultat.getString("body");

	            News tmp = new News();
	            tmp.setTitle(title);
	            tmp.setBody(body);
	            
	            result.add(tmp);
	        }
	    } 
	    catch (SQLException e) {
	    	return null;
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
	    return result;
	}
    
    public void registerComment(Comment c) {
    	try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } 
	    catch (ClassNotFoundException e) {
	        return;
	    }

	    Connection connexion = null;
	    Statement statement = null;
	    
	    try {
	        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
	        String query = "INSERT INTO Comments (name, comment) VALUES (\"" + c.getLogin()
	        		+ "\", \"" + c.getComment() + "\");";
	        
	        PreparedStatement preparedStmt = connexion.prepareStatement(query);
	        preparedStmt.execute();
	    } 
	    catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    } 
	    finally {
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
    }
    
    public void registerNews(News n) {
    	try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } 
	    catch (ClassNotFoundException e) {
	        return;
	    }

	    Connection connexion = null;
	    Statement statement = null;
	    
	    try {
	        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
	        String query = "INSERT INTO News (title, body) VALUES (\"" + n.getTitle()
	        		+ "\", \"" + n.getBody() + "\");";
	        
	        PreparedStatement preparedStmt = connexion.prepareStatement(query);
	        preparedStmt.execute();
	    } 
	    catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    } 
	    finally {
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
    }
    
   
    public void registerClient(Client c) {
    	try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } 
	    catch ( ClassNotFoundException e ) {
	        return;
	    }

	    Connection connexion = null;
	    Statement statement = null;
	    
	    try {
	        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
	        String query = "INSERT INTO Clients (name, firstName, email) VALUES (\"" + c.getName()
	        		+ "\", \"" + c.getFirstName() + "\", \"" + c.getEmail() + "\");";
	        
	        PreparedStatement preparedStmt = connexion.prepareStatement(query);
	        preparedStmt.execute();
	    } 
	    catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    } 
	    finally {
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
    }
    
    
	public List<String> getClients() {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } 
	    catch ( ClassNotFoundException e ) {
	        messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
	                		+ e.getMessage() );
	        return messages;
	    }

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
	            //String name = resultat.getString("name");
	            //String firstName = resultat.getString("firstName");
	            String email = resultat.getString("email");

	            messages.add(email);
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
	
	public void removeClient(String email, HttpServletRequest request) {
		
		HttpSession s = request.getSession();
		Client tmp = (Client) s.getAttribute("sessionUser");
		
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    }
		catch (ClassNotFoundException e) {
	        return;
	    }

	    Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
	    
	    try {
	        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
	        String query = "DELETE FROM Clients WHERE email = \"" + email + "\";";
	        PreparedStatement preparedStmt = connexion.prepareStatement(query);
	        preparedStmt.execute();
	    } 
	    catch (SQLException e) {
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
	}
	
	public List<Comment> getComments() {
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } 
	    catch ( ClassNotFoundException e ) {
	        return null;
	    }

		List<Comment> result = new ArrayList<Comment>();
	    Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
	    
	    try {
	        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

	        statement = connexion.createStatement();
	        resultat = statement.executeQuery("SELECT name, comment FROM Comments;");
	 
	        while (resultat.next()) {
	        	String name = resultat.getString("name");
	            String comment = resultat.getString("comment");

	            Comment tmp = new Comment();
	            tmp.setLogin(name);
	            tmp.setComment(comment);
	            
	            result.add(tmp);
	        }
	    } 
	    catch (SQLException e) {
	    	return null;
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
	    return result;
	}
}

