package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;
import com.mygdx.game.controller.GameListener;
import com.mygdx.game.models.World;
import com.mygdx.game.models.elements.SpaceShip;

/**
 * Created by aschmat on 08/11/2016.
 */
public class GameScreen implements Screen,ScreenGameConfig{
    private GameMain gm;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private World world;
    private SpaceShip spaceShip;
    private GameListener gameListener;

    public GameScreen(GameMain gameMain) {
        this.gm = gameMain;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH*ppux,WORLD_HEIGHT*ppuy, camera);
        camera.position.set(((WORLD_WIDTH * ppux) / 2f), (WORLD_HEIGHT * ppuy) / 2f, 0);
        camera.update();
        this.world = new World();
        this.spaceShip = world.getSpaceShip();
        this.gameListener = new GameListener(spaceShip);
        Gdx.input.setInputProcessor(this.gameListener);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(spaceShip.getTexture(), spaceShip.getPosX()*ppux,spaceShip.getPosY()*ppuy, spaceShip.getLength()*ppux,spaceShip.getWidth()*ppuy);
        batch.end();
        world.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
