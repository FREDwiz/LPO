package accounts;

public class HistoryCreditAccount extends CreditAccount implements HistoryAccount {

    private long prevAmount = -1;
    private boolean wasItWD = false;

    protected HistoryCreditAccount (int limit, long balance, Client client){

        super(limit,balance,client);

    }

    public static HistoryAccount newOfLimitBalance(int limit, long balance, Client client) {

        return new HistoryCreditAccount(limit, balance, client);
    }

    @Override
    public long deposit(long amount) {

        long result = super.deposit(amount);
        wasItWD = false;
        prevAmount = amount;

        return result;

    }

    @Override
    public long withdraw(long amount) {

        long result = super.withdraw(amount);
        wasItWD = true;
        prevAmount = amount;

        return result;

    }


    @Override
    public long undo() {

        if(prevAmount < 0) throw new IllegalStateException();

        long res;
        if(wasItWD) res = super.deposit(prevAmount);
        else res = super.withdraw(prevAmount);

        return res;

    }

    @Override
    public long redo() {

        if(prevAmount < 0) throw new IllegalStateException();

        long res;
        if(!wasItWD) res = super.deposit(prevAmount);
        else res = super.withdraw(prevAmount);

        return res;
    }
}
