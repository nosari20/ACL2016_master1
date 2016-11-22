package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import com.mygdx.game.ressources.TexturesRepository;

import java.util.List;

/**
 * Created by aschmat on 18/11/2016.
 */
public class Missile extends MoveableElement {

    private float explode = 0;
    private float stateTime = 0;
    private static final int speed = 10;

    public Missile(World w, Vector2 position, Direction direction) {
        super(w,position, new Vector2(0.8f,0.8f), speed, direction);
        this.move();
    }

    public TextureRegion getTexture() {
        if (explode != 0)
            return TexturesRepository.getInstance().getExplodeAnimation().getKeyFrame(explode,true);
        return TexturesRepository.getInstance().getMissile();
    }

    public void collision(){
            explode = 0.01f;
            this.isMoving = false;
    }

    public void update(float delta){
        if(isMoving) {
            double distance = this.speed * (delta);
            this.setPosition(this.getPosition().set(this.getPosition().x, this.getPosition().y + (float) distance));
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
}
