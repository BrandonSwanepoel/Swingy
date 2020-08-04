package com.bswanepo;

import java.io.Console;

public class App {
    public static void main(String[] args) {
        ConsoleQuestions consoleQuestions = new ConsoleQuestions();
        boolean createdHero = false;
        String result = null;
        Console c = System.console();
        if (c != null) {

            do {
                c.printf("\nDo you want to create a Hero? Y/N.\n");
                result = c.readLine();
                if (result.equals("Y") || result.equals("y")) {
                    createdHero = true;
                    createdHero = consoleQuestions.CreateHero(createdHero);
                } else if (result.equals("N") || result.equals("n")) {
                    c.printf("\nDo you want to Select a Hero? Y/N.\n");
                    if (c.readLine().equals("Y")) {
                        createdHero = consoleQuestions.SelectHero(createdHero);
                } else {
                    c.printf("\nPlease pick Y or N\n");

                }
            }
            } while (createdHero == false);
        }
    }
}