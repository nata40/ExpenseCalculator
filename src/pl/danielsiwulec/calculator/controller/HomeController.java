package pl.danielsiwulec.calculator.controller;

import pl.danielsiwulec.calculator.model.Product;
import pl.danielsiwulec.calculator.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/product")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getUserPrincipal().getName();
        ProductService productService = new ProductService();
        List<Product> allProduct = productService.getAllProductFilterUsername(username);
        request.setAttribute("allProduct",allProduct);
        request.getRequestDispatcher("main.jsp").forward(request,response);
    }

}
