package com.mygdx.game.models.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;

/**
 * Created by ACH02 on 08/11/2016.
 */
public class Spaceship extends MortalElement {

    private final int SPACESHIP_SPEED = 12;
    // Attribut qui compte le nombre d'alien detruit peut etre reutilisé pour le score
    private int nbAlienDetruit;
    //Attribut qui permet d'activer ou non le mode powerUp
    private float powerUp;

    private static final float POWERUP_TIME = 3;
    /**
     * Default constructor
     *
     * @param position
     */
    public Spaceship(World w, Vector2 position) {
        super(w, position);
        this.setSpeed(SPACESHIP_SPEED);
        this.vie = 100;
        this.nbAlienDetruit = 0;
        this.powerUp = POWERUP_TIME;
    }

    /**
     *
     * @param missileWeight
     */
    @Override
    public void destroy(float missileWeight) {
        this.addVie(missileWeight * 0.1f);
        this.nbAlienDetruit++;
        Gdx.app.log("Le pv monte de "+(missileWeight * 0.1f), "vie actuel" +this.vie);
    }

    /**
     * surcharge de la fonction update qui permet de mettre fin au powerUp apres 2 seconde pour l'instant
     * soit 60 fps
     * soit 120 iteration de la fonction update pour atteindre 2 sec
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Gdx.app.log("+++++++++++++++++++++++++++++","");
        Gdx.app.log("DeltaTime", deltaTime+"");
        Gdx.app.log("PwerUp", this.powerUp+"");
        Gdx.app.log("+++++++++++++++++++++++++++++","");
        powerUp -= deltaTime;
    }
    /**
     *
     * @return description of the element
     */
    public String toString(){
        return "Spaceship : "+super.toString();
    }




    public void spaceshipDie(){
        this.stop();
        this.isDead = true;
    }

    public void addVie(float add){
        if(vie+add > 100){
            this.setVie(100);
        }else{
            this.setVie(vie+add);
        }
    }

    /**
     * active le mode powerUp
     */
    public void activatePowerUp() {
        if(powerUp <0)
            this.powerUp = 0;
        this.powerUp += POWERUP_TIME;
    }

    /**
     * Stoppe le mode powerUp
     */

    public boolean isPowerUp(){
        return this.powerUp >0f;
    }

    public int getNbKilledAliens() {
        return nbAlienDetruit;
    }
}
