package com.googlecode.tda367.denty.core.dynamicbody;

import java.awt.Dimension;
import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;

import com.googlecode.tda367.denty.constants.Hit;

/**
 * Interface for any object within the game with physical representation that
 * can be manipulated during gameplay.
 * 
 * Implementers are also responsible for providing a filepath to their image,
 * destroy their physical representation and deliver hit constants relevant to
 * each collision.
 * 
 */
public interface DynamicBody {
	public Vec2 getPosition();

	public float getRotation();

	public void beginContact(Fixture hitFixture, Collection<Hit> hitConstants);

	public void endContact(Fixture hitFixture, Collection<Hit> hitConstants);

	public Collection<Hit> getHitConstants(Fixture fixture);

	public void destroy();

	public boolean isAlive();

	public void setAlive(boolean alive);

	public String getImagePath();

	public Dimension getDimension();
}
