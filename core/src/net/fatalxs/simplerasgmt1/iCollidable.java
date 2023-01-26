package net.fatalxs.simplerasgmt1;

interface iCollidable{
    boolean collidesWith(Pokemon other, float[] cM);
    float[] handleCollision();
    void reactToCollision(Pokemon cur, Pokemon oth);
}
