package com.googlecode.tda367.denty.core.level;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.googlecode.tda367.denty.constants.ResolutionConstants;
import com.googlecode.tda367.denty.core.camera.AutoscrollingCamera;
import com.googlecode.tda367.denty.core.camera.Camera;
import com.googlecode.tda367.denty.core.dynamicbody.ShootingEnemy;

/**
 * A skill demanding level full flying blocks, high stairs and a killer camera
 * 
 */
public class FastLevel extends AbstractLevel {

	// Keeps track of the viewport
	private Camera camera;
	private TiledMap map;

	public FastLevel() {
		try {
			this.map = new TiledMap(this.getTiledMapPath());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.parseTiledMap(this.map);

		Dimension dimension = this.getDimension();
		this.camera = new AutoscrollingCamera(dimension.width,
				dimension.height, ResolutionConstants.WIDTH,
				ResolutionConstants.HEIGHT);

		this.addEnemies();
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
		return "res/lvl/denty_fastlevel2.tmx";
	}

	@Override
	public Point getDentyStartPosition() {
		return new Point(15, 45);
	}

	@Override
	public Point getGoalPosition() {
		return new Point(295, 45);
	}

	@Override
	public int getMaxAvailableBlocks() {
		return 50;
	}

	@Override
	public int getBlocksAvailableFromStart() {
		return 5;
	}

	@Override
	public void restart() {
		super.restart();
		camera.restart();
		this.addEnemies();
	}

	private void addEnemies() {
		ArrayList<ShootingEnemy> enemyList = new ArrayList<ShootingEnemy>();
		// check for ShootingEnemies left from last try
		if (this.isAreaFree(54f, 47f, 54.5f, 47.5f))
			enemyList.add(new ShootingEnemy(54f, 47f, this.getWorld()));
		if (this.isAreaFree(85f, 47f, 86f, 47.5f))
			enemyList.add(new ShootingEnemy(85f, 47f, this.getWorld()));
		if (this.isAreaFree(118f, 47f, 119f, 47.5f))
			enemyList.add(new ShootingEnemy(118f, 47f, this.getWorld()));
		if (this.isAreaFree(151f, 47f, 152f, 47.5f))
			enemyList.add(new ShootingEnemy(151f, 47f, this.getWorld()));
		if (this.isAreaFree(284f, 3f, 285f, 3.5f))
			enemyList.add(new ShootingEnemy(284f, 3f, this.getWorld()));
		if (this.isAreaFree(286f, 47f, 287f, 47.5f))
			enemyList.add(new ShootingEnemy(286f, 47f, this.getWorld()));

		for (ShootingEnemy se : enemyList) {
			this.addDynamicBody(se);
		}
	}

	@Override
	public String getName() {
		return "Level 2 - The journey continues... FAST!";
	}
}