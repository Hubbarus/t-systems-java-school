package project.entity.enums;

public enum StatusEnum {
    NEW("New"),
    PROCESSING("Processing"),
    COLLECTING("Collecting"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    TEMPORARY_ORDER("Temporary order");

    String value;

    StatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
