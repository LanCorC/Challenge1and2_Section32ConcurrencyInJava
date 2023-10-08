import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount implements Challenge7 {

    private double balance;
    private String accountNumber;
    private ReentrantLock lock;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        lock = new ReentrantLock();
    }

    public boolean deposit(double amount) {

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
        } finally {
            System.out.println("Transaction status = " + status);
        }

        return status;
    }

    public boolean withdraw(double amount) {
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
        } finally {
            System.out.println("Transaction status = " + status);
        }

        return status;

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

    public boolean transfer(BankAccount destinationAccount, double amount) {
        System.out.println("Attempting to send " + amount + " to " + destinationAccount.getAccountNumber());
        return Challenge7.super.transfer(destinationAccount, this, amount);
    }
}