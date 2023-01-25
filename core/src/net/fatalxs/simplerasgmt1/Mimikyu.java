package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.graphics.Texture;

class Mimikyu extends NonCollidable{
    public Mimikyu(String n, int lvl, Texture spr, float[] xy){
        this.setName(n);
        this.setType(new String[]{"Ghost","Fairy"});
        this.setLevel(lvl);
        this.setSprite(spr);
        this.setSpeed(10f);
        this.setXpos(xy[0]);
        this.setYpos(xy[1]);
        System.out.println(String.format("A %s has spawned!", this.getName()));
    }

    public void shadowsneak(){
        System.out.println(String.format("&s used Shadow Sneak!", this.getName()));
    }
}