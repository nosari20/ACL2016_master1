package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ACH02 on 08/11/2016.
 */
public abstract class MoveableElement extends Element{

    /**
     * Direction enum
     */
    public enum Direction {
        SOUTH,
        NORTH,
        EST,
        WEST,
    }

    /**
     * Default speed
     */
    protected final static int DEFAULT_SPEED = 10;

    /**
     * Default initial direction
     */
    protected final static Direction DEFAULT_INITIAL_DIRECTION = Direction.NORTH;

    /**
     * Default allowed directions
     */
    protected final static List<Direction> DEFAULT_ALLOWED_DIRECTIONS = Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EST, Direction.WEST);


    /**
     * Element's speed
     */
    private int speed;

    /**
     * Element's direction
     */
    private Direction direction;

    /**
     * Element's allowed directions
     */
    private List<Direction> allowedDirection;

    /**
     * Element's moving state
     */
    protected boolean isMoving;
    


    /**
     * Default constructor
     * @param position
     */
    public MoveableElement(World w, Vector2 position) {
        super(w,position);
        this.speed = MoveableElement.DEFAULT_SPEED;
        this.direction = MoveableElement.DEFAULT_INITIAL_DIRECTION;
        this.allowedDirection = MoveableElement.DEFAULT_ALLOWED_DIRECTIONS;
        this.isMoving = false;
    }

    /**
     * Constructor with speeed
     * @param position
     */
    public MoveableElement(World w, Vector2 position, int speed) {
        super(w,position);
        this.speed = speed;
        this.direction = MoveableElement.DEFAULT_INITIAL_DIRECTION;
        this.allowedDirection = MoveableElement.DEFAULT_ALLOWED_DIRECTIONS;
        this.isMoving = false;
    }

    /**
     * Constructor with all attribute
     * @param position
     */
    public MoveableElement(World w, Vector2 position, Vector2 size, int speed, Direction direction, List<Direction> allowedDirections) {
        super(w,position, size);
        this.speed = speed;
        this.direction = direction;
        this.allowedDirection = allowedDirections;
        this.isMoving = false;
    }
    /**
     * Constructor with all attribute
     * @param position
     */
    public MoveableElement(World w, Vector2 position, Vector2 size, int speed, Direction direction) {
        super(w,position, size);
        this.speed = speed;
        this.direction = direction;
        this.isMoving = false;
    }
    /**
     * Move the Element
     * @param direction
     */
    public void setDirection(MoveableElement.Direction direction){
        this.direction = direction;
    }

    /**
     * Update the elements properties with the controler depending on the delta time
     */
    public void update(float delta){
        if(isMoving) {
            double distance = this.speed * (delta);
            Vector2 oldPos = this.getPosition();
            Vector2 newPos = this.getPosition();
            switch (this.direction) {
                case SOUTH:
                    //this.setPosition(this.getPosition().set(this.getPosition().x, this.getPosition().y - (float) distance));
                    newPos.set(newPos.x,newPos.y - (float) distance);
                    break;
                case NORTH:
                    //this.setPosition(this.getPosition().set(this.getPosition().x, this.getPosition().y + (float) distance));
                    newPos.set(newPos.x,newPos.y + (float) distance);
                    break;
                case EST:
                    //this.setPosition(this.getPosition().set(this.getPosition().x + (float) distance, this.getPosition().y));
                    newPos.set(newPos.x  + (float) distance ,newPos.y);
                    break;
                case WEST:
                    //this.setPosition(this.getPosition().set(this.getPosition().x - (float) distance, this.getPosition().y));
                    newPos.set(newPos.x  - (float) distance ,newPos.y);
                    break;
            }
            this.setPosition(newPos);
            /*if(this.isOutWorld()){
               this.setPosition(oldPos);
            }*/
        }
    }

    /**
     * Getter for speed
     * @return speed
     */
    public int getSpeed(){
        return this.speed;
    }
    /**
     * Setter for speed
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    

    /**
     *
     * @return description of the element
     */
    public String toString(){
        return "s="+this.speed+" d="+this.direction+" "+super.toString();
    }

    /**
     * Setter for isMoving (true)
     * @return
     */
    public void move(){
        this.isMoving = true;
    }

    /**
     * Setter for isMoving (false)
     * @return
     */
    public void stop(){
        this.isMoving = false;
    }

    public boolean isMoving(){
        return this.isMoving;
    }

    /**
     * Getter for direction
     * @return direction
     */
    public Direction getDirection(){
        return this.direction;
    }



}
