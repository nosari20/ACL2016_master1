package com.mygdx.game.models.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.world.World;
import com.mygdx.game.ressources.TexturesRepository;

import java.util.List;

/**
 * Created by aschmat on 18/11/2016.
 */
public class Missile extends MoveableElement {

    private static final int speed = 10;

    public Missile(World w, Vector2 position, Direction direction) {
        super(w,position, new Vector2(0.8f,0.8f), speed, direction);
        this.move();
    }

    @Override
    public Texture getTexture() {
        return TexturesRepository.getInstance().getMissile();
    }
}
