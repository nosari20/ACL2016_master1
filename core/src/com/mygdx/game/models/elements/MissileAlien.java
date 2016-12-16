package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.exceptions.GameException;
import com.mygdx.game.models.world.World;

public class MissileAlien extends MoveableElement {

    private static final int speed = 5;
    private MissileSource source = MissileSource.ALIEN;
    private int puissance = 5;

    public MissileAlien(World w, Vector2 position, Direction direction) throws GameException {
        super(w,position, new Vector2(0.8f,0.8f), speed, direction);
        this.move();
    }


    public int getPuissance() {
        return puissance;
    }
}