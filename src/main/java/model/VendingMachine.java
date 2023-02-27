package model;

import java.util.ArrayList;
import java.util.List;

import enums.Coin;
import enums.Products;
import exception.NotFullPrice;
import exception.NotSufficientChange;
import exception.SoldeInsuffisant;

public class VendingMachine {

    private Stocking<Coin> coinStocking = new Stocking<Coin>();
    private Stocking<Products> productStocking = new Stocking<Products>();
    private Products currentProduct;
    private double currentBalance;
    private double totaleSales;

    public VendingMachine() {
        init();
    }

    private void init() {
        for (Coin coin : Coin.values()) {
            coinStocking.putProduct(coin, 20);
        }

        for (Products product : Products.values()) {
            productStocking.putProduct(product, 20);
        }

    }

    public double selectProductAndGetPrice(Products product) throws SoldeInsuffisant {
        if (productStocking.isProduct(product)) {
            currentProduct = product;
            return currentProduct.getPrice();
        }
        throw new SoldeInsuffisant("Solde Insuffisant");
    }

    public void insertCoin(Coin coin) {
        currentBalance = currentBalance + coin.getValueOfCoin();
        coinStocking.addProduct(coin);
    }

    public Change<Products, List<Coin>> takeProductAndChange() throws NotSufficientChange, NotFullPrice {
        Products product = takeProduct();
        totaleSales = totaleSales + currentProduct.getPrice();
        List<Coin> change = takeChange();
        return new Change<Products, List<Coin>>(product, change);
    }

    private Products takeProduct() throws NotSufficientChange, NotSufficientChange, NotFullPrice {
        if (currentBalance >= currentProduct.getPrice()) {
            if (haveSufficentChange()) {
                productStocking.reduceQuantity(currentProduct);
                return currentProduct;
            }
            throw new NotSufficientChange("Not Sufficient change");

        }
        throw new NotFullPrice("Price not full");
    }

    private boolean haveSufficentChange() {
        return haveSufficientChangeForAmount(currentBalance - currentProduct.getPrice());
    }

    private boolean haveSufficientChangeForAmount(double amount) {
        boolean hasChange = true;
        try {
            getChange(amount);
        } catch (NotSufficientChange error) {
            return hasChange = false;
        }

        return hasChange;

    }

    private List<Coin> takeChange() throws NotSufficientChange {
        double changeAmount = currentBalance - currentProduct.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCoinStocking(change);
        currentBalance = 0;
        currentProduct = null;
        return change;
    }

    private void updateCoinStocking(List<Coin> change) {
        for (Coin coin : change) {
            coinStocking.reduceQuantity(coin);
        }
    }

    public List<Coin> refund() throws NotSufficientChange {
        List<Coin> refund = getChange(currentBalance);
        updateCoinStocking(refund);
        currentBalance = 0;
        currentProduct = null;
        return refund;
    }

    private List<Coin> getChange(double amount) throws NotSufficientChange {
        List<Coin> changes = null;
        if (amount > 0) {
            changes = new ArrayList<Coin>();
            double balance = amount;
            balancePositive(changes, balance);
        }

        return changes;
    }

    private void balancePositive(List<Coin> changes, double balance) throws NotSufficientChange {
        while (balance > 0) {
            if (balance >= Coin.FIVEMAD.getValueOfCoin() && coinStocking.isProduct(Coin.FIVEMAD)) {
                changes.add(Coin.FIVEMAD);
                balance = balance - Coin.FIVEMAD.getValueOfCoin();
                continue;
            } else if (balance >= Coin.HALFMAD.getValueOfCoin() && coinStocking.isProduct(Coin.HALFMAD)) {
                changes.add(Coin.HALFMAD);
                balance = balance - Coin.HALFMAD.getValueOfCoin();
                continue;
            } else if (balance >= Coin.ONEMAD.getValueOfCoin() && coinStocking.isProduct(Coin.ONEMAD)) {
                changes.add(Coin.ONEMAD);
                balance = balance - Coin.ONEMAD.getValueOfCoin();
                continue;
            } else if (balance >= Coin.TENMAD.getValueOfCoin() && coinStocking.isProduct(Coin.TENMAD)) {
                changes.add(Coin.TENMAD);
                balance = balance - Coin.TENMAD.getValueOfCoin();
                continue;
            } else if (balance >= Coin.TWOMAD.getValueOfCoin() && coinStocking.isProduct(Coin.TWOMAD)) {
                changes.add(Coin.TWOMAD);
                balance = balance - Coin.TWOMAD.getValueOfCoin();
                continue;
            } else {
                throw new NotSufficientChange("Not Sufficient Change");
            }
        }
    }

    // reset operation for vending machine supplier
    public void reset() {
        coinStocking.deleteProduct();
        productStocking.deleteProduct();
        totaleSales = 0;
        currentProduct = null;
        currentBalance = 0;
    }

}
