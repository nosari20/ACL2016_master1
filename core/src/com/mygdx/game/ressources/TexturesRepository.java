package com.mygdx.game.ressources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by aschmat on 08/11/2016.
 */
public class TexturesRepository {
    private Animation explosionAnimation;

    private static TexturesRepository instance = new TexturesRepository();

    public static TexturesRepository getInstance() {
        return instance;
    }


    private TextureRegion spaceship = new TextureRegion(new Texture("images/SpaceShip.png"));
    private TextureRegion alien = new TextureRegion(new Texture("images/alien.png"));
    private TextureRegion missile = new TextureRegion(new Texture("images/missile.png"));
    private static final float EXPLOSION_FRAME_DURATION = 0.06f;
    public TextureRegion getMissile() {
        return missile;
    }

    public TextureRegion getSpaceship() {
        return spaceship;
    }

    public TextureRegion getAlien() {
        return alien;
    }

    public TexturesRepository() {
        TextureRegion[] explosion = new TextureRegion[32];
        for(int i=0;i<32;i++) {
            explosion[i] = new TextureRegion(new Texture("images/explosion_1-" + i + "-00.png"));
        }
        explosionAnimation = new Animation(EXPLOSION_FRAME_DURATION,explosion);
        explosionAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public Animation getExplodeAnimation(){
        return explosionAnimation;
    }
}