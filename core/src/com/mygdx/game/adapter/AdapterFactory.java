package com.mygdx.game.adapter;

/**
 * Created by aschmat on 10/12/2016.
 */
public class AdapterFactory {

    public static Adapter getAdapter(GamesToPlug gtp){
        switch (gtp){
            case SPACEINVADER:
                return new SpaceInvaderAdapter();
        }
        return null;
    }
}
