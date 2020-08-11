package com.bswanepo;

public class VillainPlacement {
    private String[] row;
    private String[] col;

    public void setVillainPlacement(String[] villainRow, String[] villainColumn) {
        this.row = villainRow;

        this.col = villainColumn;
        System.out.println(this.row);

    }

    public String[] getVillainRow() {
        System.out.println("hi");

        System.out.println(this.row);

        return this.row;
    }

    public String[] getVillainCol() {
        System.out.println("hi");

        System.out.println(this.col[1]);

        return this.col;
    }

}