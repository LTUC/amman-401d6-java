package banking;

public class KiddyAccount extends Account implements Savings {
    private double balance;

    public KiddyAccount() {
        super();
    }

    public KiddyAccount(double balance) {
        super(balance);
    }

    @Override
    public void saveWithInterest(Double amount, Double interest) {
        balance = (balance + (amount * Account.INTEREST)) * 2;

        System.out.println("Savings with extra interest => " + balance);
    }

    public double getBalance() {
        return balance;
    }
}
