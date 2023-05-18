package pl.dominikbarys.dto.category;

//TODO add some DTOs with messages like "name should be 32 letters max"

public class UpdateCategoryDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
