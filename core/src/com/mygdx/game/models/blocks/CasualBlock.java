package com.mygdx.game.models.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.resources.RFonts;
import com.mygdx.game.resources.RTextures;

import java.util.Random;
import java.util.Set;

public class CasualBlock {
//BitmapFont font;
//    public CasualBlock(Vector2 position, int dirrection, int team) {
//        super(position, dirrection, team);
//    }
//
//    @Override
//    public void initialize() {
//        textureRegion = new TextureRegion();
//        font = new BitmapFont(RFonts.px15);
//        setSpeed(0.5f);
//        setAcceleration(0.005f);
//        Random random = new Random();
//        while (blockType == null) {
//            for (Enum<Block.TYPE> type : Block.TYPE.values()) {
//                if (random.nextInt(7) == 1) {
//                    if (type != TYPE.SPECIAL) {
//                        blockType = type;
//                        break;
//                    }
//                }
//            }
//        }
//        if (blockType.equals(Block.TYPE.RED)){
//            textureRegion.setRegion(RTextures.red_block);
//            typeNumber = 5;
//        } else if (blockType.equals(Block.TYPE.BLUE)){
//            textureRegion.setRegion(RTextures.blue_block);
//            typeNumber = 3;
//        } else if (blockType.equals(Block.TYPE.PURPLE)){
//            textureRegion.setRegion(RTextures.purple_block);
//            typeNumber = 2;
//        } else if (blockType.equals(Block.TYPE.GREEN)){
//            textureRegion.setRegion(RTextures.green_block);
//            typeNumber = 4;
//        } else if (blockType.equals(Block.TYPE.GRAY)){
//            textureRegion.setRegion(RTextures.gray_block);
//            typeNumber = 6;
//        } else if (blockType.equals(Block.TYPE.GOLD)){
//            textureRegion.setRegion(RTextures.gold_block);
//            typeNumber = 7;
//        }
//    }
//
//    @Override
//    public void drawBlock(SpriteBatch batch) {
//        if (textureRegion.getTexture() != null) {
//            batch.draw(textureRegion.getTexture(), position.x, position.y, width, height);
//            if (digit != 0) {
//                font.draw(batch, digit + "", position.x + width / 3, position.y + height / 2);
//            }
//        }
//    }
//
//    @Override
//    public void specialBlockAction(BlockManager blockManager, Block b2) {
//        //return false;
//    }
//
//    @Override
//    public String toString() {
//        return "|"+blockType.toString()+ " " + digit +"|";
//    }

}
