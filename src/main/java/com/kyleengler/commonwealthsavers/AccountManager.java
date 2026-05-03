package com.kyleengler.commonwealthsavers;

public class AccountManager {

    public static void main(String[] args) {
        System.out.println("Running in demo mode.");
        runDemo();
    }

    private static void runDemo() {
        // Demo transfer funds evenly
        Account source = new Account("source", 10000.0);
        Account target1 = new Account("target1", 0.0);
        Account target2 = new Account("target2", 0.0);
        Account target3 = new Account("target3", 0.0);

        System.out.println("Accounts initialized.");
        printAccounts(source, target1, target2, target3);

        System.out.println("Transferring funds evenly between target accounts.");
        System.out.println("Initializing transfer...");
        source.transferEntireBalanceEvenly(target1, target2, target3);

        System.out.println("Transfer completed.");
        printAccounts(source, target1, target2, target3);

        // Demo distribute funds
        System.out.println("\n================================\n");
        System.out.println("Accounts reinitialized.");
        double transferAmount = 100.10;
        source = new Account("source", 10000.0);
        target1 = new Account("target1", 0.0);
        target2 = new Account("target2", 0.0);
        target3 = new Account("target3", 0.0);
        printAccounts(source, target1, target2, target3);

        System.out.printf("Transferring $%.2f from source to each target.\n", transferAmount);
        System.out.println("Initializing transfer...");
        source.transfer(transferAmount, target1, target2, target3);

        System.out.println("Transfer completed.");
        printAccounts(source, target1, target2, target3);
    }

    public static void printAccounts(Account... accounts) {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
