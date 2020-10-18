package pl.danielsiwulec.calculator.controller;

import pl.danielsiwulec.calculator.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verification")
public class VerificationController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String key1= (String) request.getSession().getAttribute("key2");
        String username1 = (String) request.getSession().getAttribute("username");
        String email1  = (String) request.getSession().getAttribute("email");
        String password1 = (String) request.getSession().getAttribute("password");
        String code = request.getParameter("inputCode");
        if(key1.equals(code)){
            System.out.println("Udało się utworzyć konto");
            response.sendRedirect(request.getContextPath() + "/");
            UserService userService = new UserService();
            userService.addUser(username1, email1, password1);
        }else{
            System.out.println("Błąd utworzenia konta");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
