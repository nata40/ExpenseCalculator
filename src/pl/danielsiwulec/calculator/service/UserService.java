package pl.danielsiwulec.calculator.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import pl.danielsiwulec.calculator.dao.DAOFactory;
import pl.danielsiwulec.calculator.dao.UserDAO;
import pl.danielsiwulec.calculator.model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class UserService {

    public void addUser(String username, String email, String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        String md5Pass = encryptPassword(password);
        user.setPassword(md5Pass);
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        userDAO.create(user);

    }
    public User getUserByUsername(String username){
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getUserByUsername(username);
        return user;
    }
    private String encryptPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes());
        String md5Password = new BigInteger(1, digest.digest()).toString(16);
        return md5Password;
    }

}
