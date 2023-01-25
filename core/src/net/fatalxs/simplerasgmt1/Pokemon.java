package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

abstract class Pokemon extends MoveAI{
    private String name;
    private String[] type;
    private int level;
    private Sprite sprite;

    private float xpos;
    private float ypos;
    private float speed;
    private float base = 5f;

    public Pokemon(){}

    public float getBase() {
        return base;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getXpos() {
        return xpos;
    }

    public void setXpos(float xpos) {
        this.xpos = xpos;
    }

    public float getYpos() {
        return ypos;
    }

    public void setYpos(float ypos) {
        this.ypos = ypos;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}

class Collidable extends Pokemon implements iCollidable{
    MoveAI brain = new MoveAI();

    @Override
    public boolean collidesWith(iCollidable other){
        getSprite().getBoundingRectangle();
        Pokemon pother = (Pokemon) other;
        return pother.getSprite().getBoundingRectangle().overlaps(getSprite().getBoundingRectangle());
    }

    @Override
    public void handleCollision() {

    }

    @Override
    public void reactToCollision(iCollidable cur, iCollidable oth){
        Pokemon curP = (Pokemon) cur;
        Pokemon othP = (Pokemon) oth;
        System.out.println("collision detected!");
        System.out.println(String.format("%s collided with %s!",curP.getName(),othP.getName()));
    }
}


class NonCollidable extends Pokemon{
    MoveAI brain = new MoveAI();

}

interface iCollidable{
    public boolean collidesWith(iCollidable other);
    public void handleCollision();
    public void reactToCollision(iCollidable cur, iCollidable oth);
}