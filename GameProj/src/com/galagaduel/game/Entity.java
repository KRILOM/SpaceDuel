package com.galagaduel.game;

import com.galagaduel.IO.Input;

import java.awt.*;
// базовый класс энтити в котором у нас есть тип в котором мы будем создавать
public abstract class Entity {//напрямую из него не будем делать объекты
    //тип энтити
    public final EntityType type;//с помощью этого сможем знать какой конкреткно Энтити

    //мето нахождения того или иного энтити
    protected float x;//мсето нахождения вещей в игре
    protected float y;//

    protected Entity(EntityType type, float x, float y){
        this.type = type;
        this.x = x;
        this.y = y;
    }
//у каждого отдельного Энтити должны быть 2 метода Апдейт и Рендер
    public abstract void update(Input input);
    public abstract void render(Graphics2D g);
}
