package org.andrea.bank;

public interface AccountManager {

    void addAccount(String accountId, int initialBalance);

    BankAccount getAccount(String accountId);

    int getTotalAccounts();
}
