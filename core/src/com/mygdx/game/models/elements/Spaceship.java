package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import com.mygdx.game.ressources.TexturesRepository;

/**
 * Created by ACH02 on 08/11/2016.
 */
public class Spaceship extends MoveableElement {

    private final int SPACESHIP_SPEED = 12;

    private boolean isDie;

    /**
     * Default constructor
     *
     * @param position
     */
    public Spaceship(World w, Vector2 position) {
        super(w, position);
        this.setSpeed(SPACESHIP_SPEED);
        this.setSize(new Vector2(5f,5f));
        this.isDie = false;
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
        return TexturesRepository.getInstance().getSpaceship();
    }

    public void spaceshipDie(){
        this.stop();
        this.isDie = true;
    }

    public boolean isDie(){
        return this.isDie;
    }

}
