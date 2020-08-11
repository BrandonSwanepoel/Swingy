package com.bswanepo;

import com.bswanepo.models.Map;

public class Cell {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    Map content;
    
    int row, col; 

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        clear();
    }

    public void clear() {
        content = Map.EMPTY;
    }

    public void paint() {
        switch (content) {
            case PLAYER:
                System.out.print(ANSI_WHITE+" X "+ANSI_RESET);
                break;
            case WON:
                System.out.print(ANSI_YELLOW +" W "+ANSI_RESET);
                break;
            // case RUN:
            //     System.out.print(ANSI_BLUE+" R "+ANSI_RESET);
            //     break;
            case EMPTY:
                System.out.print("   ");
                break;
			default:
				break;
        }
    }
}