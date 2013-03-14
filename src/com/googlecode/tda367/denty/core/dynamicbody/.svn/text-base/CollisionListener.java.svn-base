package com.googlecode.tda367.denty.core.dynamicbody;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import com.googlecode.tda367.denty.constants.Hit;

/**
 * Implementation of ContactListener (JBox2D). The physics World triggers the
 * beginContact() and endContact() methods when two bodies start and stop
 * colliding. Our implementation notifies both bodies what part of them was
 * collided with, and any messages passed from the other body.
 * 
 * Each DynamicBody is responsible for handling the collision according to the
 * Hit-constants that are passed to it.
 * 
 */
public class CollisionListener implements ContactListener {

	private static CollisionListener instance;

	private CollisionListener() {
		// Private constructor to ensure that the class is a singleton.
	}

	public static CollisionListener getInstance() {
		if (CollisionListener.instance == null) {
			CollisionListener.instance = new CollisionListener();
		}
		return instance;
	}

	@Override
	public void beginContact(Contact c) {
		Object userData1 = c.getFixtureA().getBody().getUserData();
		Object userData2 = c.getFixtureB().getBody().getUserData();
		DynamicBody db1 = null;
		DynamicBody db2 = null;

		if (userData1 != null && userData1 instanceof DynamicBody) {
			db1 = (DynamicBody) userData1;

		}
		if (userData2 != null && userData2 instanceof DynamicBody) {
			db2 = (DynamicBody) userData2;
		}

		if (db1 != null && db2 != null) {
			Fixture f1 = c.getFixtureA();
			Fixture f2 = c.getFixtureB();
			Collection<Hit> c1 = db1.getHitConstants(f1);
			Collection<Hit> c2 = db2.getHitConstants(f2);
			if (c2 != null) {
				db1.beginContact(f1, c2);
			} else {
				db1.beginContact(f1, new ArrayList<Hit>());
			}

			if (c1 != null) {
				db2.beginContact(f2, c1);
			} else {
				db2.beginContact(f2, new ArrayList<Hit>());
			}
		} else if (db1 != null && db2 == null) {
			db1.beginContact(c.getFixtureA(), new ArrayList<Hit>());
		} else if (db2 != null && db1 == null) {
			db2.beginContact(c.getFixtureB(), new ArrayList<Hit>());
		}
	}

	@Override
	public void endContact(Contact c) {
		Object userData1 = c.getFixtureA().getBody().getUserData();
		Object userData2 = c.getFixtureB().getBody().getUserData();
		DynamicBody db1 = null;
		DynamicBody db2 = null;

		if (userData1 != null && userData1 instanceof DynamicBody) {
			db1 = (DynamicBody) userData1;
		}
		if (userData2 != null && userData2 instanceof DynamicBody) {
			db2 = (DynamicBody) userData2;
		}

		if (db1 != null && db2 != null) {
			Fixture f1 = c.getFixtureA();
			Fixture f2 = c.getFixtureB();
			Collection<Hit> c1 = db1.getHitConstants(f1);
			Collection<Hit> c2 = db2.getHitConstants(f2);
			if (c2 != null) {
				db1.endContact(f1, c2);
			} else {
				db1.endContact(f1, new ArrayList<Hit>());
			}

			if (c1 != null) {
				db2.endContact(f2, c1);
			} else {
				db2.endContact(f2, new ArrayList<Hit>());
			}
		} else if (db1 != null && db2 == null) {
			db1.endContact(c.getFixtureA(), new ArrayList<Hit>());
		} else if (db2 != null && db1 == null) {
			db2.endContact(c.getFixtureB(), new ArrayList<Hit>());
		}
	}

	@Override
	public void postSolve(Contact c, ContactImpulse ci) {

	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {

	}

}
