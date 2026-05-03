package com.kyleengler.commonwealthsavers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testTransfer() {
        Account source = new Account("source", 10000.0);
        Account target1 = new Account("t1", 0.0);
        Account target2 = new Account("t2", 0.0);

        source.transfer(100.10, target1, target2);

        assertEquals(9799.80, source.getBalance());
        assertEquals(100.10, target1.getBalance());
        assertEquals(100.10, target2.getBalance());
    }

    @Test
    void testTransferErrors() {
        Account source = new Account("source", 10000.0);
        Account target1 = new Account("t1", 0.0);
        Account target2 = new Account("t2", 0.0);
        assertThrows(InsufficientBalanceException.class, () -> {
            source.transfer(99999.9, target1, target2);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            // Account.transfer cannot be called with 0 target accounts
            source.transfer(1.0);
        });
    }

    @Test
    void testTransferEvenlyErrors() {
        Account source = new Account("source", 10000.0);
        assertThrows(IllegalArgumentException.class, () -> {
            // Account.transferEvenly cannot be called with 0 target accounts
            source.transferEvenly();
        });
    }

    @Test
    void testTransferEvenly() {
        double startingSourceBalance = 10000.0;
        Account source = new Account("source", startingSourceBalance);
        Account target1 = new Account("t1", 0.0);
        Account target2 = new Account("t2", 0.0);
        Account target3 = new Account("t3", 0.0);

        source.transferEvenly(target1, target2, target3);

        assertEquals(0.0, source.getBalance());
        assertEquals(3333.34, target1.getBalance());
        assertEquals(3333.33, target2.getBalance());
        assertEquals(3333.33, target3.getBalance());
        double totalTargetBalance = target1.getBalance() + target2.getBalance() + target3.getBalance();
        assertEquals(startingSourceBalance, totalTargetBalance);
    }
}