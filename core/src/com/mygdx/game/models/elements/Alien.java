package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.models.world.World;

/**
 * Created by ACH02 on 10/11/2016.
 */
public class Alien  extends MortalElement {

    private final static int ALIEN_SPEED = 3;
    private final static int NB_DEPLACEMENT_PATTERN = 5;

    private boolean changeDirection;

    private int nb_pattern;
    private int nb_frame;
    private int nb_dep;


    /**
     * Default constructor
     *
     * @param position
     */
    public Alien(World w, Vector2 position, int pattern)  throws GameException{
        super(w, position, new Vector2(2,3), ALIEN_SPEED+w.getLevel(), Direction.NORTH, MoveableElement.DEFAULT_ALLOWED_DIRECTIONS);
        if(pattern <1 || pattern>5){
            throw new GameException('pattern <1 or >5');
        }
        this.move();
        this.nb_pattern = pattern;
        this.changeDirection = true;
        this.vie = 5;
    }

    /**
     * Ne fait pour l'instant
     * @param missileWeight
     */
    @Override
    public void destroy(float missileWeight) {

    }




    /**
     *
     * @return description of the element
     */
    public String toString(){
        return "Spaceship : "+super.toString();
    }




    /**
     * Update the elements properties with the controler depending on the delta time
     */
    public void update(float delta){

        if(changeDirection){
            changeDirection = false;
            updateMovement();

        }

        if(isMoving) {
            double distance = this.getSpeed() * (delta);
            switch (this.getDirection()) {
                case SOUTH:
                    this.setPosition(this.getPosition().set(this.getPosition().x, this.getPosition().y - (float) distance));
                    break;
                case NORTH:
                    this.setPosition(this.getPosition().set(this.getPosition().x, this.getPosition().y + (float) distance));
                    break;
                case EST:
                    this.setPosition(this.getPosition().set(this.getPosition().x + (float) distance, this.getPosition().y));
                    break;
                case WEST:
                    this.setPosition(this.getPosition().set(this.getPosition().x - (float) distance, this.getPosition().y));
                    break;
            }
        }

        nb_frame++;
        if((nb_frame%120 ==0 && isMoving)){
            this.world.addMissileAlien();
        }
    }

    public boolean isChangeDirection() {
        return changeDirection;
    }

    public int getNb_pattern() {
        return nb_pattern;
    }

    private void updateMovement() {
        switch (nb_pattern) {

            /** Deplacement vers le SUD **/
            case 1:
                movementSouth();
                break;

            /** Deplacement vers le SUD avec deplacement aleatoire sur les coté **/
            case 2:
                movementRandomEastWest();
                break;

            /** Deplacement vers le SUD Gauche-Droite **/
            case 3:
                movementSouthLeftRight();
                break;

            /** Deplacement vers le SUD Droite-Gauche **/
            case 4:
                movementSouthRightLeft();
                break;
            /** Deplacement vers le vaisseau **/
            case 5:
                followShip();
                break;
        }
    }

    private void movementSouth() {
        this.setDirection(Direction.SOUTH);
    }

    private void movementRandomEastWest() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {changeDirection= true;
            }
        }, (float)(0.2*Math.random()));

        switch (1+(int)(Math.random()*3)) {
            case 1:
                this.setDirection(Direction.EST);
                break;
            case 2:
                this.setDirection(Direction.WEST);
                break;
            case 3:
                this.setDirection(Direction.SOUTH);
                break;
            default:
                break;
        }
    }

    private void movementSouthLeftRight() {
        if(this.nb_frame%200 == 0) {
            nb_dep = 100;
        }

        if(this.nb_dep != 0) {
            if (this.getDirection() == Direction.SOUTH) {
                this.setDirection(Direction.WEST);
            } else {
                this.setDirection(Direction.SOUTH);
            }
            nb_dep--;

        } else {
            if (this.getDirection() == Direction.SOUTH) {
                this.setDirection(Direction.EST);
            } else {
                this.setDirection(Direction.SOUTH);
            }
        }
        this.changeDirection = true;
    }

    private void movementSouthRightLeft() {
        if(this.nb_frame%200 == 0) {
            nb_dep = 100;
        }

        if(this.nb_dep != 0) {
            if (this.getDirection() == Direction.SOUTH) {
                this.setDirection(Direction.EST);
            } else {
                this.setDirection(Direction.SOUTH);
            }
            nb_dep--;

        } else {
            if (this.getDirection() == Direction.SOUTH) {
                this.setDirection(Direction.WEST);
            } else {
                this.setDirection(Direction.SOUTH);
            }
        }
        this.changeDirection = true;
    }

    private void followShip() {
        int spacex = (int)this.world.getSpaceShip().getPosition().x + 4;
        int alienx = (int)this.getPosition().x + 1;
        if(alienx != spacex){
            if(alienx > spacex){
                this.setDirection(Direction.WEST);
            }
            if(alienx < spacex){
                this.setDirection(Direction.EST);
            }
        } else {
            this.setDirection(Direction.SOUTH);
        }
        this.changeDirection = true;
    }

    public int getNb_frame() {
        return nb_frame;
    }
}



