package accounts;

public interface Account {

	long deposit(long amount);

	long withdraw(long amount);

	long getBalance();

	Client getOwner();

	long getId();

	int getLimit();

	void setLimit(int limit);

}