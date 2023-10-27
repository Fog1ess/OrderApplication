package com.fog1ess.OrderApplication.Controller;

import com.fog1ess.OrderApplication.entity.Cart;
import com.fog1ess.OrderApplication.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @ResponseBody
    public Cart getCart(){
        return cartService.getCart();
    }

    @RequestMapping(value = "/cleancart", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void cleanCart() {
        cartService.cleanCart();
    }
}
