package org.andrea.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeAccountManager implements AccountManager {
    private Map<String, ThreadSafeBankAccount> accounts;

    public ThreadSafeAccountManager() {
        this.accounts = new ConcurrentHashMap<>();
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
