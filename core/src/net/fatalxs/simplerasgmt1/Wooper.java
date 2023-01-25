package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

class Wooper extends Collidable{
    public Wooper(String n, int lvl, Sprite spr, float[] xy){
        this.setName(n);
        this.setType(new String[]{"Water","Ground"});
        this.setLevel(lvl);
        this.setSprite(spr);
        this.setSpeed(5f);
        this.setXpos(xy[0]);
        this.setYpos(xy[1]);
        System.out.println(String.format("A %s has spawned!", this.getName()));
    }

    public void watergun(){
        System.out.println(String.format("&s used Water Gun!", this.getName()));
    }
}