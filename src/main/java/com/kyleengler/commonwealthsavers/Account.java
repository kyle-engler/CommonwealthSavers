package com.kyleengler.commonwealthsavers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class Account {
    private final String id;
    private final String name;
    // Double object has to access compareTo()
    private Double balance;

    public Account(double balance) {
        this(null, balance);
    }

    public Account(String name, double balance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Transfer provided amount to target Account.
     *
     * @param amount  balance to transfer to each target account
     * @param account Account to receive provided funds
     * @throws InsufficientBalanceException if balance is too low to make transfers
     */
    public void transfer(double amount, Account account) {
        if (amount > getBalance()) {
            throw new InsufficientBalanceException(String.format("Error total transfer amount $%.2f is greater than " +
                    "source account balance of $%.2f", amount, getBalance()));
        }
        balance -= amount;
        account.balance += amount;

        // Check for rounding errors. If balance is less than 1 cent round down to 0.00
        if (balance.compareTo(0.01) < 0) {
            balance = 0.0;
        }
    }

    /**
     * Transfer provided amount to each provided target account.
     *
     * @param amount   balance to transfer to each target account
     * @param accounts list of account(s) to receive provided funds
     * @throws InsufficientBalanceException if balance is too low to make transfers
     */
    public void transfer(double amount, Account... accounts) {
        if (accounts.length == 0) {
            throw new IllegalArgumentException("Target accounts argument cannot be empty");
        }
        double total = amount * accounts.length;
        if (total > getBalance()) {
            throw new InsufficientBalanceException(String.format("Error total transfer amount $%.2f is greater than " +
                    "source account balance of $%.2f", total, getBalance()));
        }
        for (Account account : accounts) {
            transfer(amount, account);
        }
    }

    /**
     * Transfer entire balance of this Account to provided target account(s). If balance cannot be divided evenly between
     * accounts then put remainder in the first target account.
     * Post condition - this Account has a balance of $0.00
     *
     * @param accounts account(s) to receive funds.
     */
    public void transferEntireBalanceEvenly(Account... accounts) {
        if (accounts.length == 0) {
            throw new IllegalArgumentException("Target accounts argument cannot be empty");
        }
        if (getBalance() == 0.0) {
            // No balance to transfer
            return;
        }
        double transferAmount = Math.max(roundCurrency(getBalance() / accounts.length), 0.01);
        for (Account account : accounts) {
            try {
                transfer(transferAmount, account);
            } catch (InsufficientBalanceException e) {
                // No more funds to transfer
                break;
            }
        }

        // If funds can't be distributed completely evenly give remainder to first account
        if (getBalance() > 0.0) {
            transfer(getBalance(), accounts[0]);
        }
    }

    static double roundCurrency(double value) {
        BigDecimal bd = new BigDecimal(value);
        BigDecimal rounded = bd.setScale(2, RoundingMode.FLOOR);
        return rounded.doubleValue();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        if (name != null) {
            sb.append(", name='").append(name).append('\'');
        }
        sb.append(", balance=").append(String.format("$%.2f", getBalance()));
        sb.append('}');
        return sb.toString();
    }
}