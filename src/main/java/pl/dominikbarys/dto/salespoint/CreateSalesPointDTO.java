package pl.dominikbarys.dto.salespoint;

import pl.dominikbarys.entity.Address;

import java.util.List;

public class CreateSalesPointDTO {

    private String name;

    private Address address;

    private List<Integer> offerId;

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

    public List<Integer> getOfferId() {
        return offerId;
    }

    public void setOfferId(List<Integer> offerId) {
        this.offerId = offerId;
    }
}
