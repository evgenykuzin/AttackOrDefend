package com.mygdx.game.models.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.resources.RTextures;

public class FireBoll extends Block{
    public FireBoll(Vector2 position, int dirrection, int team) {
        super(position, dirrection, team);
    }

    @Override
    public void initialize() {
        setCanFall(true);
        setSpeed(0.8f);
        setAcceleration(0.005f);
        setType(TYPE.ATTACK);
        Texture texture = RTextures.fireball_block;
        TextureRegion textureRegion = new TextureRegion(texture);
        setTextureRegion(textureRegion);
    }

    @Override
    public void drawBlock(SpriteBatch batch) {
        if (textureRegion.getTexture() != null) {
            batch.draw(textureRegion.getTexture(), getPosition().x, getPosition().y, getWidth(), getHeight());
        }
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
