package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.exceptions.GameException;
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
    public Element(World w,Vector2 position) throws GameException {

        this.position = new Vector2(position);
        this.size = new Vector2(5f,5f);
        bbox = new com.badlogic.gdx.math.Rectangle(getPosition().x,getPosition().y,5f,4f);
        this.world = w;

        if(w == null){
            throw new GameException("world is null");
        }
        if(position == null){
            throw new GameException("position is null");
        }else{
            if(position.x <0 || position.x+this.size.x>w.getWidth()){
                throw new GameException("position is out of the world");
            }
        }
    }


    /**
     * Constructor with size
     * @param position
     */
    public Element(World w,Vector2 position, Vector2 size)  throws GameException {

        this.position = new Vector2(position);
        this.size = size;
        bbox = new com.badlogic.gdx.math.Rectangle(getPosition().x,getPosition().y,size.x,size.y-0.5f);
        this.world = w;

        if(w == null){
            throw new GameException("world is null");
        }
        if(size == null){
            throw new GameException("size is null");
        }else{
            if(size.x<0 || size.y<0){
                throw new GameException("size has negative value");
            }
        }
        if(position == null){
            throw new GameException("position is null");
        }else{
            if(position.x <0 || position.x+size.x>w.getWidth()){
                throw new GameException("position is out of the world");
            }
        }
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
        this.bbox.setX(position.x);
        this.bbox.setY(position.y);
        this.position = new Vector2(position);
    }

    public void setSize(Vector2 size){
        this.size = new Vector2(size);
        this.bbox = this.bbox.setSize(size.x,size.y);
    }

    /**
     *
     * @return description of the element
     */
    public String toString(){
        return this.position.toString();
    }


    public boolean hasCollision(Element e){
             if(e != null)
                 return bbox.overlaps(e.getBbox());
             return false;
     }

    public Rectangle getBbox() {
        return bbox;
    }


}
