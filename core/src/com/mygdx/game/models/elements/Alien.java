package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.models.world.World;
import com.mygdx.game.ressources.TexturesRepository;
import java.util.Random;

/**
 * Created by ACH02 on 10/11/2016.
 */
public class Alien  extends MoveableElement {

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
    public Alien(World w, Vector2 position) {
        super(w, position, new Vector2(2,3), ALIEN_SPEED, Direction.NORTH, MoveableElement.DEFAULT_ALLOWED_DIRECTIONS);
        this.move();

        Random rand = new Random();
        this.nb_pattern = rand.nextInt(NB_DEPLACEMENT_PATTERN) + 1;

        this.changeDirection = true;
    }


    /**
     *
     * @return description of the element
     */
    public String toString(){
        return "Spaceship : "+super.toString();
    }


    /**
     * Getter for texture
     * @return
     */
    public TextureRegion getTexture(){
        return TexturesRepository.getInstance().getAlien();
    }

    /**
     * Update the elements properties with the controler depending on the delta time
     */
    public void update(float delta){

        if(changeDirection){
            changeDirection = false;

            switch(this.nb_pattern) {
                /** Deplacement vers le SUD **/
                case 1:
                    this.setDirection(Direction.SOUTH);
                    break;

                /** Deplacement vers le SUD avec deplacement aleatoire sur les coté **/
                case 2:

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
                        case 4:
                            break;
                    }
                    break;

                /** Deplacement vers le SUD Gauche-Droite **/
                case 3:

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
                    break;

                /** Deplacement vers le SUD Gauche-Droite **/
                case 4:
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
                    break;

                /** Deplacement vers le vaisseau **/
                case 5:
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
                    break;
            }

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

}



