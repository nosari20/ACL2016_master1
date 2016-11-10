package com.mygdx.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.models.elements.MoveableElement;
import com.mygdx.game.models.elements.Spaceship;

/**
 * Created on 10/11/2016.
 * Controlleur direction du vaisseau.
 */
public class SpaceshipController implements InputProcessor{

    /**
     * Vaisseau
     */
    private Spaceship spaceShip;

    /**
     * Constructeur recupere le vaisseau du monde
     * @param spaceShip
     */
    public SpaceshipController(Spaceship spaceShip){
        this.spaceShip = spaceShip;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
                this.spaceShip.setDirection(MoveableElement.Direction.WEST);
                break;
            case Input.Keys.RIGHT:
                this.spaceShip.setDirection(MoveableElement.Direction.EST);
                break;
        }
        this.spaceShip.move();
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        this.spaceShip.stop();
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}