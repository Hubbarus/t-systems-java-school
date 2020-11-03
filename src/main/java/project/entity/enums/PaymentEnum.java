package project.entity.enums;

/**
 * Enumeration of payment methods.
 */
public enum PaymentEnum {
    CASH("Cash"),
    CARD("Card"),
    REMITTANCE("Remittance");

    String value;

    PaymentEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
