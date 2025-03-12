package org.andrea.bank;

import java.util.List;

public interface BankAccount {
    void deposit(int amount);

    boolean withdraw(int amount);

    int getBalance();

    List<String> getTransactionHistory();
}
