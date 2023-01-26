package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.math.Rectangle;

class Collidable extends Pokemon implements iCollidable{
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
