package project.utils;

import java.util.Comparator;
import java.util.Map;

public class TopTenComparator<T> implements Comparator<Map.Entry<T, Integer>> {
    @Override
    public int compare(Map.Entry<T, Integer> o1, Map.Entry<T, Integer> o2) {
        int quantityFirst = o1.getValue();
        int quantitySecond = o2.getValue();
        return Integer.compare(quantityFirst, quantitySecond);
    }
}
