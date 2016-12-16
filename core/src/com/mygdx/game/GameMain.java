package com.mygdx.game;

import com.badlogic.gdx.Game;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.exceptions.GameException;
import com.mygdx.game.screens.GameScreen;

public class GameMain extends Game {
    SpriteBatch batch;
    Texture img;
    private GameScreen gameScreen;
    @Override
    public void create () {
        try {
            gameScreen = new GameScreen(this);
        } catch (GameException e) {
            e.printStackTrace();
        }
        updateMainScreen(gameScreen);
        //batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");
    }

    private void updateMainScreen(Screen screen){
        if(screen != null)
         this.setScreen(screen);
    }

    @Override
    public void render () {
        super.render();

    }

    @Override
    public void dispose () {
        /*batch.dispose();
        img.dispose();*/
    }
}
