package model;

import java.util.List;

import enums.Coin;

public class Products {

    private Product product;
    private List<Coin> coin;

    public Products(Product product, List<Coin> coin) {
        this.product = product;
        this.coin = coin;
    }

    public Product getProduct() {
        return product;
    }

    public List<Coin> getCoin() {
        return coin;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCoin(List<Coin> coin) {
        this.coin = coin;
    }

}
