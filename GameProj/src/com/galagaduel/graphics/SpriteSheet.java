package com.galagaduel.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {//держит в себе какой-то более маленький кусок из нашего большого выражения в котором может быть некоторое количество индивидуальных спрайтов

    private BufferedImage sheet; //наше изображение
    private int spriteCount; //колличество индивидуальных изображений которые будут находиться внутри одной картинки
    private int scale;//размер одного спрайта(наша ширина и высота которая захватывает одну картинку )(хранится в пикселях размер одного спрайта )
    private int spritesInWidth; //кол-во спрайтов которое у нас есть в ширину

    public SpriteSheet(BufferedImage sheet, int spriteCount, int scale) {
        this.sheet = sheet;
        this.spriteCount = spriteCount;
        this.scale = scale;

        this.spritesInWidth = sheet.getWidth()/scale;

    }

    public BufferedImage getSprite(int index){//расчет координат спрайта

        index = index % spriteCount;

        int x = index % spritesInWidth * scale;//координата x через расстояние скейл
        int y = index / spritesInWidth * scale;//координата y через расстояние скейл

        return sheet. getSubimage(x, y , scale, scale);//режем что получилось


    }

}
