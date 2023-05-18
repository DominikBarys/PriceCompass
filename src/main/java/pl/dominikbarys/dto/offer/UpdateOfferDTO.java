package pl.dominikbarys.dto.offer;

import java.math.BigDecimal;

public class UpdateOfferDTO {

    private BigDecimal price;

    private String productName;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}
