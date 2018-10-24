package com.djefry.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.djefry.dao.UserDAO;
import com.djefry.model.User;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
	
	@POST
	@Path("/add/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		UserDAO.addUser(user);
		return Response.status(Response.Status.CREATED).entity(user).build();
	}
	
	
	@POST
	@Path("/authentication")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
		try {
			String user = UserDAO.authenticateUser(username,password);
			return Response.status(Response.Status.CREATED).entity(user).build();
		} catch (Exception e) {
			return Response.status(200).entity("username or password incorrectly").build();
		}
		
	}
	
}
