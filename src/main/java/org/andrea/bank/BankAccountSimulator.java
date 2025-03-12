package org.andrea.bank;

import java.util.concurrent.ThreadLocalRandom;

public class BankAccountSimulator {

    public static void main(String[] args) {
        // Test with non-threaded operations
        System.out.println("Starting non-threaded test...");
        long startTime = System.nanoTime();
        nonThreadedTest();
        long endTime = System.nanoTime();
        System.out.println("Non-threaded operations took: " + (endTime - startTime) + " nanoseconds\n");

        // Test with threaded operations on non-thread-safe account
        System.out.println("Starting threaded test on non-thread-safe account...");
        startTime = System.nanoTime();
        threadedTestNonThreadSafe();
        endTime = System.nanoTime();
        System.out.println("Threaded operations (non-thread-safe) took: " + (endTime - startTime) + " nanoseconds\n");

        // Test with threaded operations on thread-safe account
        System.out.println("Starting threaded test on thread-safe account...");
        startTime = System.nanoTime();
        threadedTestThreadSafe();
        endTime = System.nanoTime();
        System.out.println("Threaded operations (thread-safe) took: " + (endTime - startTime) + " nanoseconds\n");

        // Test transaction history comparison
        System.out.println("Starting transaction history test...");
        testTransactionHistory();

        // Test account storage comparison
        System.out.println("Starting account storage test...");
        testAccountStorage();
    }

    // Non-threaded test method
    public static void nonThreadedTest() {
        NonThreadSafeBankAccount account = new NonThreadSafeBankAccount(1000);
        for (int i = 0; i < 1000; i++) {
            account.deposit(10);
            account.withdraw(10);
        }
        System.out.println("Final balance (non-threaded): " + account.getBalance());
    }

    // Threaded test on non-thread-safe bank account
    public static void threadedTestNonThreadSafe() {
        NonThreadSafeBankAccount account = new NonThreadSafeBankAccount(1000);
        runThreads(account);
        System.out.println("Final balance (non-thread-safe, threaded): " + account.getBalance());
    }

    // Threaded test on thread-safe bank account
    public static void threadedTestThreadSafe() {
        ThreadSafeBankAccount account = new ThreadSafeBankAccount(1000);
        runThreads(account);
        System.out.println("Final balance (thread-safe, threaded): " + account.getBalance());
    }

    // Helper method to run threaded operations
    private static void runThreads(BankAccount account) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    account.deposit(10);
                    account.withdraw(10);
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Test transaction history (ArrayList vs. CopyOnWriteArrayList)
    public static void testTransactionHistory() {
        NonThreadSafeBankAccount nonSafeAccount = new NonThreadSafeBankAccount(1000);
        ThreadSafeBankAccount safeAccount = new ThreadSafeBankAccount(1000);
        runThreads(nonSafeAccount);
        runThreads(safeAccount);
        System.out.println("Non-thread-safe transaction history size: " + nonSafeAccount.getTransactionHistory().size());
        System.out.println("Thread-safe transaction history size: " + safeAccount.getTransactionHistory().size()+"\n");
    }

    // Test account storage (HashMap vs. ConcurrentHashMap)
    public static void testAccountStorage() {
        NonThreadSafeAccountManager nonSafeManager = new NonThreadSafeAccountManager();
        ThreadSafeAccountManager safeManager = new ThreadSafeAccountManager();
        runStorageThreads(nonSafeManager);
        runStorageThreads(safeManager);
        System.out.println("Total accounts in non-thread-safe storage: " + nonSafeManager.getTotalAccounts());
        System.out.println("Total accounts in thread-safe storage: " + safeManager.getTotalAccounts());

    }

    // Helper method to run threaded account storage operations
    private static void runStorageThreads(AccountManager manager) {
        Thread[] threads = new Thread[200]; // High contention with many threads
        for (int i = 0; i < 200; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) { // Each thread adds 1000 accounts
                    String accountId = "Acc" + ThreadLocalRandom.current().nextInt(10000); // Random account ID
                    manager.addAccount(accountId, 0);
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
