package com.mygdx.game.adapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.exceptions.GameException;
import com.mygdx.game.models.elements.Element;

import java.util.List;

/**
 * Created by aschmat on 10/12/2016.
 */
public abstract class Adapter {
    List<Element> listOfElements;

    public Adapter(){}


    public abstract void update() throws GameException;
    public abstract void restart();
    public abstract void show();
    public abstract void dispose();
    public abstract void pause();;
    public abstract void action(int keycode);
    public abstract List<Element> getListOfElements();

    private BitmapFont createFont(float dp)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        int fontSize = (int)(dp * Gdx.graphics.getDensity());
        parameter.size = fontSize;

        return generator.generateFont(parameter);
    }

}
