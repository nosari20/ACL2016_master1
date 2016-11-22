package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.ressources.TexturesRepository;

/**
 * Created by ACH02 on 10/11/2016.
 */
public class Alien  extends MoveableElement {

    private final static int ALIEN_SPEED = 12;

    private boolean changeDirection;

    /**
     * Default constructor
     *
     * @param position
     */
    public Alien(Vector2 position) {
        super(position, new Vector2(2,3), ALIEN_SPEED, Direction.NORTH, MoveableElement.DEFAULT_ALLOWED_DIRECTIONS);
        this.move();
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
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    changeDirection= true;
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
    }
}



