package com.mygdx.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class BlocksContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        World w = new World(new Vector2(0, -10), true);
        Body body = null;
        if(contact.getFixtureA() != null && contact.getFixtureA().getUserData() != null  &&contact.getFixtureA().getUserData().equals("b"))
            body = contact.getFixtureA().getBody();

        if(contact.getFixtureB() != null && contact.getFixtureB().getUserData() != null  && contact.getFixtureB().getUserData().equals("b"))
            body = contact.getFixtureB().getBody();

        if(body != null){
            body.setActive(false);
            //world.destroyBody(body);
        }
    }
}
