package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.graphics.g2d.Sprite;

class Mimikyu extends NonCollidable{
    public Mimikyu(String n, int lvl, Sprite spr, float[] xy){
        this.setName(n);
        this.setType(new String[]{"Ghost","Fairy"});
        this.setLevel(lvl);
        this.setSprite(spr);
        this.setSpeed(5f);
        this.setDefPos(xy);
        System.out.println(String.format("A %s has spawned!", this.getName()));
    }

    public void special(){
        System.out.println(String.format("%s used Shadow Sneak!", this.getName()));
    }
}