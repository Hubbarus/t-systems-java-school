package project.entity.enums;

public enum ShipmentEnum {
    DOOR_TO_DOOR("Door to door delivery"),
    SELF_PICKUP("Self-pickup"),
    NO_SHIPPING("No shippping");

    String value;

    ShipmentEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
