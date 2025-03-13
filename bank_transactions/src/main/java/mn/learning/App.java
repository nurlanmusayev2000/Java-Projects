package mn.learning;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        BankAccount account = new BankAccount(500);
        User user1 = new User(account, "User1");
        User user2 = new User(account, "User2");
        User user3 = new User(account, "User3");

        user1.start();
        user2.start();
        user3.start();

        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Final balance" + account.getBalance());
    }
}
