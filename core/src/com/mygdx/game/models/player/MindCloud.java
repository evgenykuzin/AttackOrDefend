package com.mygdx.game.models.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.blocks.Block;
import com.mygdx.game.models.blocks.CasualBlock;
import com.mygdx.game.models.blocks.Shield;
import com.mygdx.game.resources.RDim;
import com.mygdx.game.resources.RTextures;

import java.util.ArrayList;

public class MindCloud {
    private Vector2 position;
    private int width;
    private int height;
    private Fluffy fluffy;
    private ArrayList<Block> blocks;
    public MindCloud(Fluffy fluffy){
        this.fluffy = fluffy;
        width = 150;
        height = 100;
        position = new Vector2();
        blocks = new ArrayList<Block>();
        generate();
    }
    public void generate(){
       // ArrayList<BLock> blocks = new ArrayList<BLock>();
        blocks.clear();
         if (blocks.size() < 4) {
            for (int i = 0; i < 4; i++) {
                Block block = new Shield(new Vector2(), 1, 1);
                blocks.add(block);
            }
        }
    }

    public ArrayList<Block> getBlocks(){
        return blocks;
    }

    public void drawMindCloud(SpriteBatch batch){
        position.x = fluffy.getPosition().x - RDim.BLOCK_WIDTH /2 - 60;
        position.y = fluffy.getPosition().y - RDim.BLOCK_HEIGHT /2 - 30;
        batch.draw(RTextures.mindCloud, position.x , position.y, width, height);
        float posX = position.x + 5;
        for (Block block : blocks){
            posX += 25;
            batch.draw(block.getTexture(), posX, position.y + 30
                    , width/5,height/5);
        }
    }
}
