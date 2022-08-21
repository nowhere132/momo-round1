package services.impl;

import models.Product;
import services.Account;

public class ProductsCounter implements Account<Product> {
    static int NUM_ITEMS = 3;

    private final int[] cnt = new int[NUM_ITEMS];

    public ProductsCounter() {}

    public ProductsCounter(int... cnt) {
        System.arraycopy(cnt, 0, this.cnt, 0, NUM_ITEMS);
    }

    private int currencyId(Product p) {
        return switch (p) {
            case COKE -> 0;
            case PEPSI -> 1;
            case SODA -> 2;
        };
    }

    public void deposit(Product p, int amount) {
        cnt[currencyId(p)] += amount;
    }

    public void withdraw(Product p, int amount) {
        cnt[currencyId(p)] -= amount;
    }

    @Override
    public void setBalance(Product p, int amount) {
        cnt[currencyId(p)] = amount;
    }

    public int getBalance(Product p) {
        return cnt[currencyId(p)];
    }
}
