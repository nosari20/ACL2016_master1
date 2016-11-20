package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.mygdx.game.models.world.World;


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
     * World's instance
     */
    private World world;




    /**
     * Default constructor
     * @param position
     */
    public Element(World w,Vector2 position) {
        this.position = new Vector2(position);
        this.size = new Vector2(5,5);
        this.world = w;
    }


    /**
     * Constructor with size
     * @param position
     */
    public Element(World w,Vector2 position, Vector2 size) {
        this.position = new Vector2(position);
        this.size = size;
        this.world = w;
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

    /**
     * Getter for texture
     * @return texture
     */
    public abstract Texture getTexture();

    /**
     * Detect if the element is out of the world
     * @return
     */
    public boolean isOutWorld(){
        return (this.getPosition().x + this.getSize().x < 0 || this.getPosition().x + this.getSize().x > this.world.getSize().x || this.getPosition().y < 0 || this.getPosition().y > this.world.getSize().y);
    }




}
