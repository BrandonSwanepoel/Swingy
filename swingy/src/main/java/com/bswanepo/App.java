package com.bswanepo;

import java.io.Console;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

/**
 * Hello world!
 *
 */
public class App {
    public static ArrayList<String> hero = new ArrayList<String>();
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 
    public static void main(String[] args) {
        Lobby lobby = new Lobby();
        boolean heroCreated = false;
        String heroName = null;
        String heroClass = null;
        boolean selectHero = false;
        boolean createdHero = false;
        String result = null;
        Console c = System.console();
        if (c != null) {

            do {
                c.printf("\nDo you want to create a Hero? Y/N.\n");
                result = c.readLine();
                if (result.equals("Y") || result.equals("y")) {
                    createdHero = true;
                    do {
                        if(heroName == null){
                        c.printf("\nHero name: ");
                        heroName = c.readLine();
                       
                        if (heroName.isBlank()|| heroName.isEmpty()) {
                            c.printf("\nError: Name field is Empty or has invalid format\n");
                            
                       
                            continue;
                        }
                        lobby.SelectHero(heroName,hero);
                        if (!hero.isEmpty()) {
                            c.printf("\nError: Hero already exists!\n");
                            heroName = null;
                            continue;
                        }
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
                        c.printf("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                        c.printf("\nYour hero is created!!\n");
                        c.printf("\nLet the game begin\n");
                        c.printf("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

                    } while (heroCreated == false);
                } else if (result.equals("N") || result.equals("n")) {
                    c.printf("\nDo you want to Select a Hero? Y/N.\n");
                    if (c.readLine().equals("Y")) {
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
                            System.out.flush(); 

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
                    }
                } else {
                    c.printf("\nPlease pick Y or N\n");

                }

            } while (createdHero == false);
        }

        // try {
        // if (scan.next().equals("Y")) {
        // String value = null;
        // scan.close();
        // System.out.println("Hero name:");
        // System.in.read();
        // scan = new Scanner(System.in);
        // // value = scan.;

        // System.out.println(scan);
        // // heroName = scan.next();

        // }
        // validData = true;// if gets data successfully, sets boolean to true
        // } catch (InputMismatchException e) {
        // // executes when this exception occurs
        // System.out.println("Input has to be a number. ");
        // }
        // } while (validData == false);
        // Lobby lobby = new Lobby();
        // if (args[0] == "") {
        // } else if (args[0] != null && args[1] != null)
        //
    }
}
// }
