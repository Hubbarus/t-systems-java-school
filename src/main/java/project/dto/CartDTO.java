//package project.dto;
//
//import java.util.Objects;
//
//public class CartDTO {
//    private long id;
//    private int quantity;
//    private OrderDTO order;
//    private ItemDTO item;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public OrderDTO getOrder() {
//        return order;
//    }
//
//    public void setOrder(OrderDTO order) {
//        this.order = order;
//    }
//
//    public ItemDTO getItem() {
//        return item;
//    }
//
//    public void setItem(ItemDTO item) {
//        this.item = item;
//    }
//
//    @Override
//    public String toString() {
//        return "CartDTO{" +
//                "id=" + id +
//                ", quantity=" + quantity +
//                ", order=" + order +
//                ", item=" + item +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CartDTO)) return false;
//        CartDTO cartDTO = (CartDTO) o;
//        return id == cartDTO.id &&
//                quantity == cartDTO.quantity &&
//                Objects.equals(order, cartDTO.order) &&
//                Objects.equals(item, cartDTO.item);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, quantity, order, item);
//    }
//}
