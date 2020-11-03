package project.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.dto.CartDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Wrapper cart class for UI.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartListWrapper implements Serializable {
    private List<CartDTO> list;
    private BigDecimal subtotal;

    public CartListWrapper(List<CartDTO> topTenItems) {
        this.list = topTenItems;
    }
}
