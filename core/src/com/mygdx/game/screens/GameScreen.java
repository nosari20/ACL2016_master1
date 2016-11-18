package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;
import com.mygdx.game.controller.SpaceshipController;
import com.mygdx.game.models.elements.Alien;
import com.mygdx.game.models.elements.Spaceship;
import com.mygdx.game.models.world.World;


/**
 * Created by aschmat on 08/11/2016.
 */
public class GameScreen implements Screen,ScreenGameConfig{
    private GameMain gm;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private World world;
    private Spaceship spaceShip;
    private Alien alien;
    private SpaceshipController spaceshipController;

    public GameScreen(GameMain gameMain) {
        this.world = new World();
        this.gm = gameMain;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(this.world.getSize().x*ppux,this.world.getSize().y*ppuy, camera);
        camera.position.set(((this.world.getSize().x * ppux) / 2f), (this.world.getSize().y * ppuy) / 2f, 0);
        camera.update();

        this.spaceShip = world.getSpaceShip();
        this.alien = world.getAlien();
        this.spaceshipController = new SpaceshipController(this.spaceShip);
        Gdx.input.setInputProcessor(this.spaceshipController);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(spaceShip.getTexture(), (spaceShip.getPosition().x+(spaceShip.getSize().x/2))*ppux,(spaceShip.getPosition().y-(spaceShip.getSize().y/2))*ppuy, spaceShip.getSize().x*ppux,spaceShip.getSize().y*ppuy);
        batch.draw(alien.getTexture(), (alien.getPosition().x+(alien.getSize().x/2))*ppux,(alien.getPosition().y-(alien.getSize().y/2))*ppuy, alien.getSize().x*ppux,alien.getSize().y*ppuy);
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
