package services.impl;

import models.Coin;
import services.Account;

public class CoinsCounter implements Account<Coin> {
    static int NUM_ITEMS = 5;

    private final int[] cnt = new int[NUM_ITEMS];

    public CoinsCounter() {}

    public CoinsCounter(int... cnt) {
        System.arraycopy(cnt, 0, this.cnt, 0, NUM_ITEMS);
    }

    private int currencyId(Coin c) {
        return switch (c) {
            case TEN -> 0;
            case TWENTY -> 1;
            case FIFTY -> 2;
            case HUNDRED -> 3;
            case TWO_HUNDRED -> 4;
        };
    }

    public void deposit(Coin c, int amount) {
        cnt[currencyId(c)] += amount;
    }

    public void withdraw(Coin c, int amount) {
        cnt[currencyId(c)] -= amount;
    }

    public void setBalance(Coin c, int amount) {
        cnt[currencyId(c)] = amount;
    }

    public int getBalance(Coin c) {
        return cnt[currencyId(c)];
    }
}
