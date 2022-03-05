package banking;

public class AdultAccount extends Account implements Savings, Chequing {

    public AdultAccount(double balance) {
        super(balance);
    }

    public void setBalance(double balance) {
        super.setBalance(balance);
    }

    @Override
    public double checkBalance() {
        return super.getBalance();
    }

    @Override
    public void save(Double amount) {
        super.setBalance(super.getBalance() + amount);
    }

    @Override
    public void saveWithInterest(Double amount, Double interest) {
        super.setBalance(getBalance() + (amount * interest));
        System.out.println("Normal interest savings => " + getBalance());
    }
}
