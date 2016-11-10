package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by ACH02 on 08/11/2016.
 */
public abstract class Element{

    /**
     * Element's position in the world
     */
    private Vector2 position;

    /**
     * Element's size
     */
    private Vector2 size;


    /**
     * Default constructor
     * @param position
     */
    public Element(Vector2 position) {
        this.position = new Vector2(position);
    }


    /**
     * Constructor with size
     * @param position
     */
    public Element(Vector2 position, Vector2 size) {
        this.position = new Vector2(position);
        this.size = size;
    }



    /**
     * Return the position of the element (immutable)
     * @return position
     */
    public Vector2 getPosition() {
        return new Vector2(position);
    }

    /**
     * Return the size of the element (immutable)
     * @return position
     */
    public Vector2 getSize() {
        return new Vector2(size);
    }

    /**
     * Set the position of the element (immutable)
     * @param position
     */
    public void setPosition(Vector2 position) {
        this.position = new Vector2(position);
    }

    /**
     *
     * @return description of the element
     */
    public String toString(){
        return this.position.toString();
    }

    public abstract Texture getTexture(float time);


}
