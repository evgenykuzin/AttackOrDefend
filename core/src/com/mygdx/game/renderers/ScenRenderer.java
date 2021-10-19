package com.mygdx.game.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.controllers.PlayerInputProcessor;
import com.mygdx.game.models.animation.DirectionArrow;
import com.mygdx.game.models.blocks.BlockManager;
import com.mygdx.game.models.blocks.Shield;
import com.mygdx.game.models.player.Fluffy;
import com.mygdx.game.models.player.MindCloud;
import com.mygdx.game.resources.RConst;
import com.mygdx.game.resources.RTextures;
import com.mygdx.game.screens.GameScreen;

import java.util.Random;

import javax.swing.text.Position;

public class ScenRenderer extends Renderer {
    public MyGame game;
    private Fluffy fluffy;
    BlockManager blockManager;
    public PlayerInputProcessor playerInputProcessor;
    private MindCloud mindCloud;
    GameScreen gs;
    private DirectionArrow directionArrow;
    public ScenRenderer(MyGame game, OrthographicCamera camera, GameScreen gs) {
        super(game, camera);
        this.gs = gs;
        batch = new SpriteBatch();
        fluffy = gs.getFluffy();
        mindCloud = gs.getMindCloud();
        playerInputProcessor = new PlayerInputProcessor(game, gs, fluffy);
        Gdx.input.setInputProcessor(playerInputProcessor);
        blockManager = gs.getBlockManager();
        directionArrow = new DirectionArrow();
        //directionArrow.init(RTextures.back_arrow, RTextures.hud_pause_btn, new Shield(new Vector2(0,64), RConst.UP_DIR, 1));

        // blockManager.initWall();
    }

    public DirectionArrow getDirrectionArrow() {
        return directionArrow;
    }


    @Override
    public void render() {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
//        fluffy.drawFluffy(batch);
//        mindCloud.drawMindCloud(batch);
        if (!blockManager.isFirstWallInited()) {
            blockManager.initWall(RConst.ROBOT_TEAM, RConst.DOWN_DIR);
        }
        if (blockManager.isFirstWallInited() && !blockManager.isSecondWallInited()) {
            blockManager.initWall(RConst.PLAYER_TEAM, RConst.UP_DIR);
        }
        if (new Random().nextInt(50) == 1) {
            // foodManager.initFood();
            //blockManager.initBlock();
        }
//        foodManager.drawFoods(batch);
//        foodManager.overlaps();
//        foodManager.allFalls();
        blockManager.drawBlocks(batch);
        //blockManager.onOverlaps();
//        if (blockManager.isWallInited()) {
        directionArrow.draw(batch);
        blockManager.allFalls();
        blockManager.drawTopBlock(batch);
        gs.getBot().drawRobotTopBlock(batch);
        blockManager.overlapsBlocks();
        blockManager.gravitation();
        blockManager.sendToHell();
        batch.end();
    }

    @Override
    public void dispose() {
        //Gdx.input.setInputProcessor(null);
        batch.dispose();
    }

}
