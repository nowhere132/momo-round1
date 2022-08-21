package services.impl;

import models.Coin;

public class CoinFactory {
    public static Coin getCoin(String s) throws Exception {
        return switch (s) {
            case "10" -> Coin.TEN;
            case "20" -> Coin.TWENTY;
            case "50" -> Coin.FIFTY;
            case "100" -> Coin.HUNDRED;
            case "200" -> Coin.TWO_HUNDRED;
            default -> throw new Exception("invalid coin type: " + s);
        };
    }
}
