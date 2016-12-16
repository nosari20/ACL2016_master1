package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;

import java.util.List;

/**
 * Created by aschmat on 27/11/2016.
 */
public abstract class MortalElement extends MoveableElement {
    protected float vie;
    protected boolean isDead;

    public MortalElement(World w, Vector2 position) throws GameException {
        super(w, position);
        isDead = false;
    }

    public MortalElement(World w, Vector2 position, int speed) {
        super(w, position, speed);
    }

    public MortalElement(World w, Vector2 position, Vector2 size, int speed, Direction direction, List<Direction> allowedDirections) {
        super(w, position, size, speed, direction, allowedDirections);
    }

    public MortalElement(World w, Vector2 position, Vector2 size, int speed, Direction direction) {
        super(w, position, size, speed, direction);
    }
    public float getVie(){
        return this.vie;
    }

    public void setVie(float newVie){
        this.vie = newVie;
    }

    public boolean isDead(){
        return this.isDead;
    }

    /**
     * Methode permettant de definir un comportement si l'objet est touché par un missile
     * @param missileWeight
     */
    public void touched(int missileWeight) {
        this.vie -= missileWeight;
        checkLifeState();
    }

    protected void checkLifeState(){
        if(vie <= 0)
            isDead = true;
    }

    /**
     * Methode permettant de definir un comportement si l'objet actuel detruit un autre
     * @param missileWeight
     */
    public abstract void destroy(float missileWeight);


}
