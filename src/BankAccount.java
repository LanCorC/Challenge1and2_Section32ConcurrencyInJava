import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private double balance;
    private String accountNumber;
    private ReentrantLock lock;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        lock = new ReentrantLock();
    }

    public void deposit(double amount) {

        boolean status = false;

        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    balance += amount;
                    status = true;
//                    Thread.sleep(2000); //code to force the 'else' path
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock.");
            }
        } catch (InterruptedException e) {
            System.out.println("Something went wrong: " + e);
        }

        System.out.println("Transaction status = " + status);


    }

    public synchronized void withdraw(double amount) {
        boolean status = false;

        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    balance -= amount;
                    status = true;
//                    Thread.sleep(2000); //code to force the 'else' path
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock.");
            }
        } catch (InterruptedException e) {
            System.out.println("Something went wrong: " + e);
        }

        System.out.println("Transaction status = " + status);
    }

    public double getBalance() {
         return balance;
     }

     public String getAccountNumber() {
        return accountNumber;
     }

     public void printAccountNumber() {
         System.out.println("Account number = " + accountNumber);
     }
 }