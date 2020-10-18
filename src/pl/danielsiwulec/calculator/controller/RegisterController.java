package pl.danielsiwulec.calculator.controller;

import org.apache.commons.mail.EmailException;
import pl.danielsiwulec.calculator.service.UserService;
import pl.danielsiwulec.calculator.verification.EmailVerification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("inputUsername");
        String password = request.getParameter("inputPassword");
        String email = request.getParameter("inputEmail");
        EmailVerification emailVerification = new EmailVerification();
        //userService.addUser(username, email, password);
        int key = emailVerification.generateKey();
        String key2 = Integer.toString(key);
        System.out.println("key2 "+ key2);
        try {
            emailVerification.sendEmail(email,key);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        //response.sendRedirect(request.getContextPath() + "/");
        request.getSession().setAttribute("key2",key2);
        request.getSession().setAttribute("username",username);
        request.getSession().setAttribute("password",password);
        request.getSession().setAttribute("email",email);
        request.getRequestDispatcher("/emailVerification.jsp").forward(request,response);
    }

}
