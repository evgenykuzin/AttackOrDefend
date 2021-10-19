package com.mygdx.game.controllers;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.MyGame;
import com.mygdx.game.renderers.DeathMenuRenderer;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class DeathMenuInputProcessor implements InputProcessor {

    boolean resetDown = false;
    boolean backToMenuDown = false;
    boolean newGameDown = false;
    boolean shareDown = false;
    DeathMenuRenderer deathMenuRenderer;
    MyGame game;
    public DeathMenuInputProcessor(DeathMenuRenderer deathMenuRenderer, MyGame game){
        this.deathMenuRenderer = deathMenuRenderer;
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (deathMenuRenderer.resetBtn.getBoundingRectangle().contains(screenX, screenY)){
            resetDown = true;
        } else if (deathMenuRenderer.shareBtn.getBoundingRectangle().contains(screenX, screenY)){
            shareDown = true;
        } else if (deathMenuRenderer.backToMenuBtn.getBoundingRectangle().contains(screenX, screenY)) {
            backToMenuDown = true;
        } else if (deathMenuRenderer.newGameBtn.getBoundingRectangle().contains(screenX, screenY)){
            newGameDown = true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (resetDown){
            deathMenuRenderer.reset();
            resetDown = false;
            System.out.println("reset");
        } else if (newGameDown){
            //game.gameScreen.dispose();
            game.setScreen(new GameScreen(game));
            newGameDown = false;
            System.out.println("new game");
        } else if (backToMenuDown){
            //game.gameScreen.dispose();
            game.setScreen(new MainMenuScreen(game));
            backToMenuDown = false;
            System.out.println("back");
        }
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
