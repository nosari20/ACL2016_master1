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
import java.util.Random;

/**
 * Created by Acherus on 09/11/2016.
 */
public class World implements ScreenGameConfig{

    private final static int WORLD_WIDTH = 30;
    private final static int WORLD_HEIGHT = 35;
    private final static int NB_DEPLACEMENT_PATTERN = 5;
    private List<Element> elements;
    private Rectangle worldSurface;
    private int counter;
    /**
     * The spaceship
     */
    private Spaceship spaceship;

    /**
     * The size of the world
     */
    private Vector2 size;

    /**
     * Liste d'aliens
     */
    private ArrayList<Alien> listAlien;


    /**
     * Nombre d'alien morts
     */
    private int deadAliens;


    /**
     * Seuil d'aliens mort
     */
    private int step = 5;

    /**
     * niveau
     */
    private int level = 1;

    /**
     * Default constructor
     */
    public World(){
        this.size = new Vector2(WORLD_WIDTH, WORLD_HEIGHT);
        this.spaceship = new Spaceship(this,new Vector2((this.size.x/2),5));
        listAlien = new ArrayList<Alien>();

        for(int i = 0; i<this.level; i++) {
            createAlien();
        }
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
     * Update the world
     */
    public void update() throws GameException{
        spaceship.update(Gdx.graphics.getDeltaTime());
        for(Alien a : listAlien){
            if(a != null)
                a.update(Gdx.graphics.getDeltaTime());
        }
        ArrayList destroyElement = manage();
        if(counter % 60  == 0)
            createAlien();

             counter++;

        elements.removeAll(destroyElement);
        Gdx.app.log("Alien", elements.size()+"");

    }

    private ArrayList manage() throws GameException {
        ArrayList destroyElement = new ArrayList();
        for(Element e: elements){
            if(e instanceof MoveableElement)
                ((MoveableElement)e).update(Gdx.graphics.getDeltaTime());
            manageCollision(destroyElement, e);
            manageOuts(destroyElement, e);
        }

        if(this.spaceship.getPosition().x <= 0 || this.spaceship.getPosition().x + this.spaceship.getSize().x >= this.getSize().x){
            this.spaceship.stop();
        }

        return destroyElement;
    }

    private void manageOuts(ArrayList destroyElement, Element e) {
        Vector2 v = e.getSize();
        //Verifie si l'objet sort du monde

        if( e instanceof  Missile){
            if(e.getPosition().x <= 0 || e.getPosition().x + e.getSize().x >= this.getSize().x){

            }
            //
        }

        if(!worldSurface.overlaps(new Rectangle(e.getPosition().x,e.getPosition().y, v.x, v.y))){
            Gdx.app.log("Left the world", "for always");
            if(e.getPosition().x <= 0)
                ((Missile)e).inverseDirection(MoveableElement.Direction.WEST);
            else if(e.getPosition().x + e.getSize().x >= this.getSize().x)
                ((Missile)e).inverseDirection(MoveableElement.Direction.EST);
            else if(!destroyElement.contains(e))
                destroyElement.add(e);
        }
    }

    private void manageCollision(ArrayList destroyElement, Element e) throws SpaceshipDieException {
        if(e instanceof Missile) {
            for(Alien a : listAlien){
            if (((Missile) e).getExplode() == 0 && a != null) {

                    if (e.hasCollision(a)) {
                        ((Missile) e).collision();
                        e.setPosition(a.getPosition());
                        e.setSize(a.getSize());

                        //gestion collision Missile avec un Alien
                        a.touched(((Missile) e).getPuissance());
                        if(a.isDead() == true){
                            a.stop();
                            destroyElement.add(a);

                            spaceship.destroy(((Missile) e).getPoid());
                            //spaceship.destroyAlien(elements, alien,((Missile) e).getPoid());
                        }
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
        for(Alien a : listAlien)
            if(a.hasCollision(spaceship)){
                this.spaceship.spaceshipDie();
                a.stop();
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
        if(spaceship.isPowerUp()){
            this.elements.add(new Missile(this, new Vector2(spaceship.getPosition().x+((spaceship.getSize().x/2f)-1f),spaceship.getPosition().y+(spaceship.getSize().y/2f)), MoveableElement.Direction.NORTH ));
            this.elements.add(new Missile(this, new Vector2(spaceship.getPosition().x+((spaceship.getSize().x/2f)-1f),spaceship.getPosition().y+(spaceship.getSize().y/2f)), MoveableElement.Direction.NORTHEAST ));
            this.elements.add(new Missile(this, new Vector2(spaceship.getPosition().x+((spaceship.getSize().x/2f)-1f),spaceship.getPosition().y+(spaceship.getSize().y/2f)), MoveableElement.Direction.NORTHWEST));

        }else this.elements.add(new Missile(this, new Vector2(spaceship.getPosition().x+((spaceship.getSize().x/2f)-1f),spaceship.getPosition().y+(spaceship.getSize().y/2f)), MoveableElement.Direction.NORTH ));
    }

    public void addMissileAlien(){
        for(Alien a : listAlien){
            if(a.isMoving())
             this.elements.add(new MissileAlien(this, new Vector2(a.getPosition().x+1,a.getPosition().y ), MoveableElement.Direction.SOUTH ));

        }
    }

    public List<Element> getElements() {
        return elements;
    }


    public void createAlien() {
        Alien alien;
        Random rand = new Random();

        int min = 0;
        int max = WORLD_WIDTH - 5;
        int x = rand.nextInt(max - min + 1) + min;
        int pattern = rand.nextInt(NB_DEPLACEMENT_PATTERN) + 1;

        if(x <=4) {
            if(pattern == 2) {pattern = 1;}
            if(pattern == 3) {pattern = 4;}
        }
        if(x >= WORLD_WIDTH -7){
            if(pattern == 2) {pattern = 1;}
            if(pattern == 4) {pattern = 3;}
        }

        alien = new Alien(this,new Vector2((x),this.size.y), pattern);
        this.listAlien.add(alien);
    }

    public void destroyAlien(List<Element> elements, Alien alien,float poid){
        elements.remove(alien);
        alien = null;
        listAlien.remove(alien);
        getSpaceShip().addVie(poid * 0.1f);

    }

    /**
     * Appelle powerUp() dans spaceship et met a jour un boolean
     */
    private void addPowerUp(){
        this.spaceship.activatePowerUp();
    }


    public ArrayList<Alien> getListAlien(){
        return this.listAlien;
    }



    private void incrementsAliensdDead(){
        this.deadAliens ++;
        if(this.deadAliens >= this.step){
            this.changeLevel();
        }
    }

    private void changeLevel(){
        this.step = this.step * 2;
        this.level += 1;
    }

}

