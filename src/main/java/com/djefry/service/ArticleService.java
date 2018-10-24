package com.djefry.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.djefry.dao.ArticleDAO;
import com.djefry.dao.UserDAO;
import com.djefry.model.Article;


@Path("/article")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleService {
	
	@POST
	@Path("/add/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(Article article, @HeaderParam("token") String token) {
		if(token!=null) {
			try {
				String tokenServ = UserDAO.authenticateToken(token);
				if(tokenServ.equals(token)) {
					ArticleDAO.addArticle(article);
					return Response.status(Response.Status.CREATED).entity(article).build();
					} else {
						return Response.status(200).entity("token incorrectly").build();
					}
			} catch (Exception e) {
				return Response.status(200).entity("token incorrectly").build();
			}
		}
		return Response.status(200).entity("not token").build();
	}
	
	@GET
	@Path("/show/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showArticles(@HeaderParam("token") String token) {
		
		if(token!=null) {
			try {
				String tokenServ = UserDAO.authenticateToken(token);
				if (tokenServ.equals(token)) {
					List<Article> list = ArticleDAO.showArticles();
					return Response.status(200).entity(list).build();
					} else {
						return Response.status(200).entity("token incorrectly").build();
					}
			} catch (Exception e) {
				return Response.status(200).entity("token incorrectly").build();
			}
		
		}
		return Response.status(200).entity("not token").build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showArticle(@PathParam("id") Integer id,@HeaderParam("token") String token) {
		
		if (token!=null) {
			try {
				String tokenServ = UserDAO.authenticateToken(token);
				if(tokenServ.equals(token)) {
					Article article = ArticleDAO.showArticle(id);
					return Response.ok(article).build();
				} else {
					return Response.status(200).entity("token incorrectly").build();
				}
			} catch (Exception e) {
				return Response.status(200).entity("token incorrectly").build();
			}			
		}
		return Response.status(200).entity("not token").build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updArticle(@PathParam("id") Integer id, Article article, @HeaderParam("token") String token) {
		
		if (token!=null) {
			try {
				String tokenServ = UserDAO.authenticateToken(token);
				if (tokenServ.equals(token)) {
					article.setId(id);
					ArticleDAO.updArticle(article);
					return Response.status(Response.Status.CREATED).entity(article).build();
				}else {
					return Response.status(200).entity("token incorrectly").build();
				}
			} catch (Exception e) {
				return Response.status(200).entity("token incorrectly").build();
			}	
		}
		return Response.status(200).entity("not token").build();
	}
	
	@PUT
	@Path("/update/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updArticles(Article article, @HeaderParam("token") String token) {
		if (token!=null) {
			try {
				String tokenServ = UserDAO.authenticateToken(token);
				if (tokenServ.equals(token)) {
					ArticleDAO.updArticles(article);
					return Response.status(Response.Status.CREATED).entity(article).build();
				} else {
					return Response.status(200).entity("token incorrectly").build();
				}
			} catch (Exception e) {
				return Response.status(200).entity("token incorrectly").build();
			}
		}
		return Response.status(200).entity("not token").build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delArticle(@PathParam("id") Integer id, @HeaderParam("token") String token) {
		
		if (token!=null) {
			try {
				String tokenServ = UserDAO.authenticateToken(token);
				if (tokenServ.equals(token)) {
					ArticleDAO.delArticle(id);
					return Response.status(Response.Status.OK).build();
				} else {
					return Response.status(200).entity("token incorrectly").build();
				}
			} catch (Exception e) {
				return Response.status(200).entity("token incorrectly").build();
			}
		}
		return Response.status(200).entity("not token").build();
	}
	
	@DELETE
	@Path("/delete/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delArticles(@HeaderParam("token") String token) {
		if (token!=null) {
			try {
				String tokenServ = UserDAO.authenticateToken(token);
				if (tokenServ.equals(token)) {
					ArticleDAO.delArticles();
					return Response.status(Response.Status.OK).build();
				} else {
					return Response.status(200).entity("token incorrectly").build();
				}
			} catch (Exception e) {
				return Response.status(200).entity("token incorrectly").build();
			}
		}
		return Response.status(200).entity("not token").build();
	}
}
