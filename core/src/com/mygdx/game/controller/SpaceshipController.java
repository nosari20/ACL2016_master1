package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.models.elements.MoveableElement;
import com.mygdx.game.models.elements.Spaceship;
import com.mygdx.game.models.world.World;

/**
 * Created on 10/11/2016.
 * Controlleur direction du vaisseau.
 */
public class SpaceshipController implements InputProcessor{

    /**
     * Vaisseau
     */
    private Spaceship spaceShip;
    private World world;

    /**
     * Constructeur recupere le vaisseau du monde
     * @param world
     */
    public SpaceshipController(World world){
        this.world = world;
        this.spaceShip = this.world.getSpaceShip();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(!this.spaceShip.isDie()) {
            switch (keycode) {
                case Input.Keys.LEFT:
                case Input.Keys.Q:
                    this.spaceShip.setDirection(MoveableElement.Direction.WEST);
                    this.spaceShip.move();
                    break;
                case Input.Keys.RIGHT:
                case Input.Keys.D:
                    this.spaceShip.setDirection(MoveableElement.Direction.EST);
                    this.spaceShip.move();
                    break;
                case Input.Keys.SPACE:
                    Gdx.app.log("Test", "Launched missile !");
                    this.world.addMissile();
                    break;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        MoveableElement.Direction dir = MoveableElement.Direction.NORTH;
        switch (keycode){
            case Input.Keys.LEFT:
            case Input.Keys.Q:
                dir = MoveableElement.Direction.WEST;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                dir = MoveableElement.Direction.EST;
                break;
        }
        if((dir.equals(this.spaceShip.getDirection()))){
            this.spaceShip.stop();
        }

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