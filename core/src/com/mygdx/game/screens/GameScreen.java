package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;
import com.mygdx.game.adapter.Adapter;
import com.mygdx.game.adapter.AdapterFactory;
import com.mygdx.game.adapter.GamesToPlug;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.models.elements.Alien;
import com.mygdx.game.models.elements.Element;
import com.mygdx.game.ressources.TexturesRepository;

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
    private ArrayList<Alien> listAlien;
    private List<Element> elements;
    private Adapter game;
    private Controller controller;
    public GameScreen(GameMain gameMain) {
        this.game = AdapterFactory.getAdapter(GamesToPlug.SPACEINVADER);
        this.controller = new Controller(game);
        this.gm = gameMain;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(this.game.getWidth()*ppux,this.game.getHeight()*ppuy, camera);
        camera.position.set(((this.game.getWidth() * ppux) / 2f), (this.game.getHeight() * ppuy) / 2f, 0);
        camera.update();
        Gdx.input.setInputProcessor(this.controller);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.elements = game.getElements();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        game.display(batch);
        batch.end();
        this.game.update();

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
        game.restart();
    }

    private BitmapFont createFont(float dp)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        int fontSize = (int)(dp * Gdx.graphics.getDensity());
        parameter.size = fontSize;

        return generator.generateFont(parameter);
    }
}