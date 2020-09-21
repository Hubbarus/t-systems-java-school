package project.entity.enums;

public enum PaymentEnum {
    CASH("Cash"),
    CARD("Card"),
    REMITTANCE("Remittance"),
    NO_PAYMENT("No payment");

    String value;

    PaymentEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
