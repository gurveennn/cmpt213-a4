package ca.cmpt213.asn4.bank;

/**
 * This class is an abstract class which creates a bank account that the user can deposit, withdraw and
 * calculate their monthly interest in. At the end of each month it also resets the number of monthly
 * withdrawals and deposits
 */

public abstract class BankAccount {
    public double balance;
    public int monthlyDeposits;
    public int monthlyWithdrawals;
    protected double annualInterestRate;
    public double monthlyServiceCharges;

    protected BankAccount(int balance, double annualInterestRate) {
        if (balance < 0 || annualInterestRate < 0) {
            throw new IllegalArgumentException("Invalid balance: " + balance);
        }
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.monthlyDeposits = 0;
        this.monthlyWithdrawals = 0;
        this.monthlyServiceCharges = 0;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid amount: " + amount);
        }
        balance += amount;
        monthlyDeposits++;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid amount: " + amount);
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        } else {
            balance -= amount;
            monthlyWithdrawals++;
        }
    }

    public void calcInterest() {
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyInterest = balance * monthlyInterestRate;
        balance += monthlyInterest;

    }

    public void monthlyProcess(){
        balance -= monthlyServiceCharges;
        calcInterest();
        monthlyWithdrawals = 0;
        monthlyDeposits = 0;
        monthlyServiceCharges = 0;

    }

}
