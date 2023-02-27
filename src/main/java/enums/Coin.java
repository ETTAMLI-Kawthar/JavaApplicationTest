package enums;

public enum Coin {
    HALFMAD(0.5),
    ONEMAD(1.0),
    TWOMAD(2.0),
    FIVEMAD(5.0),
    TENMAD(10.0);

    private double valueOfCoin;

    public double getValueOfCoin() {
        return valueOfCoin;
    }

    private Coin(double valueOfCoin) {
        this.valueOfCoin = valueOfCoin;
    }

}
