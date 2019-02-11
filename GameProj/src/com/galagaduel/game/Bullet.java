package com.galagaduel.game;

import com.galagaduel.graphics.Sprite;
import com.galagaduel.graphics.SpriteSheet;
import com.galagaduel.graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Bullet{
    public enum BulletHeading{
    B_NORTH(14 * Player.SPRITE_SCALE, 6 * Player.SPRITE_SCALE + 4 , Player.SPRITE_SCALE / 2, 1 * Player.SPRITE_SCALE / 2),
        B_EAST(14 * Player.SPRITE_SCALE, 6 * Player.SPRITE_SCALE + 4 , Player.SPRITE_SCALE / 2, 1 * Player.SPRITE_SCALE / 2),
        B_WEST(14 * Player.SPRITE_SCALE, 6 * Player.SPRITE_SCALE + 4 , Player.SPRITE_SCALE / 2, 1 * Player.SPRITE_SCALE / 2),
        B_SOUTH(14 * Player.SPRITE_SCALE, 6 * Player.SPRITE_SCALE + 4 , Player.SPRITE_SCALE / 2, 1 * Player.SPRITE_SCALE / 2),;

    private int x, y , h, w;

        BulletHeading(int x, int y, int h, int w) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }
        protected BufferedImage texture(TextureAtlas atlas){
            return atlas.cut(x,y,w,h);
        }
    }

    private float speed;
    private Map<BulletHeading, Sprite> spriteMap;
    private BulletHeading bulletHeading;
    private float x;
    private float y;
    private float scale;
    private EntityType type;
    private boolean isActive;

    public Bullet(float speed, float x, float y, float scale, EntityType type,String direction,   TextureAtlas atlas) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.type = type;
        spriteMap = new HashMap<BulletHeading, Sprite>();

        for(BulletHeading bulletHeading1: BulletHeading.values()){
            SpriteSheet sheet = new SpriteSheet(bulletHeading1.texture(atlas), Player.SPRITES_PER_HEADING, Player.SPRITE_SCALE / 2);
            Sprite sprite = new Sprite(sheet,scale);
            spriteMap.put(bulletHeading1,sprite);
        }
        switch (direction){
            case "EAST":
                bulletHeading = BulletHeading.B_EAST;
                this.x = x + (Player.SPRITE_SCALE * scale) / 2;
                this.y = y + (Player.SPRITE_SCALE * scale) / 4;
                break;
            case "NORTH":
                bulletHeading = BulletHeading.B_NORTH;
                this.x = x + (Player.SPRITE_SCALE * scale) / 4;
                this.y = y;
                break;
            case "WEST":
                bulletHeading = BulletHeading.B_WEST;
                this.x = x;
                this.y = y + (Player.SPRITE_SCALE * scale) / 4;
                break;
            case "SOUTH":
                bulletHeading = BulletHeading.B_SOUTH;
                this.x = x + (Player.SPRITE_SCALE * scale) / 4;
                this.y = y + (Player.SPRITE_SCALE * scale) / 2;
                break;
        }
      //  Game.registerBullet(type, this);

    }

    public void update(){
        if(!isActive) {
            return;
        }

        switch (bulletHeading){
            case B_EAST:
                x += speed;
                if (!canFly(x + Player.SPRITE_SCALE *scale / 4 , y , x + Player.SPRITE_SCALE * scale / 4, y + Player.SPRITE_SCALE * scale / 4))
                    isActive = false;
                break;
            case B_NORTH:
                y += speed;
                if (!canFly(x, y , x + Player.SPRITE_SCALE * scale / 4 , y))
                    isActive = false;
                break;
            case B_SOUTH:
                y += speed;
                if (!canFly(x , y + Player.SPRITE_SCALE *scale / 4, x + Player.SPRITE_SCALE * scale / 4, y + Player.SPRITE_SCALE * scale / 4))
                    isActive = false;
                break;
            case B_WEST:
                x += speed;
                if (!canFly( x, y, x, y + Player.SPRITE_SCALE * scale / 4))
                    isActive = false;
                break;
        }
       if(x < 0 || x >= Game.WIDTH || y > Game.HEIGHT ){
           isActive = false;
       }
    }
    public void render(Graphics2D g){
        if(isActive){
            spriteMap.get(bulletHeading).render(g , x , y);
        }

    }

    private boolean canFly(float startX, float endX, float startY, float endY){
        int tileStartX = (int)(startX / Game.WIDTH);
        int tileStartY = (int)(startY/Game.HEIGHT);
        int tileEndX = (int)(endX/Game.WIDTH);
        int tileEndY = (int)(endY/Game.HEIGHT);

        return false;
    }

}
