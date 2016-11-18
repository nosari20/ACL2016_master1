package com.mygdx.game.ressources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by aschmat on 08/11/2016.
 */
public class TexturesRepository {

    private static TexturesRepository instance = new TexturesRepository();
    public static TexturesRepository getInstance(){
        return instance;
    }


    private Texture spaceship = new Texture("images/SpaceShip.png");
    private Texture alien = new Texture("images/alien.png");
    public Texture getSpaceship(){return spaceship;}
    public Texture getAlien(){return alien;}
}
