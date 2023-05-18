package pl.dominikbarys.dto.salespoint;

import java.util.List;

public class CreateSalesPointDTO {

    private String name;

    private List<Integer> offerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Integer> getOfferId() {
        return offerId;
    }

    public void setOfferId(List<Integer> offerId) {
        this.offerId = offerId;
    }
}
