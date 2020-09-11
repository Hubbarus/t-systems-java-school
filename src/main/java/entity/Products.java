package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Products implements Serializable {
    private long id;
    private String itemName;
    private double price;
    private String itemGroup;
    private String description;
    private double weight;
    private double volume;
    private int stock;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "item_name", nullable = false, length = -1)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "item_group", nullable = false, length = -1)
    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "weight", nullable = false, precision = 0)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "volume", nullable = false, precision = 0)
    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Basic
    @Column(name = "stock", nullable = false)
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        if (id != products.id) return false;
        if (Double.compare(products.price, price) != 0) return false;
        if (Double.compare(products.weight, weight) != 0) return false;
        if (Double.compare(products.volume, volume) != 0) return false;
        if (stock != products.stock) return false;
        if (itemName != null ? !itemName.equals(products.itemName) : products.itemName != null) return false;
        if (itemGroup != null ? !itemGroup.equals(products.itemGroup) : products.itemGroup != null) return false;
        if (description != null ? !description.equals(products.description) : products.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (itemGroup != null ? itemGroup.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + stock;
        return result;
    }

    @Override
    public String toString() {
        return "Product {" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", itemGroup='" + itemGroup + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", volume=" + volume +
                ", stock=" + stock +
                '}';
    }
}
