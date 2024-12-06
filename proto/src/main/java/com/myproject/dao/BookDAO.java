package com.myproject.dao;

import com.myproject.entities.Books;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Stateless
public class BookDAO {

    private static final String UNIT_NAME = "my_persistence_unit";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Books book) {
        em.persist(book);
    }

    public Books merge(Books book) {
        return em.merge(book);
    }

    public void remove(Books book) {
        em.remove(em.merge(book));
    }

    public Books find(Object id) {
        return em.find(Books.class, id);
    }

    public List<Books> getFullList() {
        List<Books> list = null;
        Query query = em.createQuery("SELECT b FROM Books b");
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
