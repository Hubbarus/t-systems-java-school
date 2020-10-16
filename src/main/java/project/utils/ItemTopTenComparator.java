package project.utils;

import project.dto.CartDTO;

import java.util.Comparator;

public class ItemTopTenComparator implements Comparator<CartDTO> {
    @Override
    public int compare(CartDTO o1, CartDTO o2) {
        return Integer.compare(o1.getQuantity(), o2.getQuantity());
    }
}
