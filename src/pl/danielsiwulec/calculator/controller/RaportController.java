package pl.danielsiwulec.calculator.controller;

import pl.danielsiwulec.calculator.model.Product;
import pl.danielsiwulec.calculator.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@WebServlet("/report")
public class RaportController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* Pobranie nazwy użytkownika z poziomu sesji */

        String username = request.getUserPrincipal().getName();
        ProductService productService = new ProductService();
       /*Pobranie wszystkich cen produktów dotychczas kupionych i wyświetlanie sumy w zł */

        List<Product> allProduct = productService.getAllProductFilterUsername(username);
        float allMoneySpend = 0;
        for (Product product :
                allProduct) {
            allMoneySpend += product.getPrice();
        }
        /* Pobranie dotychczasowego miesiąca i wyświetlenie kwoty na dany aktualny miesiąc */

        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        List<Double> allPriceFilterMonth = productService.getAllPriceFilterMonth(username,month);
         double allMoneySpendFilterMonth = 0;
        for (double c :
                allPriceFilterMonth) {
            allMoneySpendFilterMonth += c;
        }
        /* Zaokrąglenie kwoty do dwóch miejsc po przecinku */

        Double allMoneySpendFilterMonthScale2 = BigDecimal.valueOf(allMoneySpendFilterMonth)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();




        /* pobranie kwot dla każdego miesiąca i obliczenie ich wydatków na każdy miesiąc */
        Map<Integer,Double> earnMap = productService.calculateMonthlySum(username);
        System.out.println(earnMap.toString());


        /* Zapisanie atrybutów */
        request.setAttribute("earnMap",earnMap);
        request.setAttribute("allMoneySpend",allMoneySpend);
        request.setAttribute("allMoneySpendFilterMonth",allMoneySpendFilterMonthScale2);
        request.getRequestDispatcher("raport.jsp").forward(request,response);






    }
}
