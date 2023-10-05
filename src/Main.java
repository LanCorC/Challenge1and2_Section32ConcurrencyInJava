public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345-678", 1000.00);

        //Own code here  - create and start threads
        Thread t1 = new Thread(() -> {
            System.out.println("ping");
            account.deposit(300);
            account.withdraw(50);
        });
        Thread t2 = new Thread(() -> {
            System.out.println("pong");
            account.deposit(203.75);
            account.withdraw(100);
        });

        t1.start();
        t2.start();

//        new Thread(() -> {
//            try {
//                System.out.println("Starting sleep soon...");
//                Thread.sleep(500);
//                System.out.println("New balance: " + account.getBalance() + Thread.currentThread());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();
    }

}
