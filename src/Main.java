public class Main {
    public static void main(String[] args) {
//        BankAccount account = new BankAccount("12345-678", 1000.00);
//
//        //Own code here  - create and start threads
//        Thread t1 = new Thread(() -> {
//            System.out.println("ping");
//            account.deposit(300);
//            account.withdraw(50);
//        });
//        Thread t2 = new Thread(() -> {
//            System.out.println("pong");
//            account.deposit(203.75);
//            account.withdraw(100);
//        });
//
//        t1.start();
//        t2.start();

        //Below commented prints out the sum
        //Expected result: sum of 1353.75

//        new Thread(() -> {
//            try {
//                System.out.println("Starting sleep soon...");
//                Thread.sleep(500);
//                System.out.println("New balance: " + account.getBalance() + Thread.currentThread());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();

        //Challenge7
        BankAccount lance = new BankAccount("12312", 10000);
        BankAccount sleep = new BankAccount("21321", 500);

        Transfer t1 = new Transfer(lance, sleep, 7500);
        Transfer t2 = new Transfer(sleep, lance, 8000);

        //expectation: 0 is new balance of sleep, 21321

        new Thread(t1).start();
        new Thread(t2).start();


//        lance.transfer(sleep, 15000);
//        lance.transfer(sleep, 10000.50);
//        lance.transfer(sleep, 7500);
//
//        sleep.transfer(lance, 8000);
//        sleep.transfer(lance, 0.50);
    }

}
