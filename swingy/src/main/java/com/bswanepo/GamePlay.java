package com.bswanepo;

import java.io.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GamePlay extends Lobby implements Actions {

    @Override
    public String[] fight(final ArrayList<String> villain, final ArrayList<String> hero) {
        final Random random = new Random();
        int win = 0;
        int lose = 0;
        final String[] returnValue = new String[2];
        String villainXp = null;
        final ArrayList<Integer> villainStats = new ArrayList<>();
        final ArrayList<Integer> heroStats = new ArrayList<>();
        artefact.clear();

        // get villain Stats
        for (int i = 1; i < villain.size(); i++) {
            final String[] tmp = villain.get(i).split(" ");

            if (tmp[1].equals("Points")) {
                villainStats.add(Integer.parseInt(tmp[2]));
            } else if (tmp[0].equals("Defense")) {
                // Luck factor...
                int value = Integer.parseInt(tmp[1]);
                value += random.nextInt(10 - 1 + 1) + 1;
                villainStats.add(value);

            } else if (tmp[0].equals("Artefact")) {
                // artefact
                artefact.add(tmp[1]);
                artefact.add(tmp[2]);
            } else if (tmp[0].equals("XP")) {
                villainXp = tmp[1];
                villainStats.add(Integer.parseInt(tmp[1]));

            } else {
                villainStats.add(Integer.parseInt(tmp[1]));
            }

        }
        // get hero Stats

        for (int i = 3; i < hero.size(); i++) {
            final String[] tmp = hero.get(i).split(" ");
            if (tmp[1].equals("Points")) {
                heroStats.add(Integer.parseInt(tmp[2]));

            } else if (tmp[0].equals("Defense")) {
                // Luck factor...
                int value = Integer.parseInt(tmp[1]);
                value += random.nextInt(10 - 1 + 1) + 1;

                heroStats.add(value);
            } else {
                heroStats.add(Integer.parseInt(tmp[1]));

            }
        }
        // check who will win the fight
        for (int i = 0; i < 4; i++) {
            if (heroStats.get(i) > villainStats.get(i)) {
                win++;
            } else if (heroStats.get(i) < villainStats.get(i)) {
                lose++;
            }

        }
        if (win >= lose) {
            returnValue[1] = gainedXp(villainXp, hero.get(0));
            returnValue[0] = "Winner";
            return returnValue;
        } else {
            returnValue[0] = "Loser";
            returnValue[1] = "0";
            return returnValue;
        }

    }

    @Override
    public boolean run() {
        Random random = new Random();

        return random.nextBoolean();

    }

    @Override
    public String levelUp(final String heroName) {
        hero = selectHero(heroName);
        PrintWriter writer;
        String returnValue = null;
        String[] value = hero.get(2).split(" ");
        int levelInt = Integer.parseInt(value[1]);
        int pow = (int) Math.pow((levelInt - 1), 2);
        int lvlMeter = levelInt * 1000 + pow * 450;
        value = hero.get(3).split(" ");
        int xpValue = Integer.parseInt(value[1]);
        if (xpValue >= lvlMeter) {
            levelInt++;
            returnValue = String.valueOf(levelInt);

            try {
                final File file = new File("Heroes.txt");
                if (file != null) {
                    final Scanner fileReader = new Scanner(file);
                    try {
                        writer = new PrintWriter("HeroesTmp.txt", "UTF-8");
                        String data = fileReader.nextLine();

                        while (fileReader.hasNextLine()) {

                            if (data.contains(heroName)) {
                                while (!data.equals("")) {
                                    if (data.contains("Level")) {
                                        writer.println("Level " + levelInt);
                                        data = fileReader.nextLine();
                                    } else {
                                        writer.println(data);
                                        data = fileReader.nextLine();
                                    }
                                }
                            } else {
                                writer.println(data);
                                data = fileReader.nextLine();
                            }

                        }
                        writer.println(data);

                        writer.close();

                    } catch (final IOException e1) {
                        e1.printStackTrace();
                    }
                    fileReader.close();
                    final File tmpFile = new File("HeroesTmp.txt");
                    file.delete();
                    tmpFile.renameTo(file);

                }
            } catch (final FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                System.exit(0);
            }
        }
        return returnValue;

    }

    @Override
    public String[] pickedUpArtefact(final ArrayList<String> artefact, final String heroName) {
        PrintWriter writer;
        final String[] returnValue = new String[2];
        String stats = null;
        int artefactValue = 0;

        if (artefact.isEmpty()) {
            returnValue[0] = "ERROR";
            returnValue[1] = "ERROR";
            return returnValue;
        } else {
            artefactValue = Integer.parseInt(artefact.get(1));

        }

        try {
            final File file = new File("Heroes.txt");
            if (artefact.get(0).equals("Weapons")) {
                stats = "Attack";
            } else if (artefact.get(0).equals("Armor")) {
                stats = "Defense";
            } else if (artefact.get(0).equals("Helm")) {
                stats = "Hit Points";
            }
            if (file != null) {
                final Scanner fileReader = new Scanner(file);
                try {
                    writer = new PrintWriter("HeroesTmp.txt", "UTF-8");
                    String data = fileReader.nextLine();

                    while (fileReader.hasNextLine()) {

                        if (data.contains(heroName)) {
                            while (!data.equals("")) {
                                if (data.contains(stats)) {
                                    final String[] value = data.split(" ");
                                    int heroValue = Integer.parseInt(value[1]);
                                    heroValue += artefactValue * 5;
                                    writer.println(value[0] + " " + heroValue);
                                    data = fileReader.nextLine();
                                    returnValue[0] = value[0];
                                    returnValue[1] = String.valueOf(artefactValue * 5);

                                } else if (!data.contains(artefact.get(0))) {
                                    writer.println(data);
                                    data = fileReader.nextLine();

                                } else if (data.contains(artefact.get(0))) {

                                    writer.println(artefact.get(0) + " " + artefact.get(1));
                                    data = fileReader.nextLine();
                                    // writer.println("");

                                }
                            }
                        } else {
                            writer.println(data);
                            data = fileReader.nextLine();
                        }

                    }
                    writer.println(data);

                    writer.close();

                } catch (final IOException e1) {
                    e1.printStackTrace();
                }
                fileReader.close();
                final File tmpFile = new File("HeroesTmp.txt");
                file.delete();
                tmpFile.renameTo(file);

            }
        } catch (final FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        return returnValue;
    }

    @Override
    public String gainedXp(final String xp, final String heroName) {
        PrintWriter writer;
        String returnValue = null;

        final int xpValue = Integer.parseInt(xp);

        try {
            final File file = new File("Heroes.txt");
            if (file != null) {
                final Scanner fileReader = new Scanner(file);
                try {
                    writer = new PrintWriter("HeroesTmp.txt", "UTF-8");
                    String data = fileReader.nextLine();

                    while (fileReader.hasNextLine()) {

                        if (data.contains(heroName)) {
                            while (!data.equals("")) {
                                if (data.contains("XP")) {
                                    final String[] value = data.split(" ");
                                    int heroValue = Integer.parseInt(value[1]);
                                    heroValue += xpValue;
                                    writer.println(value[0] + " " + heroValue);
                                    data = fileReader.nextLine();
                                    returnValue = String.valueOf(xpValue);

                                } else if (!data.contains("XP")) {
                                    writer.println(data);
                                    data = fileReader.nextLine();
                                }
                            }
                        } else {
                            writer.println(data);
                            data = fileReader.nextLine();
                        }

                    }
                    writer.println(data);

                    writer.close();

                } catch (final IOException e1) {
                    e1.printStackTrace();
                }
                fileReader.close();
                final File tmpFile = new File("HeroesTmp.txt");
                file.delete();
                tmpFile.renameTo(file);

            }
        } catch (final FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        return returnValue;
    }

    @Override
    public String nextLevel(ArrayList<String> hero) {
        final String[] value = hero.get(2).split(" ");
        int heroLevel = Integer.parseInt(value[1]);
        int xp = heroLevel * 100;
        return gainedXp(String.valueOf(xp), hero.get(0));
    }

  


}