package project.utils;

import io.seruco.encoding.base62.Base62;
import org.springframework.stereotype.Component;
import project.dto.OrderDTO;

/**
 * Generator of order numbers class.
 */
@Component
public class OrderNumberGenerator {

    /**
     * Generates order number. Uses fields:
     * paymentMethod
     * shipmentMethod
     * date
     *
     * @param order to generate number
     * @param size quantity of all orders
     * @return generated number
     */
    public String generate(OrderDTO order, int size) {
        StringBuilder num = new StringBuilder();

        switch (order.getPaymentMethod()) {
            case CARD: {
                num.append("CD");
                break;
            }
            case CASH: {
                num.append("CS");
                break;
            }
            case REMITTANCE: {
                num.append("R");
                break;
            }
        }

        Base62 base62 = Base62.createInstance();
        byte[] arr = String.valueOf(size).getBytes();
        String str = new String(base62.encode(arr));
        num.append(str);

        switch (order.getShipmentMethod()) {
            case SELF_PICKUP: {
                num.append("SP");
                break;
            }
            case DOOR_TO_DOOR: {
                num.append("DTD");
                break;
            }
        }

        num.append(order.getDate().toLocalDate().getDayOfMonth());
        num.append(order.getDate().toLocalDate().getMonthValue());

        return num.toString();
    }

}
