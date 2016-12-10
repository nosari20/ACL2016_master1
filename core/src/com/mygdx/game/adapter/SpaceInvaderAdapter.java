package com.mygdx.game.adapter;

import com.mygdx.game.exceptions.GameException;
import com.mygdx.game.models.elements.Alien;
import com.mygdx.game.models.elements.Element;
import com.mygdx.game.models.elements.Spaceship;
import com.mygdx.game.models.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aschmat on 10/12/2016.
 */
public class SpaceInvaderAdapter extends Adapter {

    private World world;

    public SpaceInvaderAdapter(){
        this.world = new World();
    }

    @Override
    public void update() throws GameException {
        this.world.update();
    }

    @Override
    public void restart() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void action(int keycode) {

    }

    @Override
    public List<Element> getListOfElements() {
        return world.getElements();
    }



}
