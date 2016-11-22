package com.mygdx.game.models.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.elements.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acherus on 09/11/2016.
 */
public class World {

    private final static int WORLD_WIDTH = 30;
    private final static int WORLD_HEIGHT = 35;
    private List<Element> elements;

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
        this.spaceship = new Spaceship(this,new Vector2((this.size.x/2),5));
        this.alien = new Alien(this,new Vector2((this.size.x/2),this.size.y));
        this.elements = new ArrayList<Element>();
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
        ArrayList copyElement = new ArrayList(elements);
        if(alien != null)
            alien.update(Gdx.graphics.getDeltaTime());
        for(Element e: elements){
            if(e instanceof MoveableElement)
                ((MoveableElement)e).update(Gdx.graphics.getDeltaTime());
            if(e instanceof Missile) {
                if (((Missile) e).getExplode() == 0 && alien != null) {
                    if (e.hasCollision(alien)) {
                        ((Missile) e).collision();
                        e.setPosition(alien.getPosition());
                        e.setSize(alien.getSize());
                        destroyAlien();
                    }
                }
                if ((((Missile) e).getExplode() > 1f))
                    copyElement.remove(e);
            }
        }
        elements = copyElement;
    }

    /**
     * Getter for size
     * @return size of the world
     */
    public Vector2 getSize(){
        return new Vector2(this.size);
    }


    public void addMissile() {
        this.elements.add(new Missile(this, new Vector2(spaceship.getPosition().x+4.2f,spaceship.getPosition().y+1.5f ), MoveableElement.Direction.NORTH ));
    }

    public void addMissileAlien(){
        this.elements.add(new MissileAlien(this, new Vector2(alien.getPosition().x+1,alien.getPosition().y ), MoveableElement.Direction.SOUTH ));
    }

    public List<Element> getElements() {
        return elements;
    }

    public void destroyAlien(){
        elements.remove(alien);
        alien = null;
    }



}

