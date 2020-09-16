package project.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart  implements Serializable {
    @EmbeddedId
    private CartId cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private Order order;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    public Cart() {
    }

    public Cart(Order order, Item item) {
        this.order = order;
        this.item = item;
        this.cartId = new CartId(order.getId(), item.getId());
    }

    public CartId getCartId() {
        return cartId;
    }

    public void setCartId(CartId cartId) {
        this.cartId = cartId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", order=" + order +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return quantity == cart.quantity &&
                Objects.equals(cartId, cart.cartId) &&
                Objects.equals(order, cart.order) &&
                Objects.equals(item, cart.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, order, item, quantity);
    }
}
