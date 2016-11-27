package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.math.Vector2;
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
    protected World world;




    protected com.badlogic.gdx.math.Rectangle bbox;
    /**
     * Default constructor
     * @param position
     */
    public Element(World w,Vector2 position) {
        this.position = new Vector2(position);
        this.size = new Vector2(5,5);
        bbox = new com.badlogic.gdx.math.Rectangle(getPosition().x,getPosition().y,(float)1,(float)1);
        this.world = w;
    }


    /**
     * Constructor with size
     * @param position
     */
    public Element(World w,Vector2 position, Vector2 size) {
        this.position = new Vector2(position);
        this.size = size;
        bbox = new com.badlogic.gdx.math.Rectangle(getPosition().x,getPosition().y,(float)1,(float)1);
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
        this.bbox.setPosition(position);
    }

    public void setSize(Vector2 size){
        this.size = new Vector2(size);
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
    public abstract TextureRegion getTexture();

    public boolean hasCollision(Element e){
             if(e != null)
                 return bbox.overlaps(e.getBbox());
             return false;
     }

    public Rectangle getBbox() {
        return bbox;
    }
}
