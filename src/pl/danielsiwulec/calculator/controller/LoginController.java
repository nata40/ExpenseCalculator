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

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getUserPrincipal() != null) {
            request.getRequestDispatcher("/product").forward(request,response);

        } else {
            System.out.println("Błąd");
            response.sendError(403);


        }
    }
}

