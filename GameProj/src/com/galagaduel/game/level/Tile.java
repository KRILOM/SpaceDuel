package com.galagaduel.game.level;

import com.galagaduel.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {//мнимая таблица из которой строится уровень

    private BufferedImage image;//изображение для нашего тайла, которое мы будем рисовать
    private TileType type;

    protected Tile(BufferedImage image,int scale, TileType type){
        this.type = type;
        this.image = Utils.resize(image, image.getWidth() * scale, image.getHeight() * scale);//сохраняем в имедеже сразу размер

    }

    protected void render(Graphics2D g , int x, int y){
        g.drawImage(image,x ,y , null);
    }

    protected TileType type(){
        return type;
    }

}
