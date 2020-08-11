package com.bswanepo;

import java.io.Console;
import java.util.ArrayList;

public class ConsoleQuestions {

    public static ArrayList<String> hero = new ArrayList<>();
    public static ArrayList<String[]> heroes = new ArrayList<>();
    public static String heroLvl = null;
    public static ArrayList<String> artefact = new ArrayList<>();

    public static ArrayList<String> villains = new ArrayList<>();
    public static ArrayList<String> villainRowValues = new ArrayList<>();
    public static ArrayList<String> villainColValues = new ArrayList<>();
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public Functions functions;
    public Lobby lobby;
    boolean heroCreated = false;
    String heroName = null;
    String heroClass = null;
    boolean selectHero = false;
    boolean createdHero = false;
    Console c = System.console();

    public static void setVillainPlacement(final ArrayList<String> row, final ArrayList<String> column) {

        villainRowValues = row;
        villainColValues = column;
    }

    public void startScreen() {
        lobby = new Lobby();

        boolean validInput = false;
        String userInput = null;
        Functions.clearScreen();
        do {
            System.out.println("Do you want to create a Hero? [Y/N]" + ANSI_RED + " [" + ANSI_RESET
                    + "Type exit to leave the game" + ANSI_RED + "]" + ANSI_RESET);
            userInput = c.readLine();
            if (userInput.matches("Y|y|yes|Yes|YES")) {
                Functions.clearScreen();

                createdHero = createHero(createdHero);
                validInput = true;
            } else if (userInput.matches("N|n|No|no|NO")) {
                // SELECT A HERO
                Functions.clearScreen();

                System.out.println("Do you want to Select a Hero? [Y/N]" + ANSI_RED + " [" + ANSI_RESET
                        + "Type exit to leave the game" + ANSI_RED + "]" + ANSI_RESET);
                userInput = c.readLine();

                if (userInput.matches("Y|y|yes|Yes|YES")) {
                    Functions.clearScreen();

                    createdHero = selectHero(createdHero);
                    validInput = true;
                } else if (userInput.matches("N|n|No|no|NO")) {
                    Functions.clearScreen();

                } else if (userInput.matches("exit|Exit|EXIT")) {
                    Functions.clearScreen();
                    System.out.println(ANSI_GREEN + "Thank you for playing!" + ANSI_RESET);
                    System.exit(0);
                } else {
                    Functions.clearScreen();
                    System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~");
                    System.out.println("Please pick Y or N");
                    System.out.println("~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

                }
            } else if (userInput.matches("exit|Exit|EXIT")) {
                Functions.clearScreen();
                System.out.println(ANSI_GREEN + "Thank you for playing!" + ANSI_RESET);
                System.exit(0);

            } else {
                Functions.clearScreen();
                System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~");
                System.out.println("Please pick Y or N");
                System.out.println("~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
            }
        } while (validInput == false);
    }

    public boolean createHero(final boolean createdHero) {
        lobby = new Lobby();
        boolean validClass = false;
        if (c != null) {
            do {
                c.printf("\nHero name: ");
                heroName = c.readLine();

                if (heroName.isBlank() || heroName.isEmpty()) {
                    Functions.clearScreen();

                    c.printf("\nError: Name field is Empty or has invalid format\n");

                    continue;
                } else {

                    hero = lobby.selectHero(heroName);
                    if (!hero.isEmpty()) {
                        Functions.clearScreen();

                        c.printf("\nError: Hero already exists!\n");
                        heroName = null;
                        continue;
                    }
                    do {

                        c.printf("\n       Hero classes");
                        c.printf(ANSI_YELLOW + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + ANSI_RESET);
                        c.printf("Wizard\n");
                        c.printf("Tank\n");
                        c.printf("Lazy\n");
                        c.printf("MonkeyKing\n");
                        c.printf(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + ANSI_RESET);

                        c.printf("\nPick one of the above classes\n");
                        c.printf("\nHero class: ");
                        heroClass = c.readLine();
                        if (heroClass.isBlank() || heroClass.contains("^[[")) {
                            Functions.clearScreen();

                            c.printf("\nError: Class field is Empty or has invalid format\n");

                            continue;
                        } else if (heroClass.contains("Wizard") || heroClass.contains("Tank")
                                || heroClass.contains("Lazy") || heroClass.contains("MonkeyKing")) {
                            lobby.createHero(heroName, heroClass, hero);
                            validClass = true;
                        }

                        else {
                            Functions.clearScreen();

                            c.printf("\nError: Class Does not exist!\n");
                            continue;
                        }
                    } while (validClass == false);
                    Functions.clearScreen();

                    c.printf(ANSI_YELLOW + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + ANSI_RESET);
                    c.printf(ANSI_GREEN + "\nYour hero is created!!\n");
                    c.printf("\nLet the games begin\n" + ANSI_RESET);
                    c.printf(ANSI_YELLOW + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + ANSI_RESET);
                    startOrEndGame();
                    heroCreated = true;
                }
            } while (heroCreated == false);
        }
        return createdHero;
    }

    public boolean selectHero(boolean createdHero) {
        final Functions functions = new Functions();

        final Lobby lobby = new Lobby();
        heroes = lobby.getAllHeroes();
        functions.paintHeroList();

        try {

            do {
                System.out.println("\nPick one of the heroes above\n");
                System.out.print("Hero name: ");
                heroName = c.readLine();
                hero = lobby.selectHero(heroName);
                if (hero.isEmpty()) {

                    System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Please pick a valid Hero");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

                    continue;
                } else {
                    Functions.clearScreen();

                    System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

                    System.out.println(ANSI_GREEN + "           You have picked" + ANSI_RESET);

                    if (heroName.length() > 15) {
                        System.out.println(ANSI_GREEN + "           " + heroName + "!" + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_GREEN + "            " + heroName + "!" + ANSI_RESET);
                    }
                    System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

                    for (final String hero : hero) {
                        if (hero != heroName) {
                            if (hero.length() > 15) {
                                System.out.println("       " + hero);

                            } else {
                                System.out.println("           " + hero);

                            }
                        }
                    }
                    System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

                    startOrEndGame();

                    selectHero = true;
                    createdHero = true;

                }
            } while (selectHero == false);
        } catch (final Exception e) {
            System.out.println(e);
        }
        return createdHero;

    }

    public void startOrEndGame() {
        final Lobby lobby = new Lobby();

        String result = null;
        boolean validInput = false;
        boolean exit = false;
        do {

            System.out.println("Ready to start the Game? [Y/N]");
            result = c.readLine();
            if (result.matches("Yes|yes|y|Y")) {
                Functions.clearScreen();
                // villains = lobby.getAllVillains(hero.get(2));
                lobby.setVillainsPosition();
                validInput = true;
                new Game();
            } else if (result.matches("No|no|n|N")) {
                Functions.clearScreen();
                do {
                    System.out.println("Do you want to exit the Game? [Y/N]");
                    result = c.readLine();
                    if (result.matches("Yes|yes|y|Y")) {
                        Functions.clearScreen();

                        System.out.println(ANSI_GREEN + "Thank you for playing!" + ANSI_RESET);
                        validInput = true;
                        exit = true;

                        System.exit(0);

                    } else if (result.matches("No|no|n|N")) {
                        Functions.clearScreen();
                        validInput = true;
                        exit = true;
                        startScreen();

                    } else {
                        System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Please give a valid answer");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

                    }
                } while (exit == false);
            } else {
                System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Please give a valid answer");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

            }

        } while (validInput == false);
    }

    public void nextMission() {
        final Lobby lobby = new Lobby();
        hero = lobby.selectHero(hero.get(0));
        String result = null;
        boolean validInput = false;
        boolean exit = false;
        do {

            System.out.println("Ready to start the Next Mission? [Y/N]");
            result = c.readLine();
            if (result.matches("Yes|yes|y|Y")) {
                Functions.clearScreen();
                // villains = lobby.getAllVillains(hero.get(2));
                lobby.setVillainsPosition();
                validInput = true;
                new Game();
            } else if (result.matches("No|no|n|N")) {
                Functions.clearScreen();
                do {
                    System.out.println("Do you want to exit the Game? [Y/N]");
                    result = c.readLine();
                    if (result.matches("Yes|yes|y|Y")) {
                        Functions.clearScreen();

                        System.out.println(ANSI_GREEN + "Thank you for playing!" + ANSI_RESET);
                        validInput = true;
                        exit = true;

                        System.exit(0);

                    } else if (result.matches("No|no|n|N")) {
                        Functions.clearScreen();
                        validInput = true;
                        exit = true;
                        startScreen();

                    } else {
                        System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Please give a valid answer");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

                    }
                } while (exit == false);
            } else {
                System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Please give a valid answer");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

            }

        } while (validInput == false);
    }
}
