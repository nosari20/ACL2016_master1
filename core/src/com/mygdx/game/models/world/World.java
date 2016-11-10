package com.mygdx.game.models.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.elements.Element;
import com.mygdx.game.models.elements.Spaceship;

/**
 * Created by Acherus on 09/11/2016.
 */
public class World {

    /**
     * The spaceship
     */
    private Spaceship spaceship;

    /**
     * The size of the world
     */
    private Vector2 size;

    /**
     * Default constructor
     */
    private Spaceship spaceShip;

    public World(){
        this.spaceShip = new Spaceship(new Vector2(7,15));
    }

    public Spaceship getSpaceShip(){
        return spaceShip;
    }
    public void update(){
        spaceShip.update(Gdx.graphics.getDeltaTime());
    }


}

