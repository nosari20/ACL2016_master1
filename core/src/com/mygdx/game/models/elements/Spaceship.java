package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ressources.TexturesRepository;

/**
 * Created by ACH02 on 08/11/2016.
 */
public class Spaceship extends MoveableElement {

    private final int SPACESHIP_SPEED = 12;

    /**
     * Default constructor
     *
     * @param position
     */
    public Spaceship(Vector2 position) {
        super(position);
        this.setSpeed(SPACESHIP_SPEED);}

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
    public Texture getTexture(){
        return TexturesRepository.getInstance().getSpaceship();
    }

}
