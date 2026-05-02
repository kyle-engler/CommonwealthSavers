package com.kyleengler.commonwealthsavers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class Account {
    private final String id;
    private String name;
    private Double balance;

    public Account(Double balance) {
        this(null, balance);
    }

    public Account(String name, Double balance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    /**
     * Transfer provided amount to each provided target account.
     * @param amount    balance to transfer to each target account
     * @param accounts  list of account(s) to receive provided funds
     * @throws InsufficientBalanceException if balance is too low to make transfers
     */
    public void transfer(Double amount, Account... accounts) {
        if (accounts.length == 0) {
            throw new IllegalArgumentException("Target accounts argument cannot be empty");
        }
        Double total = amount * accounts.length;
        if (total > balance) {
            throw new InsufficientBalanceException(String.format("Error total transfer amount $%.2f is greater than " +
                    "source account balance of $%.2f", total, this.getBalance()));
        }
        this.balance -= total;
        for (Account account : accounts) {
            account.balance += amount;
        }

        // Check for rounding errors. If balance is less than 1 cent round down to 0.00
        if (this.balance.compareTo(0.01) < 0) {
            this.balance = 0.0;
        }
    }

    /**
     * Transfer entire balance of this Account to provided target account(s). If balance cannot be divided evenly between
     * accounts then put remainder in the first target account.
     * Post condition - this Account has a balance of $0.00
     * @param accounts  account(s) to receive funds.
     */
    public void transferEvenly(Account... accounts) {
        if (accounts.length == 0) {
            throw new IllegalArgumentException("Target accounts argument cannot be empty");
        }
        Double amount = roundCurrency(this.balance / accounts.length);
        Double remainder = roundCurrency(this.balance % (amount * accounts.length));

        this.transfer(amount, accounts);
        if (remainder > 0.0) {
            this.transfer(remainder, accounts[0]);
        }
    }

    static Double roundCurrency(Double value) {
        BigDecimal bd = new BigDecimal(value);
        BigDecimal rounded = bd.setScale(2, RoundingMode.FLOOR);
        return rounded.doubleValue();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        if (this.name != null) {
            sb.append(", name='").append(name).append('\'');
        }
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}