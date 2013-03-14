package com.googlecode.tda367.denty.core.camera;

import java.awt.Dimension;

import org.jbox2d.common.Vec2;

/**
 * Camera class that moves with constant speed, regardless of the player's
 * x-position.
 * 
 */
public class AutoscrollingCamera implements Camera {

	public AutoscrollingCamera(float levelWidth, float levelHeight,
			float xOffset, float yOffset) {
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.x = 0;
		this.y = 0;
	}

	private float x, y, xOffset, yOffset, levelWidth, levelHeight;

	@Override
	public float getCameraX() {
		return x;
	}

	@Override
	public float getCameraXOffset() {
		return xOffset;
	}

	@Override
	public float getCameraY() {
		return y;
	}

	@Override
	public float getCameraYOffset() {
		return yOffset;
	}

	@Override
	public void updateCamera(Vec2 position) {
		x += 1 / 16f;
		if (x > levelWidth - xOffset)
			x = levelWidth - xOffset;
		y = position.y - yOffset / 2;
		if (y < 0)
			y = 0;
		else if (y > levelHeight - yOffset)
			y = levelHeight - yOffset;
	}

	@Override
	public void restart() {
		x = 0;
		y = 0;
	}

	@Override
	public Vec2 getPosition() {
		return new Vec2(x, y);
	}

	@Override
	public boolean isOnCamera(Vec2 dbPos, Dimension dimension) {
		return (dbPos.x + dimension.getWidth() / 2 > x
				&& dbPos.x - dimension.getWidth() / 2 < x + xOffset
				&& dbPos.y + dimension.getHeight() / 2 > y && dbPos.y
				- dimension.getHeight() / 2 < y + yOffset);
	}

}
