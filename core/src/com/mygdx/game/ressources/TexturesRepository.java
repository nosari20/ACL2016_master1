package com.mygdx.game.ressources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.elements.*;

/**
 * Created by aschmat on 08/11/2016.
 */
public class TexturesRepository {

    private Animation explosionAnimation;

    private static TexturesRepository instance = new TexturesRepository();

    public static TexturesRepository getInstance() {
        return instance;
    }

    private TextureElement spaceship = new TextureElement(new TextureRegion(new Texture("images/SpaceShip.png")),new Vector2(5f,5f));
    private TextureElement alien = new TextureElement(new TextureRegion(new Texture("images/alien.png")),new Vector2(2f,3f));
    private TextureElement missile = new TextureElement(new TextureRegion(new Texture("images/missile.png")),new Vector2(0.8f,0.8f));
    private TextureElement missileAlien = new TextureElement(new TextureRegion(new Texture("images/missile_alien.png")),new Vector2(0.8f,0.8f));


    private static final float EXPLOSION_FRAME_DURATION = 0.06f;

    public TexturesRepository() {
        TextureRegion[] explosion = new TextureRegion[32];
        for(int i=0;i<32;i++) {
            explosion[i] = new TextureRegion(new Texture("images/explosion_1-" + i + "-00.png"));
        }
        explosionAnimation = new Animation(EXPLOSION_FRAME_DURATION,explosion);
        explosionAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public TextureElement getMissileAlien(){return missileAlien;}

    public TextureElement getMissile() {
        return missile;
    }

    public TextureElement getSpaceship() {
        return spaceship;
    }

    public TextureElement getAlien() {
        return alien;
    }

    public Animation getExplodeAnimation(){
        return explosionAnimation;
    }

    public TextureElement getTexture(Element e){
        if(e instanceof Spaceship){
            return getSpaceship();
        }else if(e instanceof Alien) {
            return getAlien();
        }else if(e instanceof MissileAlien){
            return getMissileAlien();
        }else if(e instanceof Missile){
            return getMissile();
        }
        return null;
    }

}



