package com.myproject.user;

import com.myproject.dao.UserDAO;
import com.myproject.entities.Users;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class UserList {
    
    private String username;
    
    @Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Users> getFullList(){
		return userDAO.getFullList();
	}

	public List<Users> getList(){
		List<Users> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (username != null && username.length() > 0){
			searchParams.put("username", username);
		}
		
		//2. Get list
		list = userDAO.getList(searchParams);
		
		return list;
	}
}
