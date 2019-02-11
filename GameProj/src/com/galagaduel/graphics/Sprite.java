package com.galagaduel.graphics;

import com.galagaduel.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {//хранит в себе ту информацию с помощью которой мы будем вытаскивать нужные нам изображения на данный момент


    private SpriteSheet sheet;
    private float scale;// насколько большим мы хотим рисовать этот спрайт на экране
    private BufferedImage image;


    public Sprite(SpriteSheet sheet, float scale) {
        this.sheet = sheet;
        this.scale = scale;
        image = sheet.getSprite(0);
        image = Utils.resize(image, (int)(image.getWidth() * scale), (int)(image.getHeight()* scale));
    }

    public void render(Graphics2D g, float x, float y){


        g.drawImage(image,(int)(x), (int)(y), null );

    }
}
