package com.mygdx.game.models.blocks;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.resources.RTextures;


public class BombBlock extends Block {
    BitmapFont font;
    public BombBlock(Vector2 position, int dirrection, int team) {
        super(position, dirrection, team);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void drawBlock(SpriteBatch batch) {

    }


    @Override
    public void specialBlockAction(BlockManager blockManager, Block b2) {

        Block block = b2;
        if (b2 == null) block = this;
        BlockManager.Neighbors neighbors = new BlockManager.Neighbors(block, blockManager.blockList, 1);
        neighbors.findNeighbors(1);
        blockManager.destroyBlocks(neighbors.getNeighbors());
        blockManager.destroyBlock(block);
        blockManager.destroyBlock(this);
        //return true;
    }
}
