package com.googlecode.tda367.denty.core.level;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.jbox2d.dynamics.World;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.googlecode.tda367.denty.constants.ResolutionConstants;
import com.googlecode.tda367.denty.core.camera.Camera;
import com.googlecode.tda367.denty.core.camera.MovingCamera;
import com.googlecode.tda367.denty.core.dynamicbody.Block;
import com.googlecode.tda367.denty.core.dynamicbody.BouncingEnemy;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.dynamicbody.Key;
import com.googlecode.tda367.denty.core.dynamicbody.Lock;
import com.googlecode.tda367.denty.core.dynamicbody.SimpleEnemy;
import com.googlecode.tda367.denty.core.dynamicbody.ThrowableBlock;

/**
 * First and introductory level for familiarization with the game features.
 * 
 */
public class TutorialLevel extends AbstractLevel {

	// Keeps track of the viewport
	private Camera camera;
	private TiledMap map;

	public TutorialLevel() {
		try {
			this.map = new TiledMap(this.getTiledMapPath());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.parseTiledMap(this.map);

		this.addBodies();

		Dimension dimension = this.getDimension();
		this.camera = new MovingCamera(dimension.width, dimension.height,
				ResolutionConstants.WIDTH, ResolutionConstants.HEIGHT);
	}

	private void addBodies() {
		List<DynamicBody> bodies = new ArrayList<DynamicBody>();
		World world = this.getWorld();

		// Enemies
		SimpleEnemy enemy = new SimpleEnemy(50f, 22f, world);
		enemy.setXBounds(17.5f, 50f);
		bodies.add(enemy);
		enemy = new SimpleEnemy(62, 31, world);
		enemy.setXBounds(62f, 81f);
		bodies.add(enemy);
		bodies.add(new BouncingEnemy(31f, 35, world));
		bodies.add(new BouncingEnemy(61.5f, 36, world));

		// Blocks
		bodies.add(new Block(16, 17, world));
		bodies.add(new Block(17, 17, world));
		bodies.add(new Block(18, 17, world));
		bodies.add(new Block(19, 17, world));
		bodies.add(new Block(83, 50, world));
		bodies.add(new Block(83, 49, world));
		bodies.add(new Block(83, 48, world));
		bodies.add(new Block(83, 47, world));

		// Throwable blocks
		bodies.add(new ThrowableBlock(6, 13, world));
		bodies.add(new ThrowableBlock(7, 13, world));
		bodies.add(new ThrowableBlock(6.5f, 12, world));
		bodies.add(new ThrowableBlock(64, 39, world));
		bodies.add(new ThrowableBlock(65, 39, world));
		bodies.add(new ThrowableBlock(64.5f, 38, world));

		// Lock and key
		bodies.add(new Lock(82, 48.5f, world));
		bodies.add(new Key(73.5f, 22, world));

		this.addDynamicBodies(bodies);
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(this.map.getWidth(), this.map.getHeight());
	}

	@Override
	public Camera getCamera() {
		return this.camera;
	}

	@Override
	public String getTiledMapPath() {
		return "res/lvl/tutorial-level.tmx";
	}

	@Override
	public Point getDentyStartPosition() {
		return new Point(5, 22);
	}

	@Override
	public Point getGoalPosition() {
		return new Point(85, 20);
	}

	@Override
	public int getMaxAvailableBlocks() {
		return 20;
	}

	@Override
	public int getBlocksAvailableFromStart() {
		return 10;
	}

	@Override
	public String getName() {
		return "Level 1 - The journey begins!";
	}
}