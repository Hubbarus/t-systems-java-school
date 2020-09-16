package project.dto;

import java.util.Objects;

public class ItemDTO {
    private long id;
    private String itemName;
    private String itemGroup;
    private double price;
    private String description;
    private double weight;
    private double volume;
    private int stock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemGroup='" + itemGroup + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", volume=" + volume +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDTO)) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return id == itemDTO.id &&
                Double.compare(itemDTO.price, price) == 0 &&
                Double.compare(itemDTO.weight, weight) == 0 &&
                Double.compare(itemDTO.volume, volume) == 0 &&
                stock == itemDTO.stock &&
                Objects.equals(itemName, itemDTO.itemName) &&
                Objects.equals(itemGroup, itemDTO.itemGroup) &&
                Objects.equals(description, itemDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, itemGroup, price, description, weight, volume, stock);
    }
}
