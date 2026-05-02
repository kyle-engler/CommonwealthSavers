package com.kyleengler.commonwealthsavers;

import java.io.BufferedReader;
import java.io.IOException;

public class SystemAccountParser {

    private static double readDoubleInput(String message, BufferedReader reader) {
        while (true) {
            System.out.println(message);
            try {
                String input = reader.readLine().trim();
                if (input.isEmpty()) {
                    return 0.0;
                } else {
                    return Double.parseDouble(input);
                }
            } catch (IOException e) {
                System.err.println("Error parsing input: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing balance: " + e.getMessage());
            }
        }
    }

    static int readIntegerInput(String message, BufferedReader reader) {
        while (true) {
            System.out.println(message);
            try {
                return Integer.parseInt(reader.readLine().trim());
            } catch (IOException e) {
                System.err.println("Error parsing input: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing balance: " + e.getMessage());
            }
        }
    }

    private static String readStringInput(String message, BufferedReader reader) {
        while (true) {
            System.out.println(message);
            try {
                String input = reader.readLine().trim();
                if (!input.isEmpty()) {
                    return input;
                } else {
                    return null;
                }
            } catch (IOException e) {
                System.err.println("Error parsing input: " + e.getMessage());
            }
        }
    }

    public static Account readInAccount(BufferedReader reader) {
        String name = SystemAccountParser.readStringInput("Enter account name or leave blank for default:", reader);
        double balance = SystemAccountParser.readDoubleInput("Enter account starting balance or leave blank for $0.00:", reader);
        return new Account(name, balance);
    }
}
