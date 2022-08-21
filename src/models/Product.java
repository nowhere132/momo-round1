package models;

public enum Product {
    COKE(10), PEPSI(10), SODA(20);
    public final int price;

    Product(int price) {
        this.price = price;
    }
}