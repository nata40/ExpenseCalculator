package pl.danielsiwulec.calculator.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.danielsiwulec.calculator.model.Product;
import pl.danielsiwulec.calculator.model.User;
import pl.danielsiwulec.calculator.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAOImpl implements ProductDAO {
    private static final String CREATE_PRODUCT =
            "INSERT INTO product(name,price,user_id,date) VALUES(:name,:price,:user_id,:date);";
    private static final String READ_ALL_PRODUCT =
            "SELECT user.user_id, username, email, password, idproduct, name, price  FROM product LEFT JOIN user ON product.user_id = user.user_id";
    private static final String READ_ALL_PRODUCT_USERNAME =
            "SELECT user.user_id, username, email, password, idproduct, name, price  FROM product LEFT JOIN user ON product.user_id = user.user_id where username = :username;";
    private static final String DELETE_PRODUCT_FROM_LIST =
            "DELETE FROM product WHERE user_id = :user_id AND name = :name;";
    private static final String READ_ALL_PRICE_FILTER_MONTH =
            "SELECT price FROM product LEFT JOIN user ON product.user_id = user.user_id where username = :username AND date = :date;";
    private NamedParameterJdbcTemplate template;

    public ProductDAOImpl(){
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }


    @Override
    public List<Product> getAll() {

        List<Product> products = template.query(READ_ALL_PRODUCT,new ProductRowMapper());
        return products;
    }

    @Override
    public List<Product> getAllProductFilterUsername(String username) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("username", username);
        List<Product> products = template.query(READ_ALL_PRODUCT_USERNAME,parameterSource,new ProductRowMapper());
        return products;
    }

    @Override
    public void deleteProduct(long user_id, String product_name) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("user_id",user_id).addValue("name",product_name);
        template.update(DELETE_PRODUCT_FROM_LIST,parameterSource);
    }

    @Override
    public List<Double> getAllPriceFilterMonth(String username, int date) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("username",username).addValue("date",date);
        List<Double> products = template.query(READ_ALL_PRICE_FILTER_MONTH, parameterSource, new RowMapper<Double>() {
            @Override
            public Double mapRow(ResultSet resultSet, int i) throws SQLException {
                double price;
                price = resultSet.getFloat("price");
                return price;
            }
        });
        return products;
    }


    private class ProductRowMapper implements RowMapper<Product>{

        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            Product product = new Product();
            product.setPrice(resultSet.getFloat("price"));
            product.setName(resultSet.getString("name"));
            product.setId(resultSet.getInt("idproduct"));
            User user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            product.setUser(user);
            return product;
        }
    }

    @Override
    public Product create(Product product) {
       Product resultProduct = new Product(product);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("name",product.getName());
        paramMap.put("price",product.getPrice());
        paramMap.put("user_id",product.getUser().getId());
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        paramMap.put("date",month);
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_PRODUCT,parameterSource,holder);
        if(update>0){
            resultProduct.setId(holder.getKey().longValue());
        }
        return resultProduct;

    }


    @Override
    public Product read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(Product updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }


}
