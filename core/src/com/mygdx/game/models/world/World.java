package com.mygdx.game.models.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.elements.Alien;
import com.mygdx.game.models.elements.Element;
import com.mygdx.game.models.elements.Spaceship;

/**
 * Created by Acherus on 09/11/2016.
 */
public class World {

    private final static int WORLD_WIDTH = 30;
    private final static int WORLD_HEIGHT = 35;


    /**
     * The spaceship
     */
    private Spaceship spaceship;

    /**
     * The alien
     */
    private Alien alien;

    /**
     * The size of the world
     */
    private Vector2 size;


    /**
     * Default constructor
     */
    public World(){
        this.size = new Vector2(WORLD_WIDTH, WORLD_HEIGHT);
        this.spaceship = new Spaceship(new Vector2((this.size.x/2),5));
        this.alien = new Alien(new Vector2((this.size.x/2),this.size.y));
    }

    /**
     * Getter the world's spaceship instance
     * @return
     */
    public Spaceship getSpaceShip(){
        return spaceship;
    }

    /**
     * Getter the world's alien instance
     * @return
     */
    public Alien getAlien(){
        return alien;
    }

    /**
     * Update the world
     */
    public void update(){
        spaceship.update(Gdx.graphics.getDeltaTime());
        alien.update(Gdx.graphics.getDeltaTime());
    }

    /**
     * Getter for size
     * @return size of the world
     */
    public Vector2 getSize(){
        return new Vector2(this.size);
    }


}

