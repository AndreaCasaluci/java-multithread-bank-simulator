package org.andrea.bank;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeBankAccount implements BankAccount {

    // Using AtomicInteger to handle balance in a thread-safe manner
    private AtomicInteger balance;
    private List<String> transactionHistory;

    public ThreadSafeBankAccount(int initialBalance) {
        this.balance = new AtomicInteger(initialBalance);
        this.transactionHistory = new CopyOnWriteArrayList<>(); // Thread-safe
    }

    // Deposit method
    public void deposit(int amount) {
        balance.addAndGet(amount);
        transactionHistory.add("Deposit: " + amount);
    }

    // Withdraw method with synchronization to ensure thread safety
    public boolean withdraw(int amount) {
        // Check if enough balance is available
        if (balance.get() >= amount) {
            // Atomic operation, thread-safe withdrawal
            balance.addAndGet(-amount);
            transactionHistory.add("Withdraw: " + amount);
            return true;
        }
        return false;
    }

    // Get the current balance
    public int getBalance() {
        return balance.get();
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
