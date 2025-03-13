package mn.learning;

import java.util.Random;

public class User extends Thread{
	private BankAccount account;
	private Random random = new Random();

	public User(BankAccount account, String name) {
		super(name);
		this.account = account;
	}

	@Override

	public void run() {
		for (int i = 0; i < 5; i++) {
			int amount = random.nextInt(100) + 1;
			if (random.nextBoolean()) {
				account.deposit(amount);
			} else {
				account.withdraw(amount);
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
