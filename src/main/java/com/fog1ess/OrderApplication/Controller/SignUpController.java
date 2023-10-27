package com.fog1ess.OrderApplication.Controller;

import com.fog1ess.OrderApplication.entity.Customer;
import com.fog1ess.OrderApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SignUpController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void SignUp(@RequestBody Customer customer) {
        //business logic
        customerService.signUp(customer);
    }
}
