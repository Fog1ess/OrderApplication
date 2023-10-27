package com.fog1ess.OrderApplication;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import com.fog1ess.OrderApplication.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();

        Customer customer = new Customer();
        customer.setEmail("sun@laioffer.com");
        customer.setPassword("123456");
        customer.setFirstName("rick");
        customer.setLastName("sun");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    Customer customer = objectMapper.readValue(IOUtils.toString(request.getReader()), Customer.class);

    System.out.println(customer.getEmail());
    System.out.println(customer.getFirstName());
    System.out.println(customer.getLastName());
    // Return status = ok as response body to the client
    response.setContentType("application/json");
    JSONObject jsonResponse = new JSONObject();
    jsonResponse.put("status", "ok");
    response.getWriter().print(jsonResponse);

    }

    public void destroy() {
    }
}