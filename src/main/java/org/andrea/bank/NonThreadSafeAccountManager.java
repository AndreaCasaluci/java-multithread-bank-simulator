package org.andrea.bank;

import java.util.HashMap;
import java.util.Map;

public class NonThreadSafeAccountManager implements AccountManager {
    private Map<String, ThreadSafeBankAccount> accounts;

    public NonThreadSafeAccountManager() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(String accountId, int initialBalance) {
        accounts.put(accountId, new ThreadSafeBankAccount(initialBalance));
    }

    public BankAccount getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public int getTotalAccounts() {
        return this.accounts.size();
    }
}
