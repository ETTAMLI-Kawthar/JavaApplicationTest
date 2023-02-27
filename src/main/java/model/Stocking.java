package model;

import java.util.HashMap;
import java.util.Map;

public class Stocking<T> {

    private Map<T, Integer> stock = new HashMap<T, Integer>();

    public int getQuantity(T products) {
        Integer value = stock.get(products);
        if (value == null) {
            return 0;
        } else {
            return value;
        }
    }

    public void addProduct(T products) {
        int number = stock.get(products);
        stock.put(products, number + 1);
    }

    public void reduceQuantity(T products) {
        if (isProduct(products)) {
            int number = stock.get(products);
            stock.put(products, number - 1);
        }
    }

    boolean isProduct(T products) {
        return getQuantity(products) > 0;
    }

    public void deleteProduct() {
        stock.clear();
    }

    public void putProduct(T product, int quantity) {
        stock.put(product, quantity);
    }

}
