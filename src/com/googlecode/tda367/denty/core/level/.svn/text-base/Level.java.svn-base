package com.googlecode.tda367.denty.core.level;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import com.googlecode.tda367.denty.core.camera.Camera;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.dynamicbody.MoveableBody;

/**
 * Interface for the level, interfacing with DentyModel and the view classes.
 * 
 */
public interface Level {
	public void placeBlock(int x, int y);

	public void throwNewBlock(float x, float y, Vec2 force);

	public void releaseBodies(float x1, float y1, float x2, float y2);

	public void update();

	public void restart();

	public boolean isAreaFree(float x1, float y1, float x2, float y2);

	public boolean canReleaseBody(float x1, float y1, float x2, float y2);

	public boolean canAddBlock();

	public String getName();

	public Dimension getDimension();

	public Point getDentyStartPosition();

	public Point getGoalPosition();

	public int getMaxAvailableBlocks();

	public int getBlocksAvailableFromStart();

	public String getTiledMapPath();

	public Camera getCamera();

	public World getWorld();

	public MoveableBody getDenty();

	public List<DynamicBody> getDynamicBodies();
}
