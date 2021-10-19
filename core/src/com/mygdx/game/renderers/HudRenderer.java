package com.mygdx.game.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;
import com.mygdx.game.models.hud.Hud;
import com.mygdx.game.resources.RDim;
import com.mygdx.game.resources.RFonts;
import com.mygdx.game.resources.RTextures;
import com.mygdx.game.screens.GameScreen;

public class HudRenderer extends Renderer {
    private Hud hud;
    BitmapFont bitmapFont;
    BitmapFont healthFont;

    public HudRenderer(MyGame game, GameScreen gs) {
        super(game);
        camera = new OrthographicCamera();
        camera = new OrthographicCamera(RDim.DEVICE_WIDTH, RDim.DEVICE_HEIGHT);
        setCamera(RDim.DEVICE_WIDTH / 2f, RDim.DEVICE_HEIGHT / 2f);
        batch = new SpriteBatch();
        hud = gs.getHud();
        bitmapFont = new BitmapFont(RFonts.px25);
        bitmapFont.setColor(Color.GOLD);
        healthFont = new BitmapFont(RFonts.px15);
        healthFont.setColor(Color.RED);
    }

    @Override
    public void render() {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        //bitmapFont.draw(batch, "score " + hud.getMoney(), 15, 20);
      //  bitmapFont.draw(batch, "level " + hud.getLevel(), RDim.DEVICE_WIDTH - 180, RDim.DEVICE_HEIGHT - 32);
        healthFont.draw(batch, "player: " + hud.getPlayerHealthBlocks(), RDim.BLOCK_WIDTH*3, RDim.FLORE_Y - RDim.FLORE_Y / 2);
        healthFont.draw(batch, "bot: " + hud.getBotHealthBlocks(), RDim.BLOCK_WIDTH*3, RDim.ROOF_Y + RDim.FLORE_Y + RDim.FLORE_Y/2);
        batch.draw(RTextures.hud_pause_btn, 10, RDim.DEVICE_HEIGHT - RDim.hud_pause_height, RDim.hud_pause_width, RDim.hud_pause_height);
        if (hud.isPauseViewShown()) {
            batch.draw(RTextures.hud_pause_view, RDim.DEVICE_WIDTH / 4, RDim.BLOCK_HEIGHT, 300, 400);
            batch.draw(RTextures.hud_quit, 220, 200, 200, 100);
        }
        int posX = 400;
//        for (int i = 0; i < hud.getPlayerHealthBlocks(); i++){
//            posX += 40;
//            batch.draw(RTextures.heart, posX, 5, 32, 32);
//        }
        batch.end();
    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
        batch.dispose();
    }

}

