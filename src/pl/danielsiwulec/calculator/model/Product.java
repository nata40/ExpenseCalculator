package pl.danielsiwulec.calculator.model;

import java.sql.Date;
import java.util.Objects;

public class Product {
    private User user;
    private long id;
    private String name;
    private float price;

    public Product(){

    }

    public Product(Product product) {
        this.user = product.user;
        this.id = product.id;
        this.name = product.name;
        this.price = product.price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Float.compare(product.price, price) == 0 &&
                Objects.equals(user, product.user) &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, id, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "user=" + user +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
