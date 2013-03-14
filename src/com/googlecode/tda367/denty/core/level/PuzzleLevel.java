package com.googlecode.tda367.denty.core.level;

import java.awt.Dimension;
import java.awt.Point;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.googlecode.tda367.denty.constants.ResolutionConstants;
import com.googlecode.tda367.denty.core.camera.Camera;
import com.googlecode.tda367.denty.core.camera.MovingCamera;
import com.googlecode.tda367.denty.core.dynamicbody.Key;
import com.googlecode.tda367.denty.core.dynamicbody.Lock;
import com.googlecode.tda367.denty.core.dynamicbody.SimpleEnemy;
import com.googlecode.tda367.denty.core.dynamicbody.SpikeyEnemy;

public class PuzzleLevel extends AbstractLevel {

	private Camera camera;
	private String tiledMapPath;
	private TiledMap map;

	/**
	 * A tricky level with hidden features and messages.
	 * 
	 */
	public PuzzleLevel() {
		this.tiledMapPath = "res/lvl/denty_puzzlelevel1.tmx";
		try {
			this.map = new TiledMap(this.tiledMapPath);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.parseTiledMap(this.map);

		Dimension dimension = this.getDimension();
		this.camera = new MovingCamera(dimension.width, dimension.height,
				ResolutionConstants.WIDTH, ResolutionConstants.HEIGHT);

		// Place lock and key
		Key key = new Key(108f, 35f, this.getWorld());
		Lock lock = new Lock(120f, 47.5f, this.getWorld());

		this.addDynamicBody(key);
		this.addDynamicBody(lock);

		SimpleEnemy enemy1 = new SimpleEnemy(12f, 30f, this.getWorld());
		enemy1.setXBounds(11f, 22f);
		this.addDynamicBody(enemy1);
		SpikeyEnemy enemy2;
		for (int i = 1; i <= 5; i++) {
			enemy2 = new SpikeyEnemy(4 * i + 50f, 47, this.getWorld());
			enemy2.setXBounds(50f, 103f);
			this.addDynamicBody(enemy2);
		}

		addBlocks(1.5f, 41f, 3);
		addBlocks(2f, 40f, 2);

		addBlocks(8.5f, 7f, 3);
		addBlocks(9f, 6f, 2);

	}

	@Override
	public String getTiledMapPath() {
		return this.tiledMapPath;
	}

	private void addBlocks(float x, float y, int n) {
		for (int i = 0; i < n; i++) {
			this.newThrowableBlock(i + x + 0.5f, y + 0.5f);
		}
	}

	@Override
	public Camera getCamera() {
		return this.camera;
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(this.map.getWidth(), this.map.getHeight());
	}

	@Override
	public Point getDentyStartPosition() {
		return new Point(1, 47);
	}

	@Override
	public Point getGoalPosition() {
		return new Point(123, 46);
	}

	@Override
	public int getMaxAvailableBlocks() {

		return 30;
	}

	@Override
	public int getBlocksAvailableFromStart() {

		return 3;
	}

	@Override
	public String getName() {
		return "Level 4 - The journey ends.. can you complete it?";
	}
}
