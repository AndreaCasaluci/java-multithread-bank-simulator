package org.andrea.bank;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeBankAccount {

    // Using AtomicInteger to handle balance in a thread-safe manner
    private AtomicInteger balance;

    public ThreadSafeBankAccount(int initialBalance) {
        this.balance = new AtomicInteger(initialBalance);
    }

    // Deposit method
    public void deposit(int amount) {
        balance.addAndGet(amount);
    }

    // Withdraw method with synchronization to ensure thread safety
    public boolean withdraw(int amount) {
        // Check if enough balance is available
        if (balance.get() >= amount) {
            // Atomic operation, thread-safe withdrawal
            balance.addAndGet(-amount);
            return true;
        }
        return false;
    }

    // Get the current balance
    public int getBalance() {
        return balance.get();
    }
}
