package com.mygdx.game.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;
import com.mygdx.game.controllers.DeathMenuInputProcessor;
import com.mygdx.game.controllers.PlayerInputProcessor;
import com.mygdx.game.resources.RTextures;

public class DeathMenuRenderer extends Renderer {

    public Sprite background;
    public Sprite resetBtn;
    public Sprite shareBtn;
    public Sprite backToMenuBtn;
    public Sprite newGameBtn;
    public boolean isGameOver;

    public DeathMenuRenderer(MyGame game) {
        super(game);
        float bpX = 150;
        float bpY = 150;
        float bw = 400;
        float bh = 500;
        background = new Sprite(RTextures.hud_pause_view);
        background.setBounds(bpX, bpY, bw, bh);
        resetBtn = new Sprite(RTextures.gold_block);
        resetBtn.setBounds(bpX + bw/4, bpY + bh/4, 100, 100);
        shareBtn = new Sprite(RTextures.gray_block, -200, 200, 50, 50);
        shareBtn.setBounds(bpX + bw/2 + bw/10, bpY, 100, 100);
        backToMenuBtn = new Sprite(RTextures.blue_block, 500, 500, 50, 50);
        newGameBtn = new Sprite(RTextures.red_block, 300, 300, 50, 50);
        batch = new SpriteBatch();
        isGameOver = false;
    }

    public void reset(){
        //game.gameScreen.incDeadLine(300);
        isGameOver = false;
        Gdx.input.setInputProcessor(game.gameScreen.getScenRenderer().playerInputProcessor);
    }


    @Override
    public void render() {
        batch.begin();
//        batch.setProjectionMatrix(camera.combined);
        if (isGameOver) {
            background.draw(batch);
            resetBtn.draw(batch);
            shareBtn.draw(batch);
            backToMenuBtn.draw(batch);
            newGameBtn.draw(batch);
//        batch.draw(background, 100, 100, 400, 400);
//        batch.draw(shareBtn, 200, 200, 50, 50);
//        batch.draw(backToMenuBtn, 300, 200, 50, 50);
//        batch.draw(newGameBtn, 200, 300, 50, 50);
//        batch.draw(resetBtn, 300, 300, 50, 50);
        }
        batch.end();
    }

    @Override
    public void dispose() {
//        background.dispose();
//        backToMenuBtn.dispose();
//        resetBtn.dispose();
//        shareBtn.dispose();
//        newGameBtn.dispose();

    }
}
