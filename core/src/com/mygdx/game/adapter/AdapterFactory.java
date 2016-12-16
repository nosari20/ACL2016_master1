package com.mygdx.game.adapter;

import com.mygdx.game.exceptions.GameException;

/**
 * Created by aschmat on 10/12/2016.
 */
public class AdapterFactory {

    public static Adapter getAdapter(GamesToPlug gtp) throws GameException {
        switch (gtp){
            case SPACEINVADER:
                return new SpaceInvaderAdapter();
        }
        return null;
    }
}
