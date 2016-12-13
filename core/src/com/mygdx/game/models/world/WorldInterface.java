package com.mygdx.game.models.world;

import com.mygdx.game.exceptions.GameException;
import com.mygdx.game.models.elements.Element;

import java.util.List;

/**
 * Created by aschmat on 13/12/2016.
 */
public interface WorldInterface {
    public List<Element> getElements();
    public void update()throws GameException;

    public  int getHeight();
    public  int getWidth();
}
