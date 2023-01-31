package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

abstract class Pokemon<T> extends MoveAI implements Cloneable{
    private String name;
    private String[] type;
    private int level;
    private Sprite sprite;

    private float[] defPos;
    private float speed;
    final private float base = 1f;

    public Pokemon(){}

    public void special(){}

    public float getBase() {
        return base;
    }

    public float[] getDefPos() {
        return defPos;
    }

    public void setDefPos(float[] defPos) {
        this.defPos = defPos;
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}

