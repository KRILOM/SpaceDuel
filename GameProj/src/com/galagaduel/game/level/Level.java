package com.galagaduel.game.level;

import com.galagaduel.game.Game;

import java.awt.*;

public class Level {

    public static final int TILE_SCALE = 8;//оригинальный тайл 8 размер
    public static final int TILE_IN_GAME_SCALE = 1;//велечина тайла
    public static final int TILES_IN_WIDTH = Game.WIDTH/(TILE_SCALE * TILE_IN_GAME_SCALE); //сколько тайлов в ширину(разммер в пикселях ширины нашего эерна / )
    public static final int TILES_IN_HEIGHT = Game.HEIGHT/(TILE_SCALE * TILE_IN_GAME_SCALE); //сколько тайлов в высоту(разммер в пикселях высоты нашего эерна / )

    private int[][] tileMap;//с поmщью этой массива мы будем определять кол-во тайлов в высотy и ширину нашего массива(каждое число будет привязано к определенному эрею)

    public Level(){
        tileMap = new int[TILES_IN_WIDTH][TILES_IN_HEIGHT];
    }

    public void update(){

    }
    public void render(Graphics2D g ){

    }


}
