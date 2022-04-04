package banking;

public class Member extends BankMember {

    private final String firstName;
    private final String lastName;
    private final String address;
    private String number;

    private Account account;
    private Savings savings;

    public Member(String firstName, String lastName, String address, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.number = number;
    }

    public Member(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public void applyForLoan(Double loanAmount) {
        System.out.println("I want to borrow => " + loanAmount + " and use my house as collateral");
    }

    public void openAccount(Account account) {
        this.account = account;
    }

    public void setSavings(Savings savings) {
        this.savings = savings;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public Account getAccount() {
        return account;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
