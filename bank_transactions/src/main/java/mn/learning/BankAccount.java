package mn.learning;

public class BankAccount {
	private int balance;

	public BankAccount(int balance) {
		balance = this.balance;
	}


  public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + ". Balance: " + balance);
    }

	public synchronized void withdraw(int amount) {
		if (balance>amount) {
			System.out.println(Thread.currentThread().getName()+ " request to withdraw " +amount + "$");
			balance-= amount;
			System.out.println(" balance left " + balance);

		}
		else {
				System.out.println(Thread.currentThread().getName() + " request for withdraw " + amount + "$");
				System.out.println(Thread.currentThread().getName()+" insuffisent balance .Current balanc is " + balance + "$" );
			}
	};

	public int getBalance() {
		return balance;
	}

}
