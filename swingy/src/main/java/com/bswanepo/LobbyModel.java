package com.bswanepo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LobbyModel extends LobbyController {

    // VillainPlacement villainPlacement = new VillainPlacement();

    public ArrayList<String> selectHero(final String heroName) {
        try {
            // if (hero != null) {
            //     hero.clear();
            // }
            final File file = new File("Heroes.txt");
            if (file != null) {
                final Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {

                    String data = fileReader.nextLine();
                    if (data.equals(heroName)) {
                        while (!data.equals("") && fileReader.hasNextLine()) {
                            hero.add(data);
                            data = fileReader.nextLine();
                        }
                        fileReader.close();
                        return hero;
                    }
                }
                fileReader.close();
            }
        } catch (final FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        return hero;
    }

    public ArrayList<String> createHero(final String heroName, final String heroClass, ArrayList<String> hero) {
        PrintWriter writer;
        try {

            // hero.clear();

            writer = new PrintWriter(new FileWriter("Heroes.txt", true));

            writer.println("");
            writer.println(heroName);
            writer.println("Class " + heroClass);
            writer.println("Level 1");
            writer.println("XP 0");
            writer.println("Attack 10");
            writer.println("Defense 10");
            writer.println("Hit Point 10");
            writer.println("Weapons 10");
            writer.println("Armor 10");
            writer.println("Helm 10");

            writer.close();

            hero = selectHero(heroName);
            return hero;
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(0);

        }
        return hero;
    }

    public ArrayList<String[]> getAllHeroes() {
        try {
            final ArrayList<String[]> heroes = new ArrayList<>();
            hero.clear();
            final File file = new File("Heroes.txt");
            int amountOfCharacters = amountOfCharacters(file);
            if (file != null) {
                final Scanner fileReader = new Scanner(file);
                for (int heroPosition = 0; heroPosition <= amountOfCharacters; heroPosition++) {
                    int i = 0;
                    String[] heroTmp = new String[10];
                    while (fileReader.hasNextLine()) {
                        final String data = fileReader.nextLine();

                        if (data.equals("")) {
                            break;
                        } else {
                            heroTmp[i] = data;
                            i++;
                        }
                    }
                    heroes.add(heroTmp);
                }
                fileReader.close();
                return heroes;
            }
        } catch (final FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public ArrayList<String> getVillain(final int row, final int col) {
        try {
            ArrayList<String> villain = new ArrayList<>();
            String[] tmpRow;
            String[] tmpCol;
            String lvl = null;
            final File file = new File("Villains.txt");
            if (file != null) {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {

                    String data = fileReader.nextLine();
                    if (data.contains("Name")) {
                        lvl = fileReader.nextLine();
                        if (lvl.contains(hero.get(2))) {
                            tmpRow = fileReader.nextLine().split(" ");
                            tmpCol = fileReader.nextLine().split(" ");
                            if (tmpRow[0].equals("Row") && tmpCol[0].equals("Col")) {

                                if (row == Integer.parseInt(tmpRow[1]) && col == Integer.parseInt(tmpCol[1])) {

                                    while (!data.equals("") && fileReader.hasNextLine()) {
                                        villain.add(data);
                                        // System.out.println(data);

                                        data = fileReader.nextLine();
                                    }
                                    fileReader.close();
                                    return villain;
                                }
                            }
                        }
                    }
                }
                fileReader.close();
            }
        } catch (final FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public void setVillainsPosition() {

        PrintWriter writer;
        int i = 0;

        ArrayList<String> villainRowPlace = new ArrayList<>();
        ArrayList<String> villainColumnPlace = new ArrayList<>();
        villainRowPlace = uniqueVillainRowPlacement();

        villainColumnPlace = uniqueVillainRowPlacement();
        villainRowValues.clear();
        villainColValues.clear();
        try {
            final File file = new File("Villains.txt");

            if (file != null) {
                final Scanner fileReader = new Scanner(file);
                try {
                    writer = new PrintWriter("VillainsTmp.txt", "UTF-8");
                    while (fileReader.hasNextLine()) {

                        final String data = fileReader.nextLine();
                        writer.println(data);
                        if (data.equals(hero.get(2))) {
                            writer.println("Row " + villainRowPlace.get(i));
                            writer.println("Col " + villainColumnPlace.get(i));
                            villainRowValues.add(villainRowPlace.get(i));
                            villainColValues.add(villainColumnPlace.get(i));

                            fileReader.nextLine();
                            fileReader.nextLine();
                            i++;
                        }
                    }
                    LobbyController.setVillainPlacement(villainRowValues, villainColValues);

                    writer.close();

                } catch (final IOException e1) {
                    e1.printStackTrace();
                }
                fileReader.close();
                final File tmpFile = new File("VillainsTmp.txt");
                file.delete();
                tmpFile.renameTo(file);

            }
        } catch (final FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public ArrayList<String> uniqueVillainRowPlacement() {
        final ArrayList<String> villainRowPlace = fillVillainPlaces();
        Collections.shuffle(villainRowPlace);
        return villainRowPlace;
    }

    public ArrayList<String> uniqueVillainColumnPlacement() {
        final ArrayList<String> villainColumnPlace = fillVillainPlaces();
        Collections.shuffle(villainColumnPlace);
        return villainColumnPlace;
    }

    public ArrayList<String> fillVillainPlaces() {
        final String[] level = LobbyController.hero.get(2).split(" ");
        final int mapSize = (Integer.parseInt(level[1]) - 1) * 5 + 10 - (Integer.parseInt(level[1]) % 2);
        final ArrayList<String> items = new ArrayList<>();

        for (int i = 0; i < mapSize; i++) {
            if (i != mapSize / 2 && i != 0 && i != mapSize) {
                items.add(String.valueOf(i));
            }
        }
        return items;
    }

    public int amountOfCharacters(final File file) {
        int amount = 0;
        Scanner fileReader;
        try {
            fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {

                final String data = fileReader.nextLine();
                if (data.equals("")) {
                    amount++;
                }
            }
            fileReader.close();

        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return amount;
    }

}
