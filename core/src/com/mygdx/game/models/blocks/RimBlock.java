package com.mygdx.game.models.blocks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RimBlock extends Block{

    public RimBlock(Vector2 position, int dirrection, int team) {
        super(position, dirrection, team);
    }

    @Override
    public void initialize() {
         setType(TYPE.DEFEND);
         setCanFall(false);
    }

    @Override
    public void drawBlock(SpriteBatch batch) {

    }

    @Override
    public void specialBlockAction(BlockManager blockManager, Block b2) {

    }
}
