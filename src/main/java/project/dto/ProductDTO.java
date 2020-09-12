package project.dto;

import java.util.Objects;

public class ProductDTO {
    private String itemName;
    private double price;
    private String itemGroup;
    private String description;
    private double weight;
    private double volume;
    private int stock;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO that = (ProductDTO) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                Double.compare(that.getWeight(), getWeight()) == 0 &&
                Double.compare(that.getVolume(), getVolume()) == 0 &&
                getStock() == that.getStock() &&
                Objects.equals(getItemName(), that.getItemName()) &&
                Objects.equals(getItemGroup(), that.getItemGroup()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemName(), getPrice(), getItemGroup(), getDescription(), getWeight(), getVolume(), getStock());
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "itemName='" + itemName + '\'' +
                ", price=" + price +
                ", itemGroup='" + itemGroup + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", volume=" + volume +
                ", stock=" + stock +
                '}';
    }
}
