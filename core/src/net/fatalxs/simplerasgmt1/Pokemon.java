package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

abstract class Pokemon extends MoveAI{
    private String name;
    private String[] type;
    private int level;
    private Sprite sprite;

    private float speed;
    final private float base = 1f;

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
    public boolean collidesWith(Pokemon other, float[] cM){
        Rectangle curHB = this.getSprite().getBoundingRectangle();
        Rectangle testHB = other.getSprite().getBoundingRectangle();
        if (testHB.overlaps(curHB.setPosition(curHB.getX()+cM[0],curHB.getY()+cM[1]))){
            return true;
        }
        else return false;
    }

    @Override
    public float[] handleCollision() {
        return new float[]{0,0};
    }

    @Override
    public void reactToCollision(Pokemon cur, Pokemon oth){
        System.out.println("Collision Detected!");
        System.out.println(String.format("%s collided with %s!\n\n", cur.getName(), oth.getName()));
    }
}


class NonCollidable extends Pokemon{
    MoveAI brain = new MoveAI();

}

interface iCollidable{
    boolean collidesWith(Pokemon other, float[] cM);
    float[] handleCollision();
    void reactToCollision(Pokemon cur, Pokemon oth);
}