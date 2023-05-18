package pl.dominikbarys.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class SalesPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany
    @JoinColumn(name = "sales_point_id", referencedColumnName = "id")
    private List<Offer> offers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesPoint that = (SalesPoint) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(offers, that.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, offers);
    }

    @Override
    public String toString() {
        return "SalesPoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", offer=" + offers +
                '}';
    }
}
