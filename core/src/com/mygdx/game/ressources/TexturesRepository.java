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


    private Texture spaceShip = new Texture("images/SpaceShip.png");
    public Texture getSpaceShip(){return spaceShip;}
}
