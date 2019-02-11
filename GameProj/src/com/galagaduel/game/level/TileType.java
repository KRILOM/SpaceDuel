package com.galagaduel.game.level;

public enum TileType {

    EMPTY(0), EMPTY_SPACE_CUBE(1);

    private int n;

    TileType(int n){
    this.n = n;
    }

    public int numeric(){
        return n;
    }

    public static TileType fromNumeric(int n){
        switch (n){
            case 1:
                return EMPTY_SPACE_CUBE;
                default:
                    return EMPTY;
        }
    }
}
