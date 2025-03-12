package org.andrea.bank;

import java.util.ArrayList;
import java.util.List;

public class NonThreadSafeBankAccount implements BankAccount {
    private int balance;
    private List<String> transactionHistory;

    public NonThreadSafeBankAccount(int initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>(); // Not Thread-Safe
    }

    public void deposit(int amount) {
        balance += amount;  // Not atomic, vulnerable to race conditions
        transactionHistory.add("Deposit: " + amount);
    }

    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;  // Not atomic, vulnerable to race conditions
            transactionHistory.add("Withdraw: " + amount);
            return true;
        }
        return false;
    }

    public int getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

