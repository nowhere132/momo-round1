package services.impl;

import exceptions.NotRefundableException;
import models.Coin;
import services.Wallet;

import java.util.List;
import java.util.Optional;

public class WalletImpl implements Wallet {
    private final CoinsCounter cc = new CoinsCounter();
    private static final List<Coin> allCoins = List.of(Coin.TEN, Coin.TWENTY, Coin.FIFTY, Coin.HUNDRED, Coin.TWO_HUNDRED);

    public WalletImpl() {
        init();
    }
    @Override
    public void init() {
        System.out.println("Wallet is initialized");
    }

    @Override
    public void insert(Coin c) {
        cc.deposit(c, 1);
    }

    @Override
    public void refund(int amount) throws NotRefundableException {
        var coins = fromAmount(amount).orElseThrow(NotRefundableException::new);
        allCoins.forEach(elem -> cc.withdraw(elem, coins.getBalance(elem)));
    }

    private Optional<CoinsCounter> fromAmount(int amount) {
        for (int i5 = 0; i5 <= maxNoCoin(amount, Coin.TWO_HUNDRED); i5++) {
            var leftAmount4 = amount - i5 * Coin.TWO_HUNDRED.value;
            for (int i4 = 0; i4 <= maxNoCoin(leftAmount4, Coin.HUNDRED); i4++) {
                var leftAmount3 = leftAmount4 - i4 * Coin.HUNDRED.value;
                for (int i3 = 0; i3 <= maxNoCoin(leftAmount3, Coin.FIFTY); i3++) {
                    var leftAmount2 = leftAmount3 - i3 * Coin.FIFTY.value;
                    for (int i2 = 0; i2 <= maxNoCoin(leftAmount2, Coin.TWENTY); i2++) {
                        var leftAmount = leftAmount2 - i2 * Coin.TWENTY.value;
                        var i1 = leftAmount/Coin.TEN.value;
                        if (leftAmount % Coin.TEN.value > 0 || i1 > maxNoCoin(leftAmount, Coin.TEN)) continue;
//                        System.out.println("fromAmount: " + i1 + ", " + i2 + ", " + i3 + ", " + i4 + ", " + i5);
                        return Optional.of(new CoinsCounter(i1, i2, i3, i4, i5));
                    }
                }
            }
        }
        return Optional.empty();
    }

    private int maxNoCoin(int amount, Coin c) {
        return Math.min(amount/c.value, cc.getBalance(c));
    }
}
