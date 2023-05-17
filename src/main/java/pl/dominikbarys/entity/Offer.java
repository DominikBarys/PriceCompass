package pl.dominikbarys.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Offer(){}

    public Offer(int id, double price, Product product) {
        this.id = id;
        this.price = price;
        this.product = product;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id && Double.compare(offer.price, price) == 0 && Objects.equals(product, offer.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, product);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", price=" + price +
                ", product=" + product +
                '}';
    }
}
