package pl.danielsiwulec.calculator.service;

import pl.danielsiwulec.calculator.dao.DAOFactory;
import pl.danielsiwulec.calculator.dao.ProductDAO;
import pl.danielsiwulec.calculator.model.Product;
import pl.danielsiwulec.calculator.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ProductService {
    public void addProduct(String name, float price, User user){
        Product product = createProductObject(name,price,user);
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        productDAO.create(product);

    }
    private Product createProductObject(String name, float price, User user){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        User userCopy = new User(user);
        product.setUser(userCopy);
        return product;
    }
    public List<Product> getAllProduct(){
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        List<Product> products = productDAO.getAll();
        return products;
    }
    /* Metoda sortowania z dowolnym parametrem comparatora */
    /*
    public List<Product> getAllProductFilterUsername(String username, Comparator<Product> comparator){
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        List<Product> products = productDAO.getAllProductFilterUsername(username);
        if(comparator != null && products != null){
            products.sort(comparator);
        }
        return products;
    }
    */
    public List<Product> getAllProductFilterUsername(String username){
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        List<Product> products = productDAO.getAllProductFilterUsername(username);
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if(o1.getPrice()>o2.getPrice())
                    return 1;
                else if(o1.getPrice()<o2.getPrice())
                    return -1;
                return 0;
            }
        });
        return products;
    }
    public void deleteProduct(long user_id,String name_product){
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        productDAO.deleteProduct(user_id,name_product);
    }
    /*Pobranie wszystkich zarobków w danym miesiącu */
    public List<Double> getAllPriceFilterMonth(String username, int date){
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        List<Double> price = productDAO.getAllPriceFilterMonth(username,date);
        return price;
    }
    /*Obliczanie wydanej kwoty na każdy miesiąc */
    public Map<Integer,Double> calculateMonthlySum(String username){
        Map<Integer,Double> notebooks = new HashMap<>();
        for (int i = 1; i < 13; i++) {
            List<Double> list1 = getAllPriceFilterMonth(username,i);
            notebooks.put(i,addExpensesForTheMonth(list1));
        }
        return notebooks;

    }
    /*Dodatkowa klasa sumująca wydatki w jednym miesiącu */
    private double addExpensesForTheMonth(List<Double>list){
        double allMoneySpendFilterMonth = 0;
        for (double c :
                list) {
            allMoneySpendFilterMonth += c;
        }
        Double allMoneySpendFilterMonthScale2 = BigDecimal.valueOf(allMoneySpendFilterMonth)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        return allMoneySpendFilterMonthScale2;
    }

   // public List<Product> sortProductPriceLowToHigh(List<Product> products){

  //  }

}
