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
import com.mygdx.game.exceptions.GameException;
import com.mygdx.game.exceptions.SpaceshipDieException;
import com.mygdx.game.models.elements.Alien;
import com.mygdx.game.models.elements.Element;
import com.mygdx.game.models.elements.MissileAlien;
import com.mygdx.game.models.elements.Spaceship;
import com.mygdx.game.models.world.World;

import java.util.ArrayList;
import java.util.List;


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
    private SpaceshipController spaceshipController;
    private ArrayList<Alien> listAlien;
    private List<Element> elements;

    public GameScreen(GameMain gameMain) {
        this.world = new World();
        this.gm = gameMain;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(this.world.getSize().x*ppux,this.world.getSize().y*ppuy, camera);
        camera.position.set(((this.world.getSize().x * ppux) / 2f), (this.world.getSize().y * ppuy) / 2f, 0);
        camera.update();
        this.spaceShip = world.getSpaceShip();
        this.listAlien = world.getListAlien();
        this.spaceshipController = new SpaceshipController(this.world);
        Gdx.input.setInputProcessor(this.spaceshipController);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.elements = world.getElements();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(spaceShip.getTexture(), (spaceShip.getPosition().x)*ppux,(spaceShip.getPosition().y)*ppuy, spaceShip.getSize().x*ppux,spaceShip.getSize().y*ppuy);
     for(Alien a : listAlien){
            if(a != null)
                batch.draw(a.getTexture(), (a.getPosition().x ) * ppux, (a.getPosition().y ) * ppuy, a.getSize().x * ppux, a.getSize().y * ppuy);

        }
        for(Element e : elements) {
            batch.draw(e.getTexture(), (e.getPosition().x ) * ppux, (e.getPosition().y ) * ppuy, e.getSize().x * ppux, e.getSize().y * ppuy);
        }

            batch.end();

        try {
            world.update();
        } catch (GameException e) {
            if(e instanceof SpaceshipDieException)
                this.restart();
        }

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


    public void restart(){
        this.world = new World();
        this.spaceShip = world.getSpaceShip();
        this.listAlien = world.getListAlien();
        this.spaceshipController = new SpaceshipController(this.world);
        Gdx.input.setInputProcessor(this.spaceshipController);
    }
}
