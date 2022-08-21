package services;

import exceptions.NotEnoughMoneyException;
import exceptions.NotRefundableException;
import exceptions.SoldOutException;
import models.Coin;
import models.Product;

public interface VendingMachine {
    void addCoin(Coin c);
    void pickProduct(Product p) throws SoldOutException, NotEnoughMoneyException;
    void clearCart();
    void refund() throws NotRefundableException;
    void releaseProducts();
}
