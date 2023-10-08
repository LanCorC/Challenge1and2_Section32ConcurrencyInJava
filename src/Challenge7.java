public interface Challenge7 {
    default boolean transfer(BankAccount destinationAccount, BankAccount sourceAccount, double amount) {
        if (sourceAccount.withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                System.out.println("Source balance: " + sourceAccount.getBalance());
                System.out.println("Destination balance: " + destinationAccount.getBalance());
                return true;
            }
            else {
                // The deposit failed. Refund the money back into the account.
                System.out.printf("%s: Destination account busy. Refunding money\n",
                        Thread.currentThread().getName());
                destinationAccount.deposit(amount);
            }
        }
        System.out.println("Source balance: " + sourceAccount.getBalance());
        System.out.println("Destination balance: " + destinationAccount.getBalance());
        return false;
    }
}

class Transfer implements Runnable {
    private BankAccount sourceAccount, destinationAccount;
    private double amount;

    Transfer(BankAccount sourceAccount, BankAccount destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public void run() {
        while (!sourceAccount.transfer(destinationAccount, amount)) {
            System.out.println("Waiting...");
        }
//            continue;
        System.out.printf("%s completed\n", Thread.currentThread().getName());
    }
}
