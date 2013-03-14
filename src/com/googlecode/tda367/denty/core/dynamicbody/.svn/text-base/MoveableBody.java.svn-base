package com.googlecode.tda367.denty.core.dynamicbody;

import org.jbox2d.common.Vec2;

import com.googlecode.tda367.denty.constants.DynamicConstant;
import com.googlecode.tda367.denty.constants.StateConstant;

/**
 * Interface for bodies that can be moved around by forces and velocities. Also
 * defines that various constants can be fetched.
 * 
 */
public interface MoveableBody extends DynamicBody {
	public Vec2 getLinearVelocity();

	public void applyForce(Vec2 force);

	public void applyVelocity(Vec2 velocity);

	public float getConstant(DynamicConstant dc);

	public boolean getState(StateConstant sc);

	public void setState(StateConstant sc, boolean b);
}
