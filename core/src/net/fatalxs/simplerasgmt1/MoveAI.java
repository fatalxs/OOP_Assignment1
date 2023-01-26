package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

class MoveAI{
    float[] nextMove = {0f,0f};
    public void update(ArrayList<Pokemon> pList, int sel, int[] controls){

        if (Gdx.input.isKeyPressed(controls[0])) { // move left
            nextMove[0] += -pList.get(sel).getBase() * pList.get(sel).getSpeed();
        }
        if (Gdx.input.isKeyPressed(controls[1])) { // move right
            nextMove[0] += pList.get(sel).getBase() * pList.get(sel).getSpeed();
        }
        if (Gdx.input.isKeyPressed(controls[2])) { // move up
            nextMove[1] += pList.get(sel).getBase() * pList.get(sel).getSpeed();
        }
        if (Gdx.input.isKeyPressed(controls[3])) { // move down
            nextMove[1] += -pList.get(sel).getBase() * pList.get(sel).getSpeed();
        }
        if (Gdx.input.isKeyJustPressed(controls[4])){
            pList.get(sel).special();
        }

        if (nextMove[0] != 0 || nextMove[1] != 0) {
            nextMove = checkWindowBound(pList.get(sel), nextMove);
            nextMove = checkCollision(pList, sel, nextMove);
            pList.get(sel).getSprite().translate(nextMove[0], nextMove[1]);
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
    public float[] checkCollision(ArrayList<Pokemon> pList, int sel, float[] cM){
        Pokemon cur = pList.get(sel);

        if (cur instanceof iCollidable){
            for (Pokemon oth : pList){
                if (oth != cur & oth instanceof iCollidable){
                    if (((iCollidable) cur).collidesWith(oth,cM)){
                        ((iCollidable) cur).reactToCollision(cur,oth);
                        return ((iCollidable) cur).handleCollision();
                    }

                }
            }
        }
        return cM;
    }
}
