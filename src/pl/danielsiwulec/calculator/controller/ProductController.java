package pl.danielsiwulec.calculator.controller;

import pl.danielsiwulec.calculator.model.User;
import pl.danielsiwulec.calculator.service.ProductService;
import pl.danielsiwulec.calculator.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getUserPrincipal() != null) {
            request.getRequestDispatcher("new.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("inputName");
        String price = request.getParameter("inputPrice");
        System.out.println(price);
        String addZeroToPrice = price;
        float floatPrice = Float.parseFloat(addZeroToPrice);
        User authenticatedUser = (User) request.getSession().getAttribute("user");
        if(request.getUserPrincipal() != null) {
            ProductService productService = new ProductService();
            productService.addProduct(name, floatPrice,authenticatedUser);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        } else {
            response.sendError(403);
        }
    }
}
