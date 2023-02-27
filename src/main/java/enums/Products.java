package enums;

public enum Products {
    MIRENDINA("Mirendina", 5),
    TANGO("Tango", 3),
    KITKAT("KitKat", 13),
    SNICKERS("Snickers", 15),
    MILKA("Milka", 26);

    private String name;
    private double price;

    private Products(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
