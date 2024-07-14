package ca.cmpt213.asn4.bank;

/**
 * This class extends BankAccount and creates a savings account which checks if the account has a balance greater than $25,
 * whenever a transaction is made. If the balance in the account is less than $25, then the account becomes inactive.
 */

public class SavingAccount extends BankAccount {
    public boolean isActive = false;

    public SavingAccount(int balance, double annualInterestRate) {
        super(balance, annualInterestRate);
        if(balance >= 25){
            this.isActive = true;
        }
    }

    @Override
    public void withdraw(double amount) {
        if(!isActive){
            throw new IllegalArgumentException("Account is not active");
        }
        super.withdraw(amount);
        if(balance < 25) {
            isActive = false;
        }
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        if(!isActive && balance >= 25) {
            isActive = true;
        }
    }

    @Override
    public void monthlyProcess(){
        if(monthlyWithdrawals > 4) {
            monthlyServiceCharges += (monthlyWithdrawals-4);
        }
        super.monthlyProcess();
        if(balance < 25) {
            isActive = false;
        }
    }
}
