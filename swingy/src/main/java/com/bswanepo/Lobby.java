package com.bswanepo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Lobby implements Hero {

    public ArrayList<String> SelectHero(String heroName, ArrayList<String> hero) {
        try {
            hero.clear();
            File file = new File("Heroes.txt");
            if (file != null) {
                Scanner fileReader = new Scanner(file);
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
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public ArrayList<String> CreateHero(String heroName, String heroClass, ArrayList<String> hero) {
        PrintWriter writer;
        try {
           
            hero.clear();

                    writer = new PrintWriter(new FileWriter("Heroes.txt",true));
              
                    writer.println("");
                    writer.println(heroName);
                    writer.println("Class "+heroClass);
                    writer.println("Level 1");
                    writer.println("XP 0");
                    writer.println("Attack 10");
                    writer.println("Defense 10");
                    writer.println("Hit Point 10");
                    writer.println("Artefacs");
                    writer.println("Weapons 10");
                    writer.println("Armor 10");
                    writer.println("Helm 10");

                    writer.close();

                SelectHero(heroName, hero);
                return hero;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);

        }
        return null;
    }
    public ArrayList<String> AllHeroes(ArrayList<String> hero) {
        try {
            hero.clear();

            File file = new File("Heroes.txt");
            if (file != null) {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {

                    String data = fileReader.nextLine();
                            hero.add(data);
                        }
                        fileReader.close();
                        return hero;
                    }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}
