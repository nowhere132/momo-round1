package services.impl;

import exceptions.NotEnoughMoneyException;
import exceptions.NotRefundableException;
import exceptions.SoldOutException;
import models.Coin;
import models.Product;
import services.VendingMachine;
import services.Wallet;

import java.util.List;

public class VendingMachineImpl implements VendingMachine {
    private final ProductsCounter inventory = new ProductsCounter(5, 5, 5);
    private final ProductsCounter cart = new ProductsCounter();
    private final Wallet wallet = new WalletImpl();
    private int balance = 0;

    private static final List<Product> allProducts = List.of(Product.COKE, Product.PEPSI, Product.SODA);

    @Override
    public void addCoin(Coin c) {
        wallet.insert(c);
        balance += c.value;

        notifyStates();
    }

    @Override
    public void pickProduct(Product p) throws SoldOutException, NotEnoughMoneyException {
        if (balance < p.price) {
            throw new NotEnoughMoneyException();
        }
        if (inventory.getBalance(p) == 0) {
            throw new SoldOutException();
        }
        balance -= p.price;
        inventory.withdraw(p, 1);
        cart.deposit(p, 1);

        notifyStates();
    }

    @Override
    public void clearCart() {
        allProducts.forEach(elem -> {
            var amount = cart.getBalance(elem);
            cart.withdraw(elem, amount);
            inventory.deposit(elem, amount);
            balance += amount * elem.price;
        });

        notifyStates();
    }

    @Override
    public void refund() throws NotRefundableException {
        wallet.refund(balance);
        balance = 0;

        notifyStates();
    }

    @Override
    public void releaseProducts() {
        allProducts.forEach(elem -> cart.withdraw(elem, cart.getBalance(elem)));

        notifyStates();
    }

    private void notifyStates() {
        System.out.println("--> balance: " + balance);
    }
}
