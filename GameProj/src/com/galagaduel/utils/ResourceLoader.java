package com.galagaduel.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceLoader {
    public static final String PATH = "res/";//путь к папке

    public static BufferedImage loadImage(String fileName){//функция просто возвращает изображение
        BufferedImage image = null;//сохраняем сюда изображение которое считали

        try {
            image = ImageIO.read(new File(PATH + fileName) );// считываем файл
            //System.out.println(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


}
