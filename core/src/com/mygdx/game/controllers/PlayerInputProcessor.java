package com.mygdx.game.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGame;
import com.mygdx.game.models.blocks.Block;
import com.mygdx.game.models.blocks.BlockManager;
import com.mygdx.game.models.hud.Hud;
import com.mygdx.game.models.player.Fluffy;
import com.mygdx.game.resources.RConst;
import com.mygdx.game.resources.RDim;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class PlayerInputProcessor implements InputProcessor {
    Vector2 lastTouch;
    Fluffy fluffy;
    boolean pauseTouched;
    boolean pauseViewShown;
    boolean quitTouched;
    MyGame game;
    Hud hud;
    BlockManager blockManager;
    private GameScreen gs;
    Block previousBlock;
    Block topBlock;
    float blockX;
    int swichDirrection;
    boolean turn;
    public PlayerInputProcessor(MyGame game, GameScreen gs, Fluffy fluffy) {
        this.game = game;
        this.gs = gs;
        turn = true;
        hud = gs.getHud();
        lastTouch = new Vector2();
        this.fluffy = fluffy;
        blockX = 0;
        swichDirrection = 1;
        blockManager = gs.getBlockManager();
        pauseTouched = false;
        pauseViewShown = false;
        quitTouched = false;
        previousBlock = blockManager.getTopBlock();
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

    public void setTurn(boolean turn){
        this.turn = turn;
    }

    public boolean isPlayerTurn(){
        return turn;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (hud.getPauseButton().getBoundingRectangle().contains(screenX, screenY)) {
            pauseTouched = true;
        } else if (hud.getQuitButton().getBoundingRectangle().contains(screenX, screenY)) {
            quitTouched = true;
        }
        if (turn) {
            previousBlock = blockManager.getTopBlock();
            int team = swichDirrection == 1 ? 1 : 2;
            team = RConst.ROBOT_TEAM;
            swichDirrection = RConst.UP_DIR;
            topBlock = blockManager.initBlock(team, swichDirrection, screenX);
            topBlock.stop();
            gs.getScenRenderer().getDirrectionArrow().init(topBlock);
        }
//        topBlock.setSpeed(0);
//        topBlock.setAcceleration(0);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pauseTouched) {
            if (!pauseViewShown) {
                pauseTouched = false;
                pauseViewShown = true;
                hud.setPauseViewShown(true);
            } else {

                pauseTouched = false;
                pauseViewShown = false;
                hud.setPauseViewShown(false);
            }
        } else if (quitTouched && pauseViewShown) {
            quitTouched = false;
            game.setScreen(new MainMenuScreen(game));
        } else {
            if (turn) {
                // blockManager.initBlock(1, blockX);
                if (previousBlock == blockManager.getTopBlock() && blockManager.getTopBlock() != null) {
                    blockManager.getTopBlock().setSpeed(9);
                }
                //blockManager.blockList.add(topBlock);
                lastTouch.set(screenX, screenY);
                topBlock.go(5, 0.5f);
                swichDirrection *= -1;
                //setTurn(false);
            }
        }
        if (turn) {
            topBlock.setSpeed(5);
            topBlock.setAcceleration(0.5f);
            //setTurn(false);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (blockManager.getTopBlock() == null) return false;
        Vector2 newTouch = new Vector2(screenX, screenY);
        Vector2 delta = newTouch.cpy().sub(lastTouch);
        boolean sub = newTouch.x + lastTouch.x == RDim.BLOCK_WIDTH;
        lastTouch = newTouch;
        float blockX = topBlock.getPosition().x;
        float blockY = topBlock.getPosition().y;
        boolean right = delta.x > 0 && screenX >= blockX + RDim.BLOCK_WIDTH;
        boolean left = delta.x < 0 && screenX <= blockX;
        boolean down = delta.y > 0 && screenY >= blockY + RDim.BLOCK_HEIGHT;
        boolean up = delta.y < 0 && screenY <= blockY;

//        boolean overlaps = false;
//        for (Block b : blockManager.blockList){
//            overlaps = (b.getBoundingRectangle().contains(blockX, blockY));
//            if (overlaps) break;
//        }
       // float subX = 0;
       // if (blockY > blockManager.getHigherBlockY()+RDim.BLOCK_HEIGHT && !overlaps) {
            if (right) {
                blockManager.moveRight();
                //subX = blockManager.getTopBlock().getPosition().x;
            } else if (left) {
                blockManager.moveLeft();
            }
            if (up) {
                topBlock.setDirrection(RConst.UP_DIR);
            } else if (down) {
                topBlock.setDirrection(RConst.DOWN_DIR);
            }

        //} else blockManager.getTopBlock().setSpeed(8);

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
