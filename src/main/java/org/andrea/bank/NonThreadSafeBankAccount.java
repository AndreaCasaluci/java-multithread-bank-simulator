package org.andrea.bank;

public class NonThreadSafeBankAccount {
    private int balance;

    public NonThreadSafeBankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        balance += amount;  // Not atomic, vulnerable to race conditions
    }

    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;  // Not atomic, vulnerable to race conditions
            return true;
        }
        return false;
    }

    public int getBalance() {
        return balance;
    }
}

