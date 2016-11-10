package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controlers.CLISpaceshipControler;
import com.mygdx.game.factories.BasicTextureFactory;

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
     * @param time
     * @return
     */
    public Texture getTexture(float time){
        return BasicTextureFactory.getInstance().getSpaceship();
    }

}