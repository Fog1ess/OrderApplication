package com.fog1ess.OrderApplication.service;

import com.fog1ess.OrderApplication.dao.OrderItemDao;
import com.fog1ess.OrderApplication.entity.Customer;
import com.fog1ess.OrderApplication.entity.MenuItem;
import com.fog1ess.OrderApplication.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private MenuInfoService menuInfoService; // find the corresponding menuItem

    @Autowired
    private CustomerService customerService; // find the customer who has the corresponding Cart

    @Autowired
    private OrderItemDao orderItemDao;

    public void saveOrderItem(int menuId) {
        OrderItem orderItem = new OrderItem();
        MenuItem menuItem = menuInfoService.getMenuItem(menuId);//find the menuItem
        //find customer's email (data in Security)
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        Customer customer = customerService.getCustomer(username);

        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setQuantity(1);//TODO
        orderItem.setPrice(menuItem.getPrice());
        orderItemDao.save(orderItem);
    }
}
