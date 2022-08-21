package models;

public enum Coin {
    TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100), TWO_HUNDRED(200);
    public final int value;

    Coin(int value) {
        this.value = value;
    }
}