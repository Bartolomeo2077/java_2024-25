package com.myproject.dao;

import com.myproject.entities.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Map;

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "my_persistence_unit";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Users user) {
		em.persist(user);
	}

	public Users merge(Users user) {
		return em.merge(user);
	}

	public void remove(Users user) {
		em.remove(em.merge(user));
	}

	public Users find(Object id) {
		return em.find(Users.class, id);
	}

	public List<Users> getFullList() {
		List<Users> list = null;

		Query query = em.createQuery("select u from Users u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Users> getList(Map<String, Object> searchParams) {
		List<Users> list = null;

		// 1. Build query string with parameters
		String select = "select u ";
		String from = "from Users u ";
		String where = "";
		String orderby = "order by u.username asc, u.email";

		// search for username
		String username = (String) searchParams.get("username");
		if (username != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.username like :username ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (username != null) {
			query.setParameter("username", username+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Users objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
