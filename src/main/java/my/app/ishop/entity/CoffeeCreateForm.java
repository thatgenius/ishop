package my.app.ishop.entity;


public class CoffeeCreateForm {
    private String coffeeType;
    private int price;
    private String description;
    private String additiveType;

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditiveType() {
        return additiveType;
    }

    public void setAdditiveType(String additiveType) {
        this.additiveType = additiveType;
    }

}
