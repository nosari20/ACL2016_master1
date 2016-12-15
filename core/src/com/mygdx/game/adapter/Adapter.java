package com.mygdx.game.adapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.models.elements.Element;
import com.mygdx.game.models.world.WorldInterface;
import com.mygdx.game.ressources.TexturesRepository;
import com.mygdx.game.screens.ScreenGameConfig;

import java.util.List;

/**
 * Created by aschmat on 10/12/2016.
 */
public abstract class Adapter implements ScreenGameConfig {
    protected List<Element> listOfElements;
    protected WorldInterface world;
    public Adapter(WorldInterface world){
        this.world = world;
        this.listOfElements = world.getElements();
    }


    public abstract void update();
    public abstract void restart();
    public abstract void show();
    public abstract void dispose();
    public abstract void pause();
    public abstract void actionKeyDown(int keycode);
    public abstract void actionKeyUp(int keycode);
    public abstract void action(int keycode);
    public List<Element> getListOfElements(){ return listOfElements;}

    protected BitmapFont createFont(float dp)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        int fontSize = (int)(dp * Gdx.graphics.getDensity());
        parameter.size = fontSize;

        return generator.generateFont(parameter);
    }



    public int getHeight() {
        return (int) this.world.getHeight();
    }


    public int getWidth() {
        return (int) this.world.getWidth();
    }


    public List<Element> getElements() {
        return world.getElements();
    }



    public  void display(SpriteBatch batch){

    }
}
