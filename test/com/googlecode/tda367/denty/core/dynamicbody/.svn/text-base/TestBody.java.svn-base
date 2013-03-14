package com.googlecode.tda367.denty.core.dynamicbody;

import java.awt.Dimension;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;

import com.googlecode.tda367.denty.constants.DynamicConstant;
import com.googlecode.tda367.denty.constants.Hit;
import com.googlecode.tda367.denty.constants.StateConstant;

/**
 * Pseudo-MoveableBody with open variables purely designed for testing constants
 * handling.
 * 
 */
public class TestBody implements DynamicBody, MoveableBody {
	private float x;
	private float y;
	public boolean isDestroyed = false;

	private boolean alive;

	Map<DynamicConstant, Float> dynamics;
	Map<StateConstant, Boolean> states;

	public TestBody() {
		this.alive = true;
		this.defineConstants();
	}

	private void defineConstants() {
		dynamics = new HashMap<DynamicConstant, Float>();
		states = new HashMap<StateConstant, Boolean>();

	}

	@Override
	public Vec2 getPosition() {
		return new Vec2(x, y);
	}

	@Override
	public Collection<Hit> getHitConstants(Fixture fixture) {
		return null;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void destroy() {
		this.isDestroyed = true;
	}

	@Override
	public boolean isAlive() {
		return this.alive;
	}

	@Override
	public String getImagePath() {
		return null;
	}

	@Override
	public float getRotation() {
		return 0;
	}

	@Override
	public void beginContact(Fixture hitFixture, Collection<Hit> hitConstants) {
	}

	@Override
	public Vec2 getLinearVelocity() {

		return new Vec2(0, 0);
	}

	@Override
	public void applyForce(Vec2 force) {

	}

	@Override
	public void applyVelocity(Vec2 velocity) {

	}

	@Override
	public float getConstant(DynamicConstant dc) {
		float f = 0;
		if (this.dynamics.containsKey(dc))
			f = this.dynamics.get(dc);
		return f;
	}

	public void addDynamicConstant(DynamicConstant key, float value) {
		if (key != null) {
			this.dynamics.put(key, value);
		}
	}

	public void removeDynamicConstant(DynamicConstant key) {
		if (key != null) {
			this.dynamics.remove(key);
		}
	}

	@Override
	public boolean getState(StateConstant sc) {
		boolean state = false;
		if (this.states.containsKey(sc))
			state = this.states.get(sc);
		return state;
	}

	@Override
	public void setState(StateConstant sc, boolean b) {
		if (this.states.containsKey(sc)) {
			if (sc.equals(StateConstant.MOVING_LEFT))
				this.states.put(StateConstant.MOVING_RIGHT, false);
			if (sc.equals(StateConstant.MOVING_RIGHT))
				this.states.put(StateConstant.MOVING_LEFT, false);

			this.states.put(sc, true);
		}
	}

	public void addAllowedState(StateConstant sc) {
		if (sc != null) {
			this.states.put(sc, false);
		}
	}

	public void removeAllowedState(StateConstant sc) {
		if (sc != null) {
			this.states.remove(sc);
		}
	}

	@Override
	public void endContact(Fixture hitFixture, Collection<Hit> hitConstants) {

	}

	@Override
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(0, 0);
	}

}
