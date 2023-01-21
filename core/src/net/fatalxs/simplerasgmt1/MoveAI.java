package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.Gdx;

class MoveAI{
    public void update(Pokemon p, int[] controls){
        if (Gdx.input.isKeyPressed(controls[0])){ // move left
            p.setXpos(p.getXpos() + -p.getBase() * p.getSpeed());
        }
        if (Gdx.input.isKeyPressed(controls[1])){ // move right
            p.setXpos(p.getXpos() + p.getBase() * p.getSpeed());
        }
        if (Gdx.input.isKeyPressed(controls[2])){ // move up
            p.setYpos(p.getYpos() + p.getBase() * p.getSpeed());
        }
        if (Gdx.input.isKeyPressed(controls[3])){ // move down
            p.setYpos(p.getYpos() + -p.getBase() * p.getSpeed());
        }
    }
}
