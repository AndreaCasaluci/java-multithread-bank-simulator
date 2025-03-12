# Bank Account Simulator - Thread Safety and Performance Comparison

This Java project demonstrates the impact of multi-threading and thread safety through a Bank Account Simulator. The goal is to showcase how multi-threading can improve performance and how thread safety mechanisms ensure correctness when multiple threads access shared resources.

## Key Concepts

### **Multi-threading in Java**
Multi-threading allows multiple threads to run concurrently, which can help to improve the performance of applications, especially when performing independent tasks in parallel. Java provides robust support for multi-threading through the `Thread` class and the `Runnable` interface. However, multi-threading introduces challenges, especially when multiple threads attempt to modify shared resources, leading to potential issues like race conditions and data inconsistencies.

### **Thread-Safe Operations**
A **thread-safe operation** is one that functions correctly when accessed by multiple threads concurrently. Thread-safe operations avoid issues like race conditions, where the outcome of operations depends on the order of execution.

Java provides tools for ensuring thread safety, such as:
- **Atomic classes** (`AtomicInteger`, `AtomicBoolean`, etc.), which ensure atomic (uninterrupted) operations on data.
- **Synchronized blocks**: These help by ensuring only one thread can access critical sections of the code at any time.
- **Locks**: Using `ReentrantLock` to provide more control over thread synchronization.

### **Race Condition**
A **race condition** occurs when two or more threads try to modify a shared resource at the same time, leading to unpredictable results. For instance, when two threads try to withdraw money from a bank account simultaneously, the account balance may be incorrectly updated if the withdrawal operations are not synchronized properly.

### **Non-Thread-Safe Operations**
In a non-thread-safe operation, when multiple threads access shared data (like a bank account balance), they can interfere with each other. Without proper synchronization, this can lead to incorrect data being stored or modified.

In this project, we will compare the performance and correctness of:
- **Non-threaded operations** (sequential execution).
- **Threaded operations** (simultaneous execution by multiple threads).
- **Thread-safe operations** (using atomic classes for correct data management).
- **Non-thread-safe operations** (without synchronization).

## Project Overview

The Bank Account Simulator consists of two main components:
1. **Non-thread-safe Bank Account**: A simple bank account class that allows deposits and withdrawals but does not handle concurrency.
2. **Thread-safe Bank Account**: A bank account class that uses `AtomicInteger` to ensure thread-safe operations.

### Features:
- **Non-threaded test**: Sequential deposit and withdrawal operations.
- **Threaded test**: Multiple threads performing deposit and withdrawal operations concurrently.
- **Thread safety comparison**: Comparison of thread-safe and non-thread-safe operations.

## Example Output:
```
Starting non-threaded test...
Non-threaded operations took: 1500000 nanoseconds
Starting threaded test on non-thread-safe account...
Threaded operations (non-thread-safe) took: 4500000 nanoseconds
Final balance (non-thread-safe, threaded): 12000
Starting threaded test on thread-safe account...
Threaded operations (thread-safe) took: 3500000 nanoseconds
Final balance (thread-safe, threaded): 12000
```
