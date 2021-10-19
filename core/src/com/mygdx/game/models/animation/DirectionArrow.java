package com.mygdx.game.models.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.models.blocks.Block;
import com.mygdx.game.resources.RDim;
import com.mygdx.game.resources.RTextures;

public class DirectionArrow implements Animation {
    private Texture arrow;
    private Texture body;
    private float x;
    private float y;
    private float arrowY;
    private float width;
    private float height;
    private boolean isInited = false;

    public DirectionArrow(Block block) {
        init(RTextures.back_arrow, RTextures.hud_pause_btn, block);
    }

    public DirectionArrow(){
    }

    public void init(Block block){
        init(RTextures.back_arrow, RTextures.hud_pause_btn, block);
    }

    public void init(Object... arguments) {
        arrow = (Texture) arguments[0];
        body = (Texture) arguments[1];
        Block block = (Block) arguments[2];
        this.x = block.getX();
        this.y = block.getY();
        arrowY = y;
        this.width = block.getWidth();
        this.height = block.getHeight();
        isInited = true;
    }

    public void draw(SpriteBatch batch) {
        if (isInited) {
            batch.draw(body, x, y, width, height * RDim.maxBlockLength);
            float arY = arrowY;
            for (int i = 0; i < RDim.maxBlockLength - 10; i++) {
                batch.draw(arrow, x, arY, width, height);
                arY += height * 2;
            }
            arrowY++;
            if (arrowY + height * (RDim.maxBlockLength - 10) >= height * RDim.maxBlockLength) {
                arrowY = y;
            }
        }
    }
}
