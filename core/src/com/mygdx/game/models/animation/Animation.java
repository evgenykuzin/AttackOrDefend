package com.mygdx.game.models.animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Animation {
    void init(Object... arguments);
    void draw(SpriteBatch batch);
}
