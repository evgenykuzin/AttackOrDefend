package com.mygdx.game.models.hud;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.resources.Prefs;
import com.mygdx.game.resources.RDim;
import com.mygdx.game.resources.RTextures;
import com.mygdx.game.screens.GameScreen;

public class Hud {
    private int money;
    private boolean pauseViewShown;
    private Sprite pauseButton;
    private Sprite quitButton;
    private int playerHealthBlocks;
    private int botHealthBlocks;
    private int level;
    private int actualBlocksCount;
    private int levelComplitBlockCount;
    private Prefs prefs;
    public Hud(GameScreen gs){
        prefs = gs.game.prefs;
        money = prefs.getPrefMoney();
        level = prefs.getLevel();
        playerHealthBlocks = 60;
        botHealthBlocks = 60;
        actualBlocksCount = 0;
        pauseViewShown = false;
        pauseButton = new Sprite(RTextures.hud_pause_btn);
        pauseButton.setBounds(10, 10, RDim.mm_start_width, RDim.mm_start_height);
        quitButton = new Sprite(RTextures.hud_quit);
        quitButton.setBounds(220, 200, 200, 100);
    }

    public int getMoney(){
        return money;
    }

    public void addMoney(int money){
        if (this.money + money >= 0) {
            this.money += money;
            prefs.setMoney(this.money);
        }

    }

    public void setPauseViewShown(boolean t) {
        pauseViewShown = t;
    }

    public boolean isPauseViewShown(){
        return pauseViewShown;
    }

    public Sprite getPauseButton(){
        return pauseButton;
    }

    public Sprite getQuitButton(){
        return quitButton;
    }

    public void missPlayerHealthBlocks(int h){
        playerHealthBlocks -= h;
    }

    public void incPlayerHealthBlocks(int h) {
        playerHealthBlocks += h;}

    public void setPlayerHealthBlocks(int h) {
        playerHealthBlocks = h;}

    public int getPlayerHealthBlocks(){
        return playerHealthBlocks;
    }

    public int getBotHealthBlocks(){
        return botHealthBlocks;
    }

    public void missBotHealthBlocks(int h){
        botHealthBlocks -= h;
    }

    public void incBotHealthBlocks(int h) {
        botHealthBlocks += h;}

    public void setBotHealthBlocks(int h) {
        botHealthBlocks = h;}


    public int getLevel(){
        return level;
    }

    public void nextLevel(){
        level++;
        prefs.setLevel(this.level);
    }

}
