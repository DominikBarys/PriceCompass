package pl.dominikbarys.dto.salespoint;

import pl.dominikbarys.entity.Address;

import java.util.List;

public class SalesPointDTO {

    private int id;

    private String name;

    private Address address;

    private List<Integer> offersId;


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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Integer> getOffers() {
        return offersId;
    }

    public void setOffers(List<Integer> offers) {
        this.offersId = offers;
    }
}
