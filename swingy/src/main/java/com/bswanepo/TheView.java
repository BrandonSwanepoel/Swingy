package com.bswanepo;

import java.io.Console;
import java.util.ArrayList;

public class TheView {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    Console c = System.console();

    String createHero() {
        Functions.clearScreen();
        System.out.println("Do you want to create a Hero? [Y/N]" + ANSI_RED + " [" + ANSI_RESET
                + "Type exit to leave the game" + ANSI_RED + "]" + ANSI_RESET);
        return c.readLine();
    };

    String selectHero() {
        Functions.clearScreen();
        System.out.println("Do you want to Select a Hero? [Y/N]" + ANSI_RED + " [" + ANSI_RESET
                + "Type exit to leave the game" + ANSI_RED + "]" + ANSI_RESET);
        return c.readLine();
    }

    void exitMessage() {
        Functions.clearScreen();
        System.out.println(ANSI_GREEN + "Thank you for playing!" + ANSI_RESET);

    }

    void createHeroName() {
        System.out.print("Hero name: ");
    }

    void pickHeroClass() {
        System.out.print("Hero class: ");

    }

    void pickHeroByName() {
        System.out.println("\nPick one of the heroes above\n");
        System.out.print("Hero name: ");
    }

    void readyToStartGame() {
        System.out.println("Ready to start the Game? [Y/N]");
    }

    void readyToStartNextMission() {
        System.out.println("Ready to start the Next Mission? [Y/N]");

    }

    void doYouWantToExit() {
        System.out.println("Do you want to exit the Game? [Y/N]");
    }

    void heroCreated() {
        Functions.clearScreen();

        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Your hero is created!!");
        System.out.println("Let the games begin" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }

    void createHeroClasses() {
        System.out.println("       Hero classes");
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println("Wizard");
        System.out.println("Tank");
        System.out.println("Lazy");
        System.out.println("MonkeyKing");
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

        System.out.println("Pick one of the above classes");
    }

    void pickedHero(ArrayList<String> hero) {
        Functions.clearScreen();

        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "           You have picked" + ANSI_RESET);

        if (hero.get(0).length() > 15) {
            System.out.println(ANSI_GREEN + "           " + hero.get(0) + "!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "            " + hero.get(0) + "!" + ANSI_RESET);
        }
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

        for (final String element : hero) {
            if (element != hero.get(0)) {
                if (element.length() > 15) {
                    System.out.println("       " + element);

                } else {
                    System.out.println("           " + element);

                }
            }
        }
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

    }

    void pickValidOption(String version) {
        Functions.clearScreen();

        if (version == "Y/N") {
            System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~");
            System.out.println("Please pick Y or N");
            System.out.println("~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        } else if (version == "Empty") {
            System.out.println("Error: Field is Empty or has invalid format\n");

        } else if (version == "Exists") {
            System.out.println("Error: Hero already exists!");

        } else if (version == "Class does not exist") {
            System.out.println("Error: Class Does not exist!");
        } else if (version == "Valid hero") {
            System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Please pick a valid Hero");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        } else if (version == "Valid option") {
            System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Please give a valid option");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        }
    }

    // Game play
    void braveOne() {
        System.out.println("Lets see how you did brave one ...");
    }

    void droppedArtifact() {
        System.out.println("The villain dropped an artefact do you want to pick it up? [Y/N]");
    }

    void notRealArtifact() {
        System.out.println("That was not a real artefact.. It was dropped");
    }

    void yourLoss() {
        Functions.clearScreen();

        System.out.println("Your loss...");
    }

    void userLevelUp(String levelUp) {
        System.out.println("           " + ANSI_GREEN + "LEVEL UP!" + ANSI_RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("           " + ANSI_RED + "LEVEL UP!" + ANSI_RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("           " + ANSI_BLUE + "LEVEL UP!" + ANSI_RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("           " + ANSI_YELLOW + "LEVEL UP!" + ANSI_RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("           " + ANSI_PURPLE + "LEVEL UP!" + ANSI_RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("     You are " + ANSI_YELLOW + "level " + levelUp + ANSI_RESET + " now!");
        System.out.println("           " + ANSI_GREEN + "Well Done" + ANSI_RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void missionText(String heroLvl) {

        System.out.println(ANSI_BLUE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println("                 Map Level " + heroLvl);

        System.out.println(" Mission: Try and exit the map without dying");
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }

    void pickDirection() {
        System.out.print("What direction do you want to move to: ");
        System.out.println("North, East, South, West");
        System.out.print("Pick a direction: ");
    }
}