package com.kyleengler.commonwealthsavers;

public class AccountManager {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("Arg: " + arg);
        }
        Account source = new Account("source", 10000.0);
        Account target1 = new Account("target1", 0.0);
        Account target2 = new Account("target2", 0.0);
        Account target3 = new Account("target3", 0.0);

        System.out.println("Accounts initialized.");
        printAccounts(source, target1, target2, target3);

        System.out.println("Initializing transfer...");
        source.transferEvenly(target1, target2, target3);

        System.out.println("Transfer completed.");
        printAccounts(source, target1, target2, target3);
    }

    public static void printAccounts(Account... accounts) {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
