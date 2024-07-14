package cmpt213.asn4.bank;

import ca.cmpt213.asn4.bank.BankAccount;
import ca.cmpt213.asn4.bank.SavingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing for bankAccount and SavingsAccount
 */


class BankAccountTest {

    //testing constructor
    @Test
    public void testNegativeBalance() {
        try {
            new BankAccount(-100, 0.05) {};
            Assertions.fail("failed");
        } catch (IllegalArgumentException e) {
            //
        }
    }

    @Test
    public void testNegativeAnnualInterest() {
        try {
            new BankAccount(100, -0.05) {};
            Assertions.fail("failed");
        } catch (IllegalArgumentException e) {
            //
        }
    }

    // testing deposit
    @Test
    public void testNegativeDeposit() {
        BankAccount account = new BankAccount(100, 0.05) {};
        try {
            account.deposit(-50);
            Assertions.fail("failed");
        } catch (IllegalArgumentException e) {
            //
        }
    }

//    @Test
//    public void testDeposit(){
//        BankAccount account = new BankAccount(100, 0.05) {};
//        account.deposit(50);
//        Assertions.assertEquals(150, account.balance, 0.001);
//        Assertions.assertEquals(1, account.monthlyDeposits);
//    }

    // testing withdraw
    @Test
    public void testNegativeWithdraw() {
        BankAccount account = new BankAccount(100, 0.05) {};
        try{
            account.withdraw(-50);
            Assertions.fail("failed");
        } catch (IllegalArgumentException e) {
            //
        }
    }

    @Test
    public void testWithdrawBalance(){
        try {
            BankAccount account = new BankAccount(100, 0.05) {
            };
            account.withdraw(200);
            Assertions.fail("failed");
        } catch (IllegalArgumentException e) {
            //
        }
    }

    @Test
    public void testWithdraw() {
        BankAccount account = new BankAccount(100, 0.05) {};
        account.withdraw(50);
        Assertions.assertEquals(50, account.balance, 0.001);
        Assertions.assertEquals(1, account.monthlyWithdrawals);
    }

    // testing calcIntrest
    @Test
    public void testCalcInterest() {
        BankAccount account = new BankAccount(100, 6) {};
        account.calcInterest();
        Assertions.assertEquals(150.00, account.balance, 0.001);
    }

    // testing monthlyProcess
    @Test
    public void testMonthlyProcess() {
        BankAccount account = new BankAccount(100, 6) {};
        account.monthlyServiceCharges = 10;
        account.monthlyProcess();
        Assertions.assertEquals(135.0, account.balance, 0.01);
        Assertions.assertEquals(0, account.monthlyDeposits);
        Assertions.assertEquals(0, account.monthlyWithdrawals);
        Assertions.assertEquals(0, account.monthlyServiceCharges, 0);
    }

    // testing saving account constructor
    @Test
    public void testSavingAccountActive() {
        SavingAccount account = new SavingAccount(100, 6) {};
        Assertions.assertTrue(account.isActive);
    }

    @Test
    public void testSavingAccountInactive() {
        SavingAccount account = new SavingAccount(10, 6) {};
        Assertions.assertFalse(account.isActive);
    }

    // testing saving account deposit
    @Test
    public void testSavingAccountDeposit() {
        SavingAccount account = new SavingAccount(20, 6) {};
        account.deposit(20);
        Assertions.assertTrue(account.isActive);
        Assertions.assertEquals(40, account.balance, 0.001);
    }

    // testing saving account withdraw
    @Test
    public void testSavingAccountWithdrawActive() {
        SavingAccount account = new SavingAccount(100, 6) {};
        account.withdraw(20);
        Assertions.assertEquals(80, account.balance, 0.001);
        Assertions.assertTrue(account.isActive);
    }

    @Test
    public void testSavingAccountWithdrawInactive() {
        SavingAccount account = new SavingAccount(20, 0.05);
        try {
            account.withdraw(10);
            Assertions.fail("failed");
        } catch (IllegalArgumentException e) {
            //
        }
    }

    // testing savings account monthly process
    @Test
    public void testSavingAccountMonthlyProcess(){
        SavingAccount account = new SavingAccount(100, 6) {};
        account.withdraw(10);
        account.withdraw(10);
        account.withdraw(10);
        account.withdraw(10);
        account.withdraw(10);
        // 5 times
        account.monthlyProcess();
        Assertions.assertEquals(73.5, account.balance, 0.001);
    }
}