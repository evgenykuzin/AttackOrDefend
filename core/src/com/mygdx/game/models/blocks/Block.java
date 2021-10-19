package com.mygdx.game.models.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.resources.RDim;

public abstract class Block {
    TextureRegion textureRegion;
    private Vector2 position;
    private int width;
    private int height;
    private float speed;
    private float acc;
    private boolean canFall;
    private int dirrection;
    private int team;
    private Enum<TYPE> blockType;

    public enum TYPE {
        ATTACK,
        DEFEND
    }


    public Block(Vector2 position, int dirrection, int team) {
        this.position = position;
        blockType = null;
        textureRegion = new TextureRegion();
        width = RDim.BLOCK_WIDTH;
        height = RDim.BLOCK_HEIGHT;
        this.dirrection = dirrection;
        this.team = team;
        initialize();
    }

    public abstract void initialize();

    public abstract void drawBlock(SpriteBatch batch);

    public abstract void specialBlockAction(BlockManager blockManager, Block b2);

    public Enum<TYPE> getType() {
        return blockType;
    }

    public void setType(Enum<TYPE> type) {
        blockType = type;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public boolean upDir() {
        return dirrection == -1;
    }

    public boolean downDir() {
        return dirrection == 1;
    }

    public int getDirrection() {
        return dirrection;
    }

    public void setDirrection(int dirrection) {
        this.dirrection = dirrection;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public void fall() {
        if (canFall) {
            speed += acc;
            position.y -= speed * dirrection;
        }
    }

    public void fallAsWall() {
        if (canFall) {
            speed += 1;
            position.y -= speed * dirrection;
        }
    }

    public void setCanFall(boolean canFall){
         this.canFall = canFall;
    }

    public void stop() {
        canFall = false;
    }

    public void go() {
        canFall = true;
    }

    public void go(float speed, float acc) {
        canFall = true;
        this.speed = speed;
        this.acc = acc;
    }

    public void setSpeed(float s) {
        speed = s;
    }

    public float getSpeed() {
        return speed;
    }

    public void setAcceleration(float a) {
        acc = a;
    }

    public float getAcc() {
        return acc;
    }

    public Texture getTexture() {
        return textureRegion.getTexture();
    }

    public boolean isCanFall() {
        return canFall;
    }

    public Enum<TYPE> getBlockType() {
        return blockType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((blockType == null) ? 0 : blockType.hashCode());
        result = prime * result + width + height;
        result = prime * result + ((textureRegion == null) ? 0 : textureRegion.hashCode());
        return result;
    }


    public void moveRight(float speed) {
        if (position.x < RDim.DEVICE_WIDTH - width) {
            position.x += speed;
        }
    }

    public void moveLeft(float speed) {
        if (position.x > 0) {
            position.x -= speed;
        }
    }

    public float getY() {
        return position.y;
    }

    public float getX() {
        return position.x;
    }

    public void setX(float x) {
        position.x = x;
    }

    public void setY(float y) {
        position.y = y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Vector2 getPosition() {
        return position;
    }

}
