package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.Gdx;

class MoveAI{
    public void update(Pokemon p, int[] controls){
        if (Gdx.input.isKeyPressed(controls[0])){ // move left
//            p.setXpos(p.getXpos() + -p.getBase() * p.getSpeed());
            p.getSprite().translateX(-p.getBase() * p.getSpeed());
        }
        if (Gdx.input.isKeyPressed(controls[1])){ // move right
//            p.setXpos(p.getXpos() + p.getBase() * p.getSpeed());
            p.getSprite().translateX(p.getBase() * p.getSpeed());
        }
        if (Gdx.input.isKeyPressed(controls[2])){ // move up
//            p.setYpos(p.getYpos() + p.getBase() * p.getSpeed());
            p.getSprite().translateY(p.getBase() * p.getSpeed());
        }
        if (Gdx.input.isKeyPressed(controls[3])){ // move down
//            p.setYpos(p.getYpos() + -p.getBase() * p.getSpeed());
            p.getSprite().translateY(-p.getBase() * p.getSpeed());
        }

        checkWindowBound(p);
    }
    public void checkWindowBound(Pokemon p){
        if (p.getSprite().getX() < 0){
            p.getSprite().setX(0);
        }
        if (p.getSprite().getX() > Gdx.graphics.getWidth()-p.getSprite().getWidth()){
            p.getSprite().setX(Gdx.graphics.getWidth()-p.getSprite().getWidth());
        }
        if (p.getSprite().getY() < 0){
            p.getSprite().setY(0);
        }
        if (p.getSprite().getY() > Gdx.graphics.getHeight()-p.getSprite().getHeight()){
            p.getSprite().setY(Gdx.graphics.getHeight()-p.getSprite().getHeight());
        }

    }
}
