package com.kyleengler.commonwealthsavers;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private static final String TAG = AccountManager.class.getSimpleName();
    private static final String OPT_DEMO = "demo";
    private static final String OPT_INTERACTIVE = "interactive";

    public static void main(String[] args) {
        // Define options
        Options options = new Options();
        options.addOption(Option.builder()
                .longOpt(OPT_DEMO)
                .desc("Run in demo mode")
                .required(false)
                .build());

        options.addOption(Option.builder()
                .longOpt(OPT_INTERACTIVE)
                .desc("Run in interactive mode")
                .required(false)
                .build());

        // Parse arguments
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Error parsing arguments: " + e.getMessage());
            formatter.printHelp(TAG, options);
            System.exit(1);
            return;
        }

        // Check flags
        if (cmd.hasOption(OPT_DEMO)) {
            System.out.println("Running in demo mode.");
            runDemo();
        } else if (cmd.hasOption(OPT_INTERACTIVE)) {
            System.out.println("Running in interactive mode.");
            runInteractive();
        } else {
            formatter.printHelp(TAG, options);
            System.out.println("Running in demo mode.");
            runDemo();
        }
    }

    private static void runInteractive() {
        System.out.println("Running in interactive mode...");
        System.out.println("Initializing source account.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Account sourceAccount = SystemAccountParser.readInAccount(reader);

        int accountsCount = SystemAccountParser.readIntegerInput("Enter number of target accounts to create:", reader);
        List<Account> accounts = new ArrayList<>(accountsCount + 1);
        accounts.add(sourceAccount);
        for (int i = 0; i < accountsCount; i++) {
            accounts.add(SystemAccountParser.readInAccount(reader));
        }

        System.out.println("Accounts initialized.");
        printAccounts(accounts.toArray(new Account[0]));
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
