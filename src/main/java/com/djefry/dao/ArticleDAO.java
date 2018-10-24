package com.djefry.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.djefry.model.Article;

public class ArticleDAO {
	
	static EntityManagerFactory factory;
	static EntityManager entityManager;
	
	public static void addArticle(Article article) {
		begin();
		entityManager.persist(article);
		end();
	}
	
	public static List<Article> showArticles(){
		begin();
		List<Article> list = showArticlesQuery();
		end();		
		return list;
	}
	
	private static List<Article> showArticlesQuery() {
		String jpql = "SELECT a FROM Article a";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Article> listArticles = query.getResultList();
		return listArticles;
	}
	
	public static Article showArticle(Integer id){
		begin();
		Article article = showArticleQuery(id);
		end();		
		return article;
	}
	
	private static Article showArticleQuery(Integer id) {
		String jpql = "Select b From Article b Where b.id = "+String.valueOf(id);
		Query query = entityManager.createQuery(jpql);
		Article article = (Article) query.getSingleResult();
		return article;
	}
	
	public static void updArticle(Article article) {
		begin();
		updArticleQuery(article);
		end();
	}

	public static void updArticleQuery(Article article) {
		String jpql = "Update Article SET name= '"+article.getName()+"' ,type= '"+article.getType()+"' ,numpage= '"+article.getNumpage()+ "' WHERE id='"+String.valueOf(article.getId())+"'";
		Query query = entityManager.createQuery(jpql);
		query.executeUpdate();
	}
	
	public static void updArticles(Article article) {
		begin();
		updArticlesQuery(article);
		end();
	}
	
	public static void updArticlesQuery(Article article) {
		String jpql = "Update Article SET name= '"+article.getName()+"' ,type= '"+article.getType()+"' ,numpage= '"+article.getNumpage()+ "'";
		Query query = entityManager.createQuery(jpql);
		query.executeUpdate();
	}
	
	
	public static void delArticles(){	
		begin();
		String jpql = "Delete From Article b ";
		Query query = entityManager.createQuery(jpql);
		query.executeUpdate();
		end();
	}
	
	public static void delArticle(Integer id){	
		begin();
		String jpql = "Delete From Article b Where b.id = " + String.valueOf(id);
		Query query = entityManager.createQuery(jpql);
		query.executeUpdate();
		end();
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
