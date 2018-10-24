package com.djefry.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.djefry.model.User;

public class UserDAO {
	
	static EntityManagerFactory factory;
	static EntityManager entityManager;
	
	public static void addUser(User user){	
		begin();
		addUserQuery(user);
		end();
	}
	
	private static void addUserQuery(User user) {
		//entityManager.persist(user);
		String passwordMD5 = getMd5(user.getPassword());
		String jpql = "INSERT INTO user(username, password, token) values ('"+user.getUsername()+"','"+passwordMD5+"','"+user.getToken()+"')";
		Query query = entityManager.createNativeQuery(jpql);
		query.executeUpdate();
	}
	
	 public static String getMd5(String input) { 
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
	 } 
	
	public static String authenticateUser(String username, String password){
		begin();
		User user = authenticateUserQuery(username,password);
		end();		
		return "TOKEN: "+user.getToken();
	}
	
	private static User authenticateUserQuery(String username, String password) {
		String passwordMD5 = getMd5(password);
		String jpql = "Select b From User b Where b.username = '"+username+"' and b.password = '"+passwordMD5+"'";
		Query query = entityManager.createQuery(jpql);
		User user = (User) query.getSingleResult();
		return user;
	}
	
	public static String authenticateToken(String token){
		begin();
		String userToken = authenticateTokenQuery(token);
		end();		
		return userToken;
	}
	
	private static String authenticateTokenQuery(String token) {
		String jpql = "Select b.token From User b Where '"+token+"' in (b.token)";
		Query query = entityManager.createQuery(jpql);
		String utoken = (String) query.getSingleResult();
		return utoken;
	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("ArticleUnit");
		entityManager = factory.createEntityManager();		
		entityManager.getTransaction().begin();
	}
	
	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
