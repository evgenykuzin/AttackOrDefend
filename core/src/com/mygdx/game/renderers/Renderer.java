package com.mygdx.game.renderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;

public abstract class Renderer {
    MyGame game;
    OrthographicCamera camera;
    public SpriteBatch batch;
    public Renderer(MyGame game, OrthographicCamera camera){
        this.game = game;
        this.camera = camera;
       // batch = game.batch;
    }

    public Renderer(MyGame game){
        this.game = game;
    }

    public void setCamera(float x, float y) {
        this.camera.position.set(x, y, 0);
        this.camera.update();
    }

    public abstract void render();

    public abstract void dispose();
}
