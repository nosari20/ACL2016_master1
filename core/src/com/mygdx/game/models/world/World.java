package com.mygdx.game.models.world;

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
    public World(){
        this.spaceship = new Spaceship(new Vector2(10,10));
        this.size = new Vector2(480,600);
    }

    public Vector2 getSize(){
        return new Vector2(this.size);
    }

    public Element getSpaceship(){
        return this.spaceship;
    }


}

