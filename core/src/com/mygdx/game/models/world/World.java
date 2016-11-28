package com.mygdx.game.models.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.exceptions.GameException;
import com.mygdx.game.exceptions.SpaceshipDieException;
import com.mygdx.game.models.elements.*;
import com.mygdx.game.screens.ScreenGameConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acherus on 09/11/2016.
 */
public class World implements ScreenGameConfig{

    private final static int WORLD_WIDTH = 30;
    private final static int WORLD_HEIGHT = 35;
    private List<Element> elements;
    private Rectangle worldSurface;
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
        worldSurface = new Rectangle(0,0,this.WORLD_WIDTH,this.WORLD_HEIGHT);
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
    public void update() throws GameException{
        spaceship.update(Gdx.graphics.getDeltaTime());
        if(alien != null)
            alien.update(Gdx.graphics.getDeltaTime()*2);
        ArrayList destroyElement = manage();

        elements.removeAll(destroyElement);
    }

    private ArrayList manage() throws GameException {
        ArrayList destroyElement = new ArrayList();
        for(Element e: elements){
            if(e instanceof MoveableElement)
                ((MoveableElement)e).update(Gdx.graphics.getDeltaTime());
            manageCollision(destroyElement, e);
            manageOuts(destroyElement, e);
        }

        return destroyElement;
    }

    private void manageOuts(ArrayList destroyElement, Element e) {
        Vector2 v = e.getSize();
        //Verifie si l'objet sort du monde
        if(!worldSurface.overlaps(new Rectangle(e.getPosition().x,e.getPosition().y, v.x, v.y))){
            Gdx.app.log("Left the world", "for always");
            if(!destroyElement.contains(e))
                destroyElement.add(e);
        }
    }

    private void manageCollision(ArrayList destroyElement, Element e) throws SpaceshipDieException {
        if(e instanceof Missile) {
            if (((Missile) e).getExplode() == 0 && alien != null) {
                if (e.hasCollision(alien)) {
                    ((Missile) e).collision();
                    e.setPosition(alien.getPosition());
                    e.setSize(alien.getSize());

                    //gestion collision Missile avec un Alien
                    alien.touched(((Missile) e).getPuissance());
                    if(alien.isDead() == true){
                        alien.stop();
                        destroyAlien(elements, alien);
                    }
                    
                }
            }
            if ((((Missile) e).getExplode() > 1f))
                destroyElement.add(e);
        }else if(e instanceof MissileAlien){
            if(spaceship.hasCollision(e)){

                //gestion collision MissileAlien aves le SpaceShip
                spaceship.touched(((MissileAlien) e).getPuissance());
                if(spaceship.isDead() == true) {
                    throw new SpaceshipDieException();
                }
            }
        }
        if(alien.hasCollision(spaceship)){
            this.spaceship.spaceshipDie();
            alien.stop();
            throw new SpaceshipDieException();
        }
    }

    /**
     * Getter for size
     * @return size of the world
     */
    public Vector2 getSize(){
        return new Vector2(this.size);
    }


    public void addMissile() {
        this.elements.add(new Missile(this, new Vector2(spaceship.getPosition().x+((spaceship.getSize().x/2f)-1f),spaceship.getPosition().y+(spaceship.getSize().y/2f)), MoveableElement.Direction.NORTH ));
    }

    public void addMissileAlien(){
        this.elements.add(new MissileAlien(this, new Vector2(alien.getPosition().x+1,alien.getPosition().y ), MoveableElement.Direction.SOUTH ));
    }

    public List<Element> getElements() {
        return elements;
    }

    public void destroyAlien(List<Element> elements, Alien alien){
        elements.remove(alien);
        alien = null;
        getSpaceShip().addVie(1);
    }




}

