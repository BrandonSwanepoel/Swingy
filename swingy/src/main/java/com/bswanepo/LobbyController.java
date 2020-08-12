package com.bswanepo;

import java.io.Console;
import java.util.ArrayList;

public class LobbyController {

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
        TheView view = new TheView();
        lobby = new Lobby();

        boolean validInput = false;
        String userInput = null;
        Functions.clearScreen();
        do {

            userInput = view.createHero();
            if (userInput.matches("Y|y|yes|Yes|YES")) {
                createdHero = createHero(createdHero);
                validInput = true;
            } else if (userInput.matches("N|n|No|no|NO")) {
                // SELECT A HERO
                userInput = view.selectHero();

                if (userInput.matches("Y|y|yes|Yes|YES")) {
                    Functions.clearScreen();
                    createdHero = selectHero(createdHero);
                    validInput = true;
                } else if (userInput.matches("N|n|No|no|NO")) {

                } else if (userInput.matches("exit|Exit|EXIT")) {

                    view.exitMessage();
                    System.exit(0);
                } else {

                }
            } else if (userInput.matches("exit|Exit|EXIT")) {
                view.exitMessage();
                System.exit(0);

            } else {
                view.pickValidOption("Y/N");
            }
        } while (validInput == false);

    }

    public boolean createHero(final boolean createdHero) {
        lobby = new Lobby();
        TheView view = new TheView();
        boolean validClass = false;
        if (c != null) {
            do {
                view.createHeroName();
                heroName = c.readLine();
                if (heroName.isBlank() || heroName.isEmpty()) {
                    view.pickValidOption("Empty");
                    continue;
                } else {

                    hero = lobby.selectHero(heroName);
                    if (!hero.isEmpty()) {
                        view.pickValidOption("Exists");
                        heroName = null;
                        continue;
                    }
                    do {

                        view.createHeroClasses();
                        view.pickHeroClass();
                        heroClass = c.readLine();
                        if (heroClass.isBlank() || heroClass.contains("^[[")) {
                            view.pickValidOption("Empty");
                            continue;
                        } else if (heroClass.contains("Wizard") || heroClass.contains("Tank")
                                || heroClass.contains("Lazy") || heroClass.contains("MonkeyKing")) {
                            lobby.createHero(heroName, heroClass, hero);
                            validClass = true;
                        }

                        else {
                            view.pickValidOption("Class does not exist");
                            continue;
                        }
                    } while (validClass == false);
                    view.heroCreated();
                    startOrEndGame();
                    heroCreated = true;
                }
            } while (heroCreated == false);
        }
        return createdHero;
    }

    public boolean selectHero(boolean createdHero) {
        TheView view = new TheView();
        final Functions functions = new Functions();

        final Lobby lobby = new Lobby();
        heroes = lobby.getAllHeroes();
        functions.paintHeroList();

        try {

            do {
                view.pickHeroByName();
                heroName = c.readLine();
                hero = lobby.selectHero(heroName);
                if (hero.isEmpty()) {
                    view.pickValidOption("Valid hero");
                    continue;
                } else {
                    view.pickedHero(hero);
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
        TheView view = new TheView();
        final Lobby lobby = new Lobby();
        String result = null;
        boolean validInput = false;
        boolean exit = false;
        do {

            view.readyToStartGame();
            result = c.readLine();
            if (result.matches("Yes|yes|y|Y")) {
                Functions.clearScreen();
                lobby.setVillainsPosition();
                validInput = true;
                new GameController();
            } else if (result.matches("No|no|n|N")) {
                Functions.clearScreen();
                do {
                    view.doYouWantToExit();
                    result = c.readLine();
                    if (result.matches("Yes|yes|y|Y")) {
                        view.exitMessage();
                        validInput = true;
                        exit = true;

                        System.exit(0);

                    } else if (result.matches("No|no|n|N")) {
                        Functions.clearScreen();
                        validInput = true;
                        exit = true;
                        startScreen();

                    } else {
                        view.pickValidOption("Valid option");

                    }
                } while (exit == false);
            } else {
                view.pickValidOption("Valid option");
            }

        } while (validInput == false);
    }

    public void nextMission() {
        TheView view = new TheView();
        final Lobby lobby = new Lobby();
        String result = null;
        boolean validInput = false;
        boolean exit = false;
        do {
            view.readyToStartNextMission();
            result = c.readLine();
            if (result.matches("Yes|yes|y|Y")) {
                Functions.clearScreen();
                lobby.setVillainsPosition();
                validInput = true;
                new GameController();
            } else if (result.matches("No|no|n|N")) {
                Functions.clearScreen();
                do {
                    view.doYouWantToExit();
                    result = c.readLine();
                    if (result.matches("Yes|yes|y|Y")) {
                        view.exitMessage();
                        validInput = true;
                        exit = true;

                        System.exit(0);

                    } else if (result.matches("No|no|n|N")) {
                        Functions.clearScreen();
                        validInput = true;
                        exit = true;
                        startScreen();

                    } else {
                        view.pickValidOption("Valid option");

                    }
                } while (exit == false);
            } else {
                view.pickValidOption("Valid option");
            }

        } while (validInput == false);
    }

}
