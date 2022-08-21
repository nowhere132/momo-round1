package services;

import exceptions.NotRefundableException;
import models.Coin;

public interface Wallet {
    void init();
    void insert(Coin c);
    void refund(int amount) throws NotRefundableException;
}
