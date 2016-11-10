package com.mygdx.game.models.elements;

import com.badlogic.gdx.math.Vector2;

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
    private boolean isMoving;
    


    /**
     * Default constructor
     * @param position
     */
    public MoveableElement(Vector2 position) {
        super(position);
        this.speed = MoveableElement.DEFAULT_SPEED;
        this.direction = MoveableElement.DEFAULT_INITIAL_DIRECTION;
        this.allowedDirection = MoveableElement.DEFAULT_ALLOWED_DIRECTIONS;
        this.isMoving = false;
    }

    /**
     * Constructor with speeed
     * @param position
     */
    public MoveableElement(Vector2 position, int speed) {
        super(position);
        this.speed = speed;
        this.direction = MoveableElement.DEFAULT_INITIAL_DIRECTION;
        this.allowedDirection = MoveableElement.DEFAULT_ALLOWED_DIRECTIONS;
        this.isMoving = false;
    }

    /**
     * Constructor with all attribute
     * @param position
     */
    public MoveableElement(Vector2 position, Vector2 size, int speed, Direction direction, List<Direction> allowedDirections) {
        super(position, size);
        this.speed = speed;
        this.direction = direction;
        this.allowedDirection = allowedDirections;
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
            switch (this.direction) {
                case NORTH:
                    this.setPosition(this.getPosition().set(this.getPosition().x, this.getPosition().y - (float) distance));
                    break;
                case SOUTH:
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


    /**
     * Getter for direction
     * @return direction
     */
    public Direction getDirection(){
        return this.direction;
    }

}
