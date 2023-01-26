package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.Gdx;

class MoveAI{
    float[] nextMove = {0f,0f};
    public void update(Pokemon p, int[] controls){
        if (Gdx.input.isKeyPressed(controls[0])){ // move left
            nextMove[0] += -p.getBase() * p.getSpeed();
        }
        if (Gdx.input.isKeyPressed(controls[1])){ // move right
            nextMove[0] += p.getBase() * p.getSpeed();
        }
        if (Gdx.input.isKeyPressed(controls[2])){ // move up
            nextMove[1] += p.getBase() * p.getSpeed();
        }
        if (Gdx.input.isKeyPressed(controls[3])){ // move down
            nextMove[1] += -p.getBase() * p.getSpeed();
        }

        if (nextMove[0] != 0 || nextMove[1] != 0){
            nextMove = checkWindowBound(p, nextMove);
            p.getSprite().translate(nextMove[0],nextMove[1]);
            nextMove[0] = 0;
            nextMove[1] = 0;
        }

    }
    public float[] checkWindowBound(Pokemon p, float[] nextMove){
        float[] calcMove = nextMove.clone();
        if (p.getSprite().getX()+nextMove[0] < 0){
            calcMove[0] = 0;
            p.getSprite().setX(0);
        }
        if (p.getSprite().getX()+nextMove[0] > Gdx.graphics.getWidth()-p.getSprite().getWidth()){
            calcMove[0] = 0;
            p.getSprite().setX(Gdx.graphics.getWidth()-p.getSprite().getWidth());
        }
        if (p.getSprite().getY()+nextMove[1] < 0){
            calcMove[1] = 0;
            p.getSprite().setY(0);
        }
        if (p.getSprite().getY()+nextMove[1] > Gdx.graphics.getHeight()-p.getSprite().getHeight()){
            calcMove[1] = 0;
            p.getSprite().setY(Gdx.graphics.getHeight()-p.getSprite().getHeight());
        }
        return calcMove;
    }
    public void checkCollision(iCollidable cur, iCollidable oth){

    }
}
