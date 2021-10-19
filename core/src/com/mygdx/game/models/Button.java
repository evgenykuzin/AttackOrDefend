package com.mygdx.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.models.store.Store;
import com.mygdx.game.resources.RDim;
import com.mygdx.game.resources.RFonts;
import com.mygdx.game.resources.RTextures;

public class Button {
    private Sprite sprite;
    private Texture texture;
    private boolean isClickable;
    private Vector2 position;
    private float width;
    private float height;
    private OnClickListener onClickListener;
    private String text;
    private BitmapFont bitmapFont;

    public Button(Texture texture, Vector2 position, float width, float height) {
        this.texture = texture;
        this.position = position;
        sprite = new Sprite(texture);
        sprite.setBounds(position.x, RDim.DEVICE_HEIGHT - position.y - height, width, height);
        isClickable = true;
        this.width = width;
        this.height = height;
    }

    public Button(String text, Vector2 position) {
        bitmapFont = new BitmapFont(RFonts.px25);
        bitmapFont.setColor(Color.WHITE);
        this.text = text;
        this.position = position;
        texture = RTextures.mm_start_btn;
        width = RDim.mm_start_width / 3;
        height = RDim.mm_start_height / 3;
        sprite = new Sprite(texture);
        //sprite.setBounds(position.x, position.y, width, height);
        isClickable = true;
    }

    public Button(String text, Vector2 position, Texture texture) {
        bitmapFont = new BitmapFont(RFonts.px25);
        bitmapFont.setColor(Color.WHITE);
        this.text = text;
        this.position = position;
        this.texture = texture;
        width = RDim.mm_start_width / 3;
        height = RDim.mm_start_height / 3;
        sprite = new Sprite(texture);
        //sprite.setBounds(position.x, position.y, width, height);
        isClickable = true;
    }

    public void drawButton(SpriteBatch batch, float moveX, float moveY) {
        position.x = moveX;
        position.y = moveY;
        batch.draw(texture, position.x, position.y,
                sprite.getWidth(), sprite.getHeight());
    }

//    public void drawButton(SpriteBatch batch){
//        batch.draw(texture, sprite.getX(), sprite.getY(),
//                sprite.getWidth(), sprite.getHeight());
//    }

    public boolean isClicked(float x, float y) {
        sprite.setPosition(position.x, position.y);
        return sprite.getBoundingRectangle().contains(x, y) && isClickable;
    }

    public boolean isClicked(float x, float y, Store s) {
        return sprite.getBoundingRectangle().contains(x, y) && isClickable;
    }

    public void setClickable(boolean t) {
        isClickable = t;
    }

    public interface OnClickListener {
        void onClick();
    }

    public void addOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void render(Batch batch) {
        batch.begin();
        if (onClickListener != null) {
            if (isClicked(Gdx.input.getX(), Gdx.input.getY())){
                onClickListener.onClick();
            }
        }
        batch.draw(texture, position.x, position.y,
                sprite.getWidth(), sprite.getHeight());
        if (bitmapFont != null) {
            bitmapFont.draw(batch, text, position.x + position.x / 3, position.y);
        }
        batch.end();
    }
}
