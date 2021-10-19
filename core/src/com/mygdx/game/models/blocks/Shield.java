package com.mygdx.game.models.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.resources.RPaths;
import com.mygdx.game.resources.RTextures;

import java.util.Random;

public class Shield extends Block {

    public Shield(Vector2 position, int dirrection, int team) {
        super(position, dirrection, team);
    }

    @Override
    public void initialize() {
        setCanFall(true);
        setSpeed(0.8f);
        setAcceleration(0.005f);
        setType(TYPE.DEFEND);
        //TextureAtlas textureAtlas = new TextureAtlas(RPaths.wall_atlas);
        Texture texture = RTextures.wall_block;
//        int i = 1;
//        for (Texture t : textureAtlas.getTextures()) {
//            if (new Random().nextInt(i) == 1) texture = t;
//            i++;
//        }
        TextureRegion textureRegion = new TextureRegion(texture);
        setTextureRegion(textureRegion);
        //textureRegion.setRegion(RTextures.bomb_block);
    }

    @Override
    public void drawBlock(SpriteBatch batch) {
        if (textureRegion.getTexture() != null) {
            batch.draw(textureRegion.getTexture(), getPosition().x, getPosition().y, getWidth(), getHeight());
        }
    }

    @Override
    public void specialBlockAction(BlockManager blockManager, Block b2) {

    }
}
