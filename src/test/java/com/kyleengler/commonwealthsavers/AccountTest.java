package com.kyleengler.commonwealthsavers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @BeforeEach
    void setUp() {

    }

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
            source.transfer(100000.0, target1, target2);
        });
    }

    @Test
    void testTransferEvenly() {
        Account source = new Account("source", 10000.0);
        Account target1 = new Account("t1", 0.0);
        Account target2 = new Account("t2", 0.0);
        Account target3 = new Account("t3", 0.0);

        source.transferEvenly(target1, target2, target3);

        assertEquals(0.0, source.getBalance());
        assertEquals(3333.34, target1.getBalance());
        assertEquals(3333.33, target2.getBalance());
        assertEquals(3333.33, target3.getBalance());
    }
}