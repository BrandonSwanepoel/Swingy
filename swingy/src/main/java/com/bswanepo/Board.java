package com.bswanepo;

public class Board extends LobbyController {

   public static int mapSize;
   public static int ROWS;
   public static int COLS;
   public static final String ANSI_GREEN = "\u001B[32m";
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_WHITE = "\u001B[37m";
   public static final String ANSI_BLUE = "\u001B[34m";
   public static final String ANSI_RED = "\u001B[31m";

   Cell[][] cells;
   int currentRow, currentCol;

   public static void getBoardRowAndCell() {
      String[] level = hero.get(2).split(" ");

      mapSize = (Integer.parseInt(level[1]) - 1) * 5 + 10 - (Integer.parseInt(level[1]) % 2);
      ROWS = mapSize;
      COLS = mapSize;
   }

   public Board() {
      getBoardRowAndCell();
      cells = new Cell[ROWS][COLS];
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            cells[row][col] = new Cell(row, col);
         }
      }
   }

   public void init() {
      getBoardRowAndCell();
      heroLvl = Functions.getLevel(hero);
      Functions.gameStart();
      try {
         Thread.sleep(2000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            cells[row][col].clear();
         }
      }
   }

   public void paint() {
      getBoardRowAndCell();

      String line = "-";
      int i = mapSize;
      while (i-- != 0) {
         line += "---";
      }

      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            cells[row][col].paint();
            if (col < COLS - 1)
               if (col == 0 || col == COLS - 2) {
                  System.out.print(ANSI_GREEN + "|" + ANSI_RESET);
               }
         }
         System.out.println();
         if (row < ROWS - 1) {
            if (row == 0 || row == ROWS - 2) {

               System.out.println(ANSI_GREEN + line + ANSI_RESET);

            }
         }
      }
      Functions.compass();
   }

}