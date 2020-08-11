package com.bswanepo;

import java.util.ArrayList;
import com.bswanepo.models.GameState;
import com.bswanepo.models.Seed;

public class Game extends ConsoleQuestions {
    public Lobby lobby = new Lobby();

    private int playerRow;
    private int playerColumn;
    private Board board;
    private GameState currentState;
    private Seed currentPlayer;
    String[] gameOutCome = new String[2];
    int beatVillainRow = -1;
    int beatVillainCol = -1;
    // int runFromVillainRow = -1;
    // int runFromVillainCol = -1;
    boolean won = false;
    String levelUp = null;

    public Game() {
        board = new Board();
        GamePlay gamePlay = new GamePlay();
        boolean valid = false;
        String action;
        initGame();
        do {

            playerMove(currentPlayer);
            if (currentState == GameState.PLAYING) {
                board.paint();

            }

            if (currentState == GameState.YOU_WON) {
                Functions.clearScreen();

                String xp = gamePlay.nextLevel(hero);
                Functions.beatTheMap(xp);
                levelUp = gamePlay.levelUp(hero.get(0));
                
                if (levelUp != null) {

                    Functions.userLevelUp(levelUp);
                }
                
                nextMission();

            } else if (currentState == GameState.FIGHT_WON) {
                System.out.println("Lets see how you did brave one ...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                levelUp = gamePlay.levelUp(hero.get(0));

                Functions.gameWinner(gameOutCome[1]);
                if (!artefact.isEmpty()) {

                    do {
                        System.out.println("The villain dropped an artefact do you want to pick it up? [Y/N]");

                        action = c.readLine();
                        if (action.matches("Y|y|Yes|yes|YES")) {
                            Functions.clearScreen();

                            String[] result = gamePlay.pickedUpArtefact(artefact, hero.get(0));
                            if (!result[0].equals("ERROR")) {
                                Functions.artifactPickUp(result);
                                try {
                                    Thread.sleep(4000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Functions.clearScreen();
                                Functions.missionText();

                            } else {
                                System.out.println("That was not a real artefact.. It was dropped");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Functions.clearScreen();
                                Functions.missionText();
                            }
                            valid = true;

                        } else if (action.matches("N|n|No|no|NO")) {
                            Functions.clearScreen();

                            System.out.println("Your loss...");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Functions.clearScreen();

                            Functions.missionText();
                            valid = true;

                        } else {
                            System.out.println(ANSI_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + ANSI_RESET);
                            System.out.println("  Please pick a valid option");
                            System.out.println(ANSI_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + ANSI_RESET);
                            valid = false;
                        }
                    } while (valid == false);
                }
                if (levelUp != null) {

                    Functions.userLevelUp(levelUp);
                    Functions.missionText();

                }
                board.paint();

                currentState = GameState.PLAYING;
            } else if (currentState == GameState.FIGHT_LOST) {
                Functions.clearScreen();

                System.out.println("Lets see how you did brave one ...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Functions.lostGame();
                nextMission();

            } else if (currentState == GameState.RUN) {

                System.out.println("Not everyone is brave...");
                Functions.missionText();
                currentState = GameState.PLAYING;
                board.paint();

            }
        } while (currentState == GameState.PLAYING);

    }

    public void initGame() {
        board.init();
        startGameBoard();
        board.paint();
        currentPlayer = Seed.PLAYER;
        currentState = GameState.PLAYING;
    }

    public void playerMove(Seed theSeed) {
        GamePlay gamePlay = new GamePlay();
        Board.getBoardRowAndCell();
        String direction = null;
        int tempRow = playerRow;
        int tempColumn = playerColumn;

        boolean directionSet = false;

        do {

            System.out.print("What direction do you want to move to: ");
            System.out.println("North, East, South, West");
            System.out.print("Pick a direction: ");
            direction = c.readLine();
            if (direction.matches("North|north|N|n")) {
                tempRow = playerRow;
                playerRow -= 1;
                directionSet = true;

            } else if (direction.matches("East|east|E|e")) {
                tempColumn = playerColumn;
                playerColumn += 1;
                directionSet = true;

            } else if (direction.matches("South|south|s|S")) {
                tempRow = playerRow;
                playerRow += 1;
                directionSet = true;

            } else if (direction.matches("West|west|W|w")) {
                tempColumn = playerColumn;
                playerColumn -= 1;
                directionSet = true;

            } else {
                System.out.println(ANSI_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!" + ANSI_RESET);
                System.out.println("Please pick a valid Move!");
                System.out.println(ANSI_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!" + ANSI_RESET);

            }
        } while (directionSet == false);
        int row = playerRow;
        int col = playerColumn;
        int rowMax = Board.ROWS;
        int colMax = Board.COLS;

        Functions.clearScreen();

        if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS) {
            Functions.missionText();

            if (won == true) {
                board.cells[tempRow][tempColumn].content = Seed.WON;
                won = false;
            } else {
                board.cells[tempRow][tempColumn].content = Seed.EMPTY;
            }
        }
        if (board.cells[row][col].content == Seed.WON) {
            won = true;
            board.cells[row][col].content = theSeed;
        } else if (board.cells[row][col].content != Seed.WON) {
            board.cells[row][col].content = theSeed;

        }

        if (beatVillainRow != -1 && beatVillainCol != -1) {
            board.cells[beatVillainRow][beatVillainCol].content = Seed.WON;
            beatVillainRow = -1;
            beatVillainCol = -1;

            // } else if (runFromVillainRow != -1 && runFromVillainCol != -1) {
            // board.cells[runFromVillainRow][runFromVillainCol].content = Seed.RUN;
            // runFromVillainRow = -1;
            // runFromVillainCol = -1;
        }

        if (row == 0 || row == rowMax - 1 || col == 0 || col == colMax - 1) {
            currentState = GameState.YOU_WON;

        } else if (won == false) {

            for (int i = 0; i < villainRowValues.size(); i++) {

                int villainRowInteger = Integer.parseInt(villainRowValues.get(i));
                int villainColumnInteger = Integer.parseInt(villainColValues.get(i));
                if (villainRowInteger == row && villainColumnInteger == col) {
                    ArrayList<String> landedOnVillain = new ArrayList<>();
                    landedOnVillain = lobby.getVillain(villainRowInteger, villainColumnInteger);
                    String action;
                    boolean picked = false;
                    do {

                        Functions.landedOnVillain();

                        action = c.readLine();
                        if (action.matches("Fight|fight|f|F|FIGHT")) {
                            Functions.clearScreen();

                            gameOutCome = gamePlay.fight(landedOnVillain, hero);

                            if (gameOutCome[0] == "Winner") {

                                currentState = GameState.FIGHT_WON;
                                beatVillainRow = row;
                                beatVillainCol = col;

                            } else if (gameOutCome[0] == "Loser") {
                                currentState = GameState.FIGHT_LOST;
                            }

                            picked = true;
                            i = villainRowValues.size();
                        } else if (action.matches("Run|run|r|R|RUN")) {
                            boolean runResult = gamePlay.run();

                            if (runResult == true) {
                                board.cells[row][col].content = Seed.EMPTY;
                                board.cells[tempRow][tempColumn].content = theSeed;
                                playerRow = tempRow;
                                playerColumn = tempColumn;
                               Functions.goodOdds();
                                currentState = GameState.RUN;
                                break;
                            } else if (runResult == false) {
                               Functions.badOdds();
                                gameOutCome = gamePlay.fight(landedOnVillain, hero);

                                if (gameOutCome[0] == "Winner") {

                                    currentState = GameState.FIGHT_WON;
                                    beatVillainRow = row;
                                    beatVillainCol = col;

                                } else if (gameOutCome[0] == "Loser") {
                                    currentState = GameState.FIGHT_LOST;
                                }
                                // currentState = GameState.FIGHT_WON;

                                // runFromVillainRow = row;
                                // runFromVillainCol = col;
                                picked = true;
                            }
                        } else {
                            Functions.clearScreen();
                            System.out.println(ANSI_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + ANSI_RESET);
                            System.out.println("  Please pick a valid option");
                            System.out.println(ANSI_RED + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + ANSI_RESET);
                        }

                    } while (picked == false);
                }

            }
        }
        // if(playerRun == false){
        // board.currentRow = row;
        // board.currentCol = col;
        // }else{
        // board.currentRow = tempRow;
        // board.currentCol = tempColumn;
        // playerRun = false;
        // }
    }

    public void startGameBoard() {
        Board.getBoardRowAndCell();
        String[] level = hero.get(2).split(" ");
        int mapSize = (Integer.parseInt(level[1]) - 1) * 5 + 10 - (Integer.parseInt(level[1]) % 2);
        boolean validInput = false;
        do {
            playerRow = mapSize / 2;

            playerColumn = mapSize / 2;
            int row = mapSize / 2;
            int col = mapSize / 2;
            if (row > 0 && row < Board.ROWS && col > 0 && col < Board.COLS) {
                board.cells[row][col].content = Seed.PLAYER;
                board.currentRow = row;
                board.currentCol = col;
                validInput = true;
            } else {
                System.out.println("You have Passed the Mission!");
            }
        } while (!validInput);
    }
}