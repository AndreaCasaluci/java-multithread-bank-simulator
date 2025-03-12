package org.andrea.bank;

public class BankAccountSimulator {

    public static void main(String[] args) {
        // Test with non-threaded operations
        System.out.println("Starting non-threaded test...");
        long startTime = System.nanoTime();
        nonThreadedTest();
        long endTime = System.nanoTime();
        System.out.println("Non-threaded operations took: " + (endTime - startTime) + " nanoseconds");

        // Test with threaded operations on non-thread-safe account
        System.out.println("Starting threaded test on non-thread-safe account...");
        startTime = System.nanoTime();
        threadedTestNonThreadSafe();
        endTime = System.nanoTime();
        System.out.println("Threaded operations (non-thread-safe) took: " + (endTime - startTime) + " nanoseconds");

        // Test with threaded operations on thread-safe account
        System.out.println("Starting threaded test on thread-safe account...");
        startTime = System.nanoTime();
        threadedTestThreadSafe();
        endTime = System.nanoTime();
        System.out.println("Threaded operations (thread-safe) took: " + (endTime - startTime) + " nanoseconds");
    }

    // Non-threaded test method
    public static void nonThreadedTest() {
        NonThreadSafeBankAccount account = new NonThreadSafeBankAccount(1000);

        // Sequential operations (non-threaded)
        for (int i = 0; i < 1000; i++) {
            account.deposit(10);
            account.withdraw(10);
        }

        System.out.println("Final balance (non-threaded): " + account.getBalance());
    }

    // Threaded test on non-thread-safe bank account
    public static void threadedTestNonThreadSafe() {
        NonThreadSafeBankAccount account = new NonThreadSafeBankAccount(1000);

        // Creating 10 threads for deposit and withdrawal
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

        // Wait for all threads to finish
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final balance (non-thread-safe, threaded): " + account.getBalance());
    }

    // Threaded test on thread-safe bank account
    public static void threadedTestThreadSafe() {
        ThreadSafeBankAccount account = new ThreadSafeBankAccount(1000);

        // Creating 10 threads for deposit and withdrawal
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

        // Wait for all threads to finish
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final balance (thread-safe, threaded): " + account.getBalance());
    }
}

