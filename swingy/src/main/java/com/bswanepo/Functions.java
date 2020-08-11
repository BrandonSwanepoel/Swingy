package com.bswanepo;

import java.util.ArrayList;

public class Functions extends ConsoleQuestions {
    public void paintHeroList() {
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

        int heroesLength = 0;
        String line = " +-------";

        for (int i = 0; i < 7; i++) {
            if (i == 6) {
                line += "---+";
            } else {
                line += "---";
            }
        }
        for (String[] hero : heroes) {

            for (int i = 0; i < hero.length; ++i) {
                if (i == 0) {
                    System.out.println(ANSI_GREEN + line + ANSI_RESET);

                    System.out.println(ANSI_YELLOW + "            HERO NAME" + ANSI_RESET);

                    if (hero[i].length() > 15) {
                        System.out.println(ANSI_GREEN + "      " + hero[i] + ANSI_RESET);

                    } else {
                        System.out.println(ANSI_GREEN + "           " + hero[i] + ANSI_RESET);

                    }
                    System.out.println("");
                    i++;
                }
                if (hero[i].equals("")) {
                    i++;
                    System.out.println(ANSI_GREEN + line + ANSI_RESET);

                    System.out.println("");
                    System.out.println(ANSI_YELLOW + "            HERO NAME" + ANSI_RESET);
                    if (hero[i].length() > 20) {
                        System.out.println(ANSI_GREEN + "       " + hero[i] + ANSI_RESET);

                    } else {
                        System.out.println(ANSI_GREEN + "                 " + hero[i] + ANSI_RESET);

                    }
                    System.out.println("");
                    i++;
                }
                if (hero[i].length() > 15) {
                    System.out.println("       " + hero[i]);

                } else {
                    System.out.println("           " + hero[i]);

                }

            }
            heroesLength++;
            if (heroesLength == heroes.size()) {
                System.out.println(ANSI_GREEN + line + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

            }
        }

    }

    public static String getLevel(ArrayList<String> hero) {
        final String[] value = hero.get(2).split(" ");
        return value[1];

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Game Text Frontend
    public static void userLevelUp(String levelUp) {
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

    public static void missionText() {
        heroLvl = getLevel(hero);

        System.out.println(ANSI_BLUE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println("                 Map Level " + heroLvl);

        System.out.println(" Mission: Try and exit the map without dying");
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }

    public static void artifactPickUp(String[] result) {
        System.out.println(ANSI_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println("  You picked up a level " + artefact.get(1) + " " + artefact.get(0) + " artefact");
        System.out.println("    It increased your " + ANSI_GREEN + result[0] + ANSI_RESET + " with " + ANSI_YELLOW
                + result[1] + ANSI_RESET + "!");
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }

    public static void gameWinner(String xp) {
        System.out.println(ANSI_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

        System.out.println("      You are the " + ANSI_GREEN + "Winner" + ANSI_RESET + "!");
        System.out.println("       You earned " + xp + " " + ANSI_YELLOW + "XP" + ANSI_RESET);

        System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }

    public static void beatTheMap(String xp) {
        System.out.println(ANSI_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  You have Passed the Mission!");
        System.out.println("      Well done " + hero.get(0) + "!" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("~~~~~~~~~~" + ANSI_RESET);
        System.out.print(ANSI_YELLOW + "Next level" + ANSI_RESET);
        System.out.print(ANSI_GREEN + "~~~~~~~~~~\n");
        System.out.print("~~~~~~~" + ANSI_RESET);
        System.out.print(ANSI_YELLOW + " Gained " + xp + " XP " + ANSI_RESET);
        System.out.print(ANSI_GREEN + "~~~~~~~~\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }

    public static void lostGame() {
        clearScreen();

        System.out.println("You are the " + ANSI_RED + "LOSER " + ANSI_RESET + "...Sorry");
        System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("You have Failed the Mission!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }

    public static void goodOdds() {
        clearScreen();
        System.out.println(ANSI_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println("           The odds are in your favor");
        System.out.println("      You don't have to fight the villain...");
        System.out.println(ANSI_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }

    public static void badOdds() {
        clearScreen();
        System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println("         The odds are not in your favor");
        System.out.println("   You are going to have to fight the villain...");
        System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }

    public static void landedOnVillain() {
        System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
        System.out.println(" You have landed on a villain");
        System.out.println(ANSI_RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

        System.out.println("Do you want to FIGHT or RUN? [fight/run]");
    }
    public static void compass(){
        System.out.println("");
        System.out.println(ANSI_WHITE + "    N" + ANSI_RESET);
        System.out.println(ANSI_RED + "    |" + ANSI_RESET);
        System.out.print(ANSI_WHITE + "W" + ANSI_RESET);
        System.out.print(ANSI_BLUE + " ~~~~~ " + ANSI_RESET);
        System.out.print(ANSI_WHITE + "E\n" + ANSI_RESET);
        System.out.println(ANSI_RED + "    |" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "    S" + ANSI_RESET);
        System.out.println("");
    }
    public static void gameStart(){
        System.out.println(ANSI_BLUE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
      System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
      System.out.println("              The Game Started");
      System.out.println("                  GoodLuck ");
      System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
      System.out.println("                 Map Level " + heroLvl);
      System.out.println(" Mission: Try and exit the map without dying");
      System.out.println(ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
      System.out.println(ANSI_BLUE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
    }
}