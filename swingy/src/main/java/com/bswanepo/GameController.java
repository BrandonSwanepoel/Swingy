package com.bswanepo;

import java.util.ArrayList;
import com.bswanepo.Models.GameState;
import com.bswanepo.Models.Map;

public class GameController extends LobbyController {
    public Lobby lobby = new Lobby();

    private int playerRow;
    private int playerColumn;
    private Board board;
    private GameState currentState;
    private Map currentPlayer;
    String[] gameOutCome = new String[2];
    int beatVillainRow = -1;
    int beatVillainCol = -1;
    // int runFromVillainRow = -1;
    // int runFromVillainCol = -1;
    boolean won = false;
    String levelUp = null;

    public GameController() {
        TheView view = new TheView();
        board = new Board();
        GamePlay gamePlay = new GamePlay();
        boolean valid = false;
        String action;
        initGame();
        heroLvl = Functions.getLevel(hero);

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

                    view.userLevelUp(levelUp);
                }

                nextMission();

            } else if (currentState == GameState.FIGHT_WON) {
                view.braveOne();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                levelUp = gamePlay.levelUp(hero.get(0));

                Functions.gameWinner(gameOutCome[1]);
                if (!artefact.isEmpty()) {

                    do {
                        view.droppedArtifact();

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
                                view.missionText(heroLvl);

                            } else {
                                view.notRealArtifact();
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Functions.clearScreen();
                                view.missionText(heroLvl);
                            }
                            valid = true;

                        } else if (action.matches("N|n|No|no|NO")) {
                            view.yourLoss();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            view.missionText(heroLvl);
                            valid = true;

                        } else {
                            view.pickValidOption("Valid option");
                            valid = false;
                        }
                    } while (valid == false);
                }
                if (levelUp != null) {

                    view.userLevelUp(levelUp);
                    view.missionText(heroLvl);

                }
                board.paint();

                currentState = GameState.PLAYING;
            } else if (currentState == GameState.FIGHT_LOST) {
                Functions.clearScreen();

                view.braveOne();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Functions.lostGame();
                nextMission();

            } else if (currentState == GameState.RUN) {

                // System.out.println("Not everyone is brave...");
                view.missionText(heroLvl);
                currentState = GameState.PLAYING;
                board.paint();

            }
        } while (currentState == GameState.PLAYING);

    }

    public void initGame() {
        board.init();
        startGameBoard();
        board.paint();
        currentPlayer = Map.PLAYER;
        currentState = GameState.PLAYING;
    }

    public void playerMove(Map thePlayer) {
        TheView view = new TheView();
        GamePlay gamePlay = new GamePlay();
        Board.getBoardRowAndCell();
        String direction = null;
        int tempRow = playerRow;
        int tempColumn = playerColumn;
        heroLvl = Functions.getLevel(hero);
        boolean directionSet = false;

        do {

            view.pickDirection();
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
                view.pickValidOption("Valid option");

            }
        } while (directionSet == false);
        int row = playerRow;
        int col = playerColumn;
        int rowMax = Board.ROWS;
        int colMax = Board.COLS;

        Functions.clearScreen();

        if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS) {
            view.missionText(heroLvl);

            if (won == true) {
                board.cells[tempRow][tempColumn].content = Map.WON;
                won = false;
            } else {
                board.cells[tempRow][tempColumn].content = Map.EMPTY;
            }
        }
        if (board.cells[row][col].content == Map.WON) {
            won = true;
            board.cells[row][col].content = thePlayer;
        } else if (board.cells[row][col].content != Map.WON) {
            board.cells[row][col].content = thePlayer;

        }

        if (beatVillainRow != -1 && beatVillainCol != -1) {
            board.cells[beatVillainRow][beatVillainCol].content = Map.WON;
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
                                board.cells[row][col].content = Map.EMPTY;
                                board.cells[tempRow][tempColumn].content = thePlayer;
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
                                picked = true;
                            }
                        } else {
                            Functions.clearScreen();
                            view.pickValidOption("Valid option");
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
                board.cells[row][col].content = Map.PLAYER;
                board.currentRow = row;
                board.currentCol = col;
                validInput = true;
            }
        } while (!validInput);
    }
}