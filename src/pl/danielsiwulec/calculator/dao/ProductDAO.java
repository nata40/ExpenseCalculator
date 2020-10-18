package pl.danielsiwulec.calculator.dao;

import pl.danielsiwulec.calculator.model.Product;

import java.text.SimpleDateFormat;
import java.util.List;

public interface ProductDAO extends GenericDAO<Product,Long> {
    List<Product> getAll();
    List<Product> getAllProductFilterUsername(String username);
    public void deleteProduct(long user_id, String product_name);
    List<Double> getAllPriceFilterMonth(String username,int date);

}
