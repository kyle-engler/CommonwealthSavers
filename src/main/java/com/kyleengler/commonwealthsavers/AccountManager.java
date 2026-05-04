package com.kyleengler.commonwealthsavers;

public class AccountManager {

    public static void main(String[] args) {
        System.out.println("Running in demo mode.");
        try {
            runDemo();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private static void runDemo() {
        transferEntireBalanceEvenly(10000.0, 3);
        System.out.println("\n======================================\n");
        transferBalance(10000, 3, 100.10);
    }

    /**
     * Helper function to create a source account and transfer given amount to each target account
     *
     * @param sourceBalance         Starting balance for source account, must be greater than 0
     * @param targetAccountsCount   Number of target accounts to create that will receive funds
     * @param transferAmount        Amount to tranfer from source account to each target account
     */
    private static void transferBalance(double sourceBalance, int targetAccountsCount, double transferAmount) {
        Account source = new Account("source", sourceBalance);
        Account[] targets = new Account[targetAccountsCount];
        for (int i = 0; i < targetAccountsCount; i++) {
            targets[i] = new Account("target" + i, 0.0);
        }
        System.out.println("Accounts initialized.");
        printAccounts(source);
        printAccounts(targets);
        System.out.printf("Transferring $%.2f from source to each target.\n", transferAmount);
        System.out.println("Initializing transfer...");
        source.transfer(transferAmount, targets);
        System.out.println("Transfer completed.");
        printAccounts(source);
        printAccounts(targets);
    }

    /**
     * Helper function to create a funding source account and transfer all funds evenly between all target accounts
     *
     * @param sourceBalance         Starting balance for source account, must be greater than 0
     * @param targetAccountsCount   Number of target accounts to create that will receive funds
     */
    private static void transferEntireBalanceEvenly(double sourceBalance, int targetAccountsCount) {
        Account source = new Account("source", sourceBalance);
        Account[] targets = new Account[targetAccountsCount];
        for (int i = 0; i < targetAccountsCount; i++) {
            targets[i] = new Account("target" + i, 0.0);
        }
        System.out.println("Accounts initialized.");
        printAccounts(source);
        printAccounts(targets);
        System.out.println("Transferring funds evenly between target accounts.");
        System.out.println("Initializing transfer...");
        source.transferEntireBalanceEvenly(targets);
        System.out.println("Transfer completed.");
        printAccounts(source);
        printAccounts(targets);
    }

    private static void printAccounts(Account... accounts) {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
