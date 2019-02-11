package com.galagaduel.graphics;

import com.galagaduel.utils.ResourceLoader;

import java.awt.image.BufferedImage;

public class TextureAtlas {//класс который отвечает за само большое изображение из которого мы можем вырезать какие-то более маленькие куски

    BufferedImage image;
    public TextureAtlas(String imageName){
        image = ResourceLoader.loadImage(imageName);
    }

    public BufferedImage cut(int x, int y, int w, int h){
        return image.getSubimage(x, y, w, h);//вырезаем более маленький кусок из картинки

    }
}
