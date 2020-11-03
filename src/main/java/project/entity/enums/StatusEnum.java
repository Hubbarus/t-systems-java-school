package project.entity.enums;

/**
 * Enumeration of order status.
 */
public enum StatusEnum {
    NEW("New"),
    PROCESSING("Processing"),
    COLLECTING("Collecting"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    String value;

    StatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
