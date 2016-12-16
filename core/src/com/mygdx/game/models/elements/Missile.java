package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import com.mygdx.game.ressources.TexturesRepository;

/**
 * Created by aschmat on 18/11/2016.
 */
public class Missile extends MoveableElement {

    private float explode = 0;
    private float stateTime = 0;
    private static final int speed = 10;
    private MissileSource source = MissileSource.SPACESHIP;
    private int puissance = 5;
    private float poid = 1f;

    public Missile(World w, Vector2 position, Direction direction)  throws GameException{
        super(w,position, new Vector2(0.8f,0.8f), speed, direction);
        this.move();
    }

    public TextureRegion getTexture() {
        if (explode != 0)
            return TexturesRepository.getInstance().getExplodeAnimation().getKeyFrame(explode,true);
       // return TexturesRepository.getInstance().getMissile();
        return null;
    }

    public void collision(){
            explode = 0.01f;
            this.isMoving = false;
    }

    public void update(float delta){
        if(isMoving) {
            double distance = this.speed * (delta);
            Vector2 newPos = this.getPosition();
            switch (this.getDirection()) {
                case NORTH:
                    //this.setPosition(this.getPosition().set(this.getPosition().x, this.getPosition().y + (float) distance));
                    newPos.set(newPos.x, newPos.y + (float) distance);
                    break;
                case NORTHEAST:
                    newPos.set(newPos.x + (float) (Math.cos(Math.PI / 4) * distance), newPos.y + (float) (Math.sin(Math.PI / 4) * distance));
                    break;
                case NORTHWEST:
                    newPos.set(newPos.x + (float) (Math.cos(Math.PI * 3 / 4) * distance), newPos.y + (float) (Math.sin(Math.PI * 3 / 4) * distance));
                    break;
            }
            this.setPosition(newPos);
        }
        if(explode > 0)
            explode += delta;
    }

    public float getStateTime(){
        return stateTime;
    }

    public float getExplode(){
        return explode;
    }

    public int getPuissance() {
        return puissance;
    }

    public float getPoid() {
        return poid;
    }

    public void setPoid(float poid) {
        this.poid = poid;
    }
    public void inverseDirection(MoveableElement.Direction direction) {
        switch (direction){
            case WEST:
                this.setDirection(Direction.NORTHEAST);
                break;
            case EST:
                this.setDirection(Direction.NORTHWEST);
                break;
        }
    }
}
