package pl.danielsiwulec.calculator.controller;

import pl.danielsiwulec.calculator.model.User;
import pl.danielsiwulec.calculator.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteProductController")
public class DeleteProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("inputName");
        User authenticatedUser = (User) request.getSession().getAttribute("user");
        if(request.getUserPrincipal() != null) {
            ProductService productService = new ProductService();
            productService.deleteProduct(authenticatedUser.getId(),name);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        } else {
            response.sendError(403);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getUserPrincipal() != null) {
            request.getRequestDispatcher("delete.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    }
}
