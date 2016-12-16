package com.mygdx.game.adapter;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.exceptions.GameException;
import com.mygdx.game.exceptions.SpaceshipDieException;
import com.mygdx.game.models.elements.Element;
import com.mygdx.game.models.elements.MoveableElement;
import com.mygdx.game.models.world.World;
import com.mygdx.game.ressources.TexturesRepository;

/**
 * Created by aschmat on 10/12/2016.
 */
public class SpaceInvaderAdapter extends Adapter {
    private BitmapFont levelDisplay;
    private BitmapFont scoreDisplay;
    public SpaceInvaderAdapter() throws GameException{
        super(new World());
        levelDisplay = createFont(64);
        scoreDisplay = createFont(64);

    }

    @Override
    public void update() {
        try {
            this.world.update();
        } catch (GameException e) {
            if(e instanceof SpaceshipDieException)
                this.restart();
        }

    }

    @Override
    public void restart() {
        try {
            this.world = new World();
        } catch (GameException e) {
            e.printStackTrace();
        }
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
    public void actionKeyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
            case Input.Keys.Q:
                ((World)world).setSpaceShipDirection(MoveableElement.Direction.WEST);
                ((World)world).makeSpaceShipMove();
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                ((World)world).setSpaceShipDirection(MoveableElement.Direction.EST);
                ((World)world).makeSpaceShipMove();
                break;
            case Input.Keys.SPACE:
                // Gdx.app.log("Test", "Launched missile !");
                try {
                    ((World)world).addMissile();
                } catch (GameException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void actionKeyUp(int keycode) {
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
        if((dir.equals(((World)world).getSpaceShipDirection()))){
            ((World)world).makeSpaceShipStop();
        }
    }

    @Override
    public void display(SpriteBatch batch) {

        for(Element e : world.getElements()) {
            batch.draw(TexturesRepository.getInstance().getTexture(e).getTextureRegion(), (e.getPosition().x ) * ppux, (e.getPosition().y ) * ppuy, TexturesRepository.getInstance().getTexture(e).getSize().x * ppux, TexturesRepository.getInstance().getTexture(e).getSize().y * ppuy);
        }

        levelDisplay.setColor(1.0f,1.0f,1.0f,1.0f);
        levelDisplay.draw(batch, "Level "+((World)world).getLevel(), LEVEL_X * ppux,LEVEL_Y * ppuy);

        scoreDisplay.setColor(1.0f,1.0f,1.0f,1.0f);
        scoreDisplay.draw(batch, "Score "+(int)(((World)world).getNbKilledAliens() * ALIEN_VALUE) , LEVEL_X * ppux,(LEVEL_Y - 3 )* ppuy);
    }


}
