package pl.dominikbarys.dto.product;

import pl.dominikbarys.entity.Category;

public class UpdateProductDTO {

    private String name;

    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}