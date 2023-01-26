package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

class Ceruledge extends Collidable{
    public Ceruledge(String n, int lvl, Sprite spr, float[] xy){
        this.setName(n);
        this.setType(new String[]{"Fire","Ghost"});
        this.setLevel(lvl);
        this.setSprite(spr);
        this.setSpeed(4f);
        this.setDefPos(xy);
        System.out.println(String.format("A %s has spawned!", this.getName()));
    }

    public void ember(){
        System.out.println(String.format("&s used Ember!", this.getName()));
    }
}
