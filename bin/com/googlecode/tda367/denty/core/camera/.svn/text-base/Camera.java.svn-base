package com.googlecode.tda367.denty.core.camera;

import java.awt.Dimension;

import org.jbox2d.common.Vec2;

/**
 * Interface for classes that dictate how much and what objects of a level the
 * view should render. Currently two classes implement this interface.
 * 
 */
public interface Camera {

	// Returns the position of the camera (upper left corner)
	public float getCameraX();

	public float getCameraY();

	public Vec2 getPosition();

	// Returns the offsets (width and height of the screen)
	public float getCameraXOffset();

	public float getCameraYOffset();

	public void updateCamera(Vec2 position);

	public void restart();

	boolean isOnCamera(Vec2 dbPos, Dimension dimension);
}
