package com.myproject.dao;

import com.myproject.entities.Orders;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Stateless
public class OrderDAO {

    private static final String UNIT_NAME = "my_persistence_unit";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Orders order) {
        em.persist(order);
    }

    public Orders merge(Orders order) {
        return em.merge(order);
    }

    public void remove(Orders order) {
        em.remove(em.merge(order));
    }

    public Orders find(Object id) {
        return em.find(Orders.class, id);
    }

    public List<Orders> getFullList() {
        List<Orders> list = null;

        Query query = em.createQuery("SELECT o FROM Orders o");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Orders> getListByUserId(Integer userId) {
        List<Orders> list = null;

        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.userID.id = :userId");
        query.setParameter("userId", userId);

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Orders> getListByBookId(Integer bookId) {
        List<Orders> list = null;

        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.bookID.id = :bookId");
        query.setParameter("bookId", bookId);

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

