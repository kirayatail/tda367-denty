package com.googlecode.tda367.denty.core.dynamicbody;

import static com.googlecode.tda367.denty.constants.StateConstant.MOVING_LEFT;
import static com.googlecode.tda367.denty.constants.StateConstant.MOVING_RIGHT;

import java.awt.Dimension;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

import com.googlecode.tda367.denty.constants.DynamicConstant;
import com.googlecode.tda367.denty.constants.Hit;
import com.googlecode.tda367.denty.constants.StateConstant;

/**
 * Contains all logic that concerns the physical representation, as well as data
 * related to constants. This generic body is all that is common between the
 * MoveableBodys.
 * 
 */
public class InnerBody implements MoveableBody {
	private Body body;
	private Map<DynamicConstant, Float> dynConst;
	private Map<StateConstant, Boolean> allowedStates;
	private int hp;
	private final int maxHP;

	public InnerBody(Body body, int hp) {
		this.body = body;
		this.maxHP = hp > 0 ? hp : getStandardHP();
		this.dynConst = new HashMap<DynamicConstant, Float>();
		this.allowedStates = new HashMap<StateConstant, Boolean>();
		this.resetHP();
	}

	public InnerBody(Body body) {
		this(body, getStandardHP());
	}

	private static int getStandardHP() {
		return 100;
	}

	public void resetHP() {
		this.hp = maxHP;
	}

	public void changeHP(int difference) {
		this.hp += difference;
		if (this.hp > this.maxHP) {
			this.hp = maxHP;
		}
	}

	public int getHP() {
		return this.hp;
	}

	@Override
	public Vec2 getPosition() {
		return new Vec2(this.body.getPosition());
	}

	@Override
	public float getRotation() {
		return this.body.getAngle();
	}

	@Override
	public void beginContact(Fixture hitFixture, Collection<Hit> hitConstants) {
	}

	@Override
	public Collection<Hit> getHitConstants(Fixture fixture) {
		return null;
	}

	@Override
	public void destroy() {
		this.body.getWorld().destroyBody(this.body);
	}

	@Override
	public boolean isAlive() {
		return this.hp > 0;
	}

	public void setAlive(boolean value) {
		if (value && !isAlive()) {
			resetHP();
		} else if (!value && isAlive()) {
			this.hp = 0;
		}
	}

	@Override
	public String getImagePath() {
		return null;
	}

	@Override
	public Vec2 getLinearVelocity() {
		return new Vec2(this.body.getLinearVelocity());
	}

	@Override
	public void applyForce(Vec2 force) {
		this.body.applyForce(force, this.body.getLocalCenter());
	}

	@Override
	public void applyVelocity(Vec2 velocity) {
		this.body.setLinearVelocity(velocity);
	}

	@Override
	public float getConstant(DynamicConstant dc) {
		return this.dynConst.containsKey(dc) ? this.dynConst.get(dc) : 0;
	}

	public void addDynamicConstant(DynamicConstant key, float value) {
		if (key != null) {
			this.dynConst.put(key, value);
		}
	}

	public void removeDynamicConstant(DynamicConstant key) {
		if (key != null) {
			this.dynConst.remove(key);
		}
	}

	public void setXBounds(float west, float east) {
		if (east < west) {
			float temp = west;
			west = east;
			east = temp;
		}

		this.addDynamicConstant(DynamicConstant.BOUNDS_WEST, west);
		this.addDynamicConstant(DynamicConstant.BOUNDS_EAST, east);
	}

	@Override
	public boolean getState(StateConstant sc) {
		return sc != null && this.allowedStates.containsKey(sc)
				&& this.allowedStates.get(sc);
	}

	@Override
	public void setState(StateConstant sc, boolean b) {
		if (sc != null && this.allowedStates.containsKey(sc)) {
			this.allowedStates.put(sc, b);
			if (b) {
				if (sc == MOVING_LEFT
						&& allowedStates.containsKey(MOVING_RIGHT)) {
					this.allowedStates.put(MOVING_RIGHT, false);
				} else if (sc == MOVING_RIGHT
						&& allowedStates.containsKey(MOVING_LEFT)) {
					this.allowedStates.put(MOVING_LEFT, false);
				}
			}
		}
	}

	public void addAllowedState(StateConstant sc) {
		if (sc != null) {
			this.allowedStates.put(sc, false);
		}
	}

	public void removeAllowedState(StateConstant sc) {
		if (sc != null) {
			this.allowedStates.remove(sc);
		}
	}

	@Override
	public void endContact(Fixture hitFixture, Collection<Hit> hitConstants) {
		// does nothing

	}

	@Override
	public Dimension getDimension() {
		return new Dimension(0, 0);
	}

	public void setRotation(float rad) {
		// body.
	}

}
