package com.galagaduel.game;

import com.galagaduel.IO.Input;
import com.galagaduel.graphics.Sprite;
import com.galagaduel.graphics.SpriteSheet;
import com.galagaduel.graphics.TextureAtlas;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class EnemyPlayer extends Entity {

    public static final int SPRITE_SCALE = 23;
    public static final int SPRITES_PER_HEADING = 1;// кол-во спрайтов 1 - го напрваления для вырезания




    private enum Heading {//перечисление направлений самолета(то куда на плеер в данный момент смотрит )
        //привязываем кажждой нумерации координаты
        NORTH(0*SPRITE_SCALE,1*SPRITE_SCALE,SPRITE_SCALE * 1,SPRITE_SCALE * 1),//умножаем на 1, чтоб не делать пересчет //сохраняем координаты первого танка смотрящего вверх
        EAST(2*SPRITE_SCALE,1*SPRITE_SCALE,SPRITE_SCALE * 1,SPRITE_SCALE * 1),//
        SOUTH(0*SPRITE_SCALE,1*SPRITE_SCALE,SPRITE_SCALE * 1,SPRITE_SCALE * 1),
        WEST(11*SPRITE_SCALE,1*SPRITE_SCALE,SPRITE_SCALE * 1,SPRITE_SCALE * 1);
        private int x, y , h , w; //хранят в себе координаты где находится координата самого спрата которая нас интересует

        Heading(int x, int y, int h , int w){
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }
        //метод с помощью которого мы будем вырезать наши изображения из имеджа
        protected BufferedImage texture(TextureAtlas atlas){
            return atlas.cut(x, y , w, h);//возвращаем вырезанный кусок из нашего атласа
        }
    }

    private EnemyPlayer.Heading heading;//объект хранящий в себе направление самолета
    private Map<EnemyPlayer.Heading, Sprite> spriteMap;//ключ это нумерейш, а значение это спрайт => мапа нужна для того чтобы правильно вытасивать из нее спрайт уже с заданным направлением
    private float scale;// размер нашего спрайта(с каким размером мы хотим чтоб был создан наш плеер )
    private float speed;



    public EnemyPlayer(float x, float y, TextureAtlas atlas,float scale, float speed ) {
        super(EntityType.EnemyPlayer, x, y);
        heading = EnemyPlayer.Heading.NORTH;//изнаально любой плеер смотрит на север, то есть от игрока
        spriteMap = new HashMap<EnemyPlayer.Heading, Sprite>();//на каждый хединг мы хотим сохранять отдельный спрайт
        this.scale = scale;
        this.speed = speed;


        for(EnemyPlayer.Heading h: EnemyPlayer.Heading.values()){// возвращает массив который хранит в себе все значения которые есть в енумерации(в данном случае наш h будет равняться каждому из значений Heading)
            SpriteSheet sheet = new SpriteSheet(h.texture(atlas),SPRITES_PER_HEADING,SPRITE_SCALE );//создали спрайтщит
            Sprite sprite = new Sprite(sheet,scale); //создаем индивидуальный спрайт
            spriteMap.put(h, sprite);//хединг - ключ, спрайт - значение(=> мы свзали каждое направление с каким-то изз спрайтов)//связываем спрайт с хедингом
        }

    }

    //вызывается изнутри гейм класса 60 раз в секунду
    @Override
    public void update(Input input) {//управление поэтому передаем инпут

        //делаем это для того, чтобы координаты были там где они должны быть(проверка крч на необходимость добавления изменений по координатам(смотрим где оказывается игрок(самолет )) )
        float newX = x;// из класса энтити
        float newY = y;

        if(input.getKey(KeyEvent.VK_W)){// если кнопка навер зажата, то
            newY -= speed;
            heading = EnemyPlayer.Heading.NORTH;
        }else if(input.getKey(KeyEvent.VK_D)){
            newX += speed;
            heading = EnemyPlayer.Heading.EAST;
        }else if(input.getKey(KeyEvent.VK_S)){
            newY += speed;
            heading = EnemyPlayer.Heading.SOUTH;
        }else if(input.getKey(KeyEvent.VK_A)){
            newX -= speed;
            heading = EnemyPlayer.Heading.WEST;
        }
        else if(!input.getKey(KeyEvent.VK_A) || !input.getKey(KeyEvent.VK_D)){
            heading = EnemyPlayer.Heading.NORTH;
        }


//проверка на то, что корабль не за экраном
        if(newX < 0){// если х ноль или меньше, то это значит что наш корабль за экраном
            newX = 0;//и если это так то приравниваем переменную х нулю
        }else if(newX >= Game.WIDTH - SPRITE_SCALE * scale){// если же он больше ил равен ширине экрна - (велечине спрата * велечину спрату) не заходит правее чем можно
            newX = Game.WIDTH - SPRITE_SCALE * scale;//
        }
        if(newY < 0){// если х ноль или меньше, то это значит что наш корабль за экраном
            newY = 0;//и если это так то приравниваем переменную х нулю
        }else if(newY >= Game.HEIGHT - SPRITE_SCALE * scale){// если же он больше ил равен ширине экрна - (велечине спрата * велечину спрату) не заходит правее чем можно
            newY = Game.HEIGHT - SPRITE_SCALE * scale;//
        }
//если проверки выполнились, то
        x = newX;
        y = newY;

    }

    @Override
    public void render(Graphics2D g) {
        //проверм куда смотрит наш корабль и правильный ли спрайт мы вытащили и нарисовать его на наших координатах(х , у)
        spriteMap.get(heading).render( g, x, y );// получаем спрайт по ключу(который мы апдейтим в методе выше(постоянно меняем))

    }
}
