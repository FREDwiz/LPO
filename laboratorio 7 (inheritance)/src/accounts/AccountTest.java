package accounts;

public class AccountTest {

	public static void main(String[] args) {
		Client alice = new Person("Alice", "Ford");
		HistoryAccount aliceAccount = HistoryCreditAccount.newOfLimitBalance(-1000, 1000, alice);
		System.out.println( aliceAccount.withdraw(1000) == 0);
		System.out.println( aliceAccount.redo() == -1000);
		System.out.println( aliceAccount.undo() == 0);
		System.out.println( aliceAccount.deposit(2000) == 2000);
		System.out.println( aliceAccount.redo() == 4000);
		System.out.println( aliceAccount.redo() == 6000);
		System.out.println( aliceAccount.undo() == 4000);
		System.out.println( aliceAccount.getBalance() == 4000);
		System.out.println( aliceAccount.getLimit() == -1000);
	}

}
