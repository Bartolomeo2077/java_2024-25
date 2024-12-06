package com.myproject.orders;

import com.myproject.dao.OrderDAO;
import com.myproject.entities.Orders;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class OrdersList {

    @EJB
    private OrderDAO orderDAO;

    private List<Orders> orders;

    public List<Orders> getOrders() {
        if (orders == null) {
            orders = orderDAO.getFullList();
        }
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}