package com.bswanepo;

import java.io.Console;
import java.util.ArrayList;

public class ConsoleQuestions {
    public static ArrayList<String> hero = new ArrayList<String>();

    Lobby lobby = new Lobby();
    boolean heroCreated = false;
    String heroName = null;
    String heroClass = null;
    boolean selectHero = false;
    boolean createdHero = false;
    Console c = System.console();
    
    public boolean CreateHero(boolean createdHero){
        
        if (c != null) {
        do {
            c.printf("\nHero name: ");
            heroName = c.readLine();
           
            if (heroName.isBlank()|| heroName.isEmpty()) {
                c.printf("\nError: Name field is Empty or has invalid format\n");
                
           
                continue;
            }else{
            lobby.SelectHero(heroName,hero);
            if (!hero.isEmpty()) {
                c.printf("\nError: Hero already exists!\n");
                heroName = null;
                continue;
            }
            c.printf("\n       Hero classes");
            c.printf("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            c.printf("Wizard\n");
            c.printf("Tank\n");
            c.printf("Lazy\n");
            c.printf("MonkeyKing\n");
            c.printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            c.printf("\nPick one of the above classes\n");
            c.printf("\nHero class: ");
            // if (c.readLine() != null) {
            heroClass = c.readLine();
            if (heroClass.isBlank() || heroClass.contains("^[[")) {
                c.printf("\nError: Class field is Empty or has invalid format\n");
                System.out.flush(); 

                continue;
            } else if (heroClass.contains("Wizard") || heroClass.contains("Tank")
                    || heroClass.contains("Lazy") || heroClass.contains("MonkeyKing")) {
                lobby.CreateHero(heroName, heroClass, hero);
                       
            }

            else {
                c.printf("\nError: Class Does not exist!\n");

                continue;
            }
            heroCreated = true;
            createdHero = true;
            c.printf("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            c.printf("\nYour hero is created!!\n");
            c.printf("\nLet the game begin\n");
            c.printf("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
        } while (heroCreated == false);
    }
    return createdHero;
    }
    public boolean SelectHero(boolean createdHero){
        try{
            lobby.AllHeroes(hero);

            for (String hero : hero) {
                c.printf("%s\n", hero);
            }
            c.printf("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            do {
                c.printf("\nPick one of the heroes above\n");
                c.printf("Hero name: ");
                heroName = c.readLine();
                lobby.SelectHero(heroName, hero);
                if (hero.isEmpty()) {
                    c.printf("\nPlease pick a valid Hero\n");
                    continue;
                } else {
                    c.printf("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

                    c.printf("\nYou have picked %s!!!\n\n", heroName.toUpperCase());
                    for (String hero : hero) {
                        c.printf("%s\n", hero);
                    }
                    c.printf("\nLet the game begin\n");
                    c.printf("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                    selectHero = true;
                    createdHero = true;

                }
            } while (selectHero == false);
        }catch(Exception e){
            System.out.println(e);
        }
        return createdHero;
        
    }
}