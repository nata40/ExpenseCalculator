package pl.danielsiwulec.calculator.dao;

public class MysqlDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAOImpl();
    }
}
