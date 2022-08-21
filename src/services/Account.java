package services;

public interface Account<T> {
    void deposit(T item, int amount);

    void withdraw(T item, int amount);

    void setBalance(T item, int amount);

    int getBalance(T item);
}
