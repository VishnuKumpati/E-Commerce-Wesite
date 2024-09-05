package com.web.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.Entity.Admin;
import com.web.Entity.Buyer;
import com.web.Entity.Retailer;
import com.web.GenericConfig.GenericConfiguration;
import com.web.Service.serviceImpl.RegisterServiceImpl;
import com.web.ServiceInterface.RegisterServiceInterface;

@WebServlet("/registerController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RegisterServiceInterface service;

    public RegisterController() {
        service = GenericConfiguration.createInstance(RegisterServiceImpl.class);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String contactno = req.getParameter("contactno");
        String city = req.getParameter("city");
        String userType = req.getParameter("userType");
        String passkey=req.getParameter("adminPasswordField");

        Object user = null;

        switch (userType) {
            case "Admin":
            	if(passkey.equals("98765432123")) {
                user = new Admin(name, email, password);
            	}
                break;
            case "Buyer":
                Buyer buyer = new Buyer();
                buyer.setName(name);
                buyer.setEmail(email);
                buyer.setPassword(password);
                buyer.setAge(age);
                buyer.setContactno(Long.parseLong(contactno));
                buyer.setCity(city);
                buyer.setUserType(userType);
                user = buyer;
                break;
            case "Retailer":
              Retailer retailer = new Retailer();
              retailer.setName(name);
              retailer.setEmail(email);
              retailer.setCity(city);
              retailer.setContactNumber(contactno);
              retailer.setPassword(password);
              user=retailer;
                break;
            default:
                res.sendRedirect("register.jsp?error=Invalid%20User%20Type");
                return;
        }

        String msg = service.addUser(user, userType);
        System.out.println(msg);

        // Set the attributes for the success page
        req.setAttribute("message", "Registration successful. Please log in to continue.");
        req.setAttribute("nextPage", "login.jsp");
        req.setAttribute("buttonLabel", "Login");

        // Forward to success.jsp
        req.getRequestDispatcher("success.jsp").forward(req, res);
    }
}
