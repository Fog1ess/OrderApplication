package com.fog1ess.OrderApplication.service;

import com.fog1ess.OrderApplication.dao.CartDao;
import com.fog1ess.OrderApplication.entity.Cart;
import com.fog1ess.OrderApplication.entity.Customer;
import com.fog1ess.OrderApplication.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartDao cartDao;

    public Cart getCart() {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        Customer customer = customerService.getCustomer(username);

        if(customer != null) {
            Cart cart = customer.getCart();
            double totalPrice = 0;
            for(OrderItem orderItem: cart.getOrderItemList()) {
                totalPrice += orderItem.getPrice() * orderItem.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            return cart;
        }
        return new Cart();
    }

    public void cleanCart() {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        Customer customer = customerService.getCustomer(username);
        if (customer != null) cartDao.removeAllCartItems(customer.getCart());
    }

}
