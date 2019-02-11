package com.galagaduel.utils;

import java.awt.image.BufferedImage;

public class Utils {

    public static BufferedImage resize(BufferedImage image, int width, int height){//функция изменяющая размеры изображений

        BufferedImage newImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        newImage.getGraphics().drawImage(image, 0 , 0 , width,height, null);//мы взяли какую-то картинку котору нам сюда передали и нарисовали на ней //

        return newImage;
    }


}
