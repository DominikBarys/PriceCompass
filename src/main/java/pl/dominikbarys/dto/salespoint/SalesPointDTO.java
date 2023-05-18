package pl.dominikbarys.dto.salespoint;

import java.util.List;

public class SalesPointDTO {

    private int id;

    private String name;

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

    public List<Integer> getOffers() {
        return offersId;
    }

    public void setOffers(List<Integer> offers) {
        this.offersId = offers;
    }
}
