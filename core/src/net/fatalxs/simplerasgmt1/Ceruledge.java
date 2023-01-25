package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.graphics.Texture;

class Ceruledge extends Collidable{
    public Ceruledge(String n, int lvl, Texture spr, float[] xy){
        this.setName(n);
        this.setType(new String[]{"Fire","Ghost"});
        this.setLevel(lvl);
        this.setSprite(spr);
        this.setSpeed(8f);
        this.setXpos(xy[0]);
        this.setYpos(xy[1]);
        System.out.println(String.format("A %s has spawned!", this.getName()));
    }

    public void ember(){
        System.out.println(String.format("&s used Ember!", this.getName()));
    }
}
