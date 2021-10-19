package com.mygdx.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;

public class Prefs {
    private Preferences pref;
    public boolean hasSound;
    public int completedLevel;
    public int money;
    public int level;
    public int fluffyInteger;
    public Prefs(){
        pref = Gdx.app.getPreferences("My Preferences");
        hasSound = pref.getBoolean("hasSound",true);
        completedLevel=pref.getInteger("level",0);
        money = pref.getInteger("money", 0);
        level = pref.getInteger("level", 1);
        fluffyInteger = pref.getInteger("f_i", 0);
    }

    public boolean getSelected(int fi){
        if (fluffyInteger == fi){
            return true;
        } else return false;
    }

    public void setFluffy(int fluffyInteger){
        this.fluffyInteger = fluffyInteger;
        pref.putInteger("f_i", fluffyInteger);
        pref.flush();
    }


    public Preferences getPref(){
        return pref;
    }

    public int getPrefMoney(){
        return pref.getInteger("money");
    }

    public void setMoney(int money){
        this.money = money;
        pref.putInteger("money", money);
        pref.flush();
    }

    public void setLevel(int level){
        this.level = level;
        pref.putInteger("level", level);
        pref.flush();
    }

    public void setSound(boolean hasSound){
        this.hasSound = hasSound;
        pref.putBoolean("hasSound",hasSound);
        pref.flush();
    }

    public boolean hasSound(){
        return hasSound;
    }

    //should be called once when we need to increase my level
    public void increaseLevel(){
        completedLevel++;
        pref.putInteger("level",completedLevel);
        pref.flush();
    }

    public int getLevel(){
        return this.level;
    }
}
