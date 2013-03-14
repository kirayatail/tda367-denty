package com.googlecode.tda367.denty.core.level;

import java.awt.Dimension;
import java.awt.Point;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.googlecode.tda367.denty.constants.ResolutionConstants;
import com.googlecode.tda367.denty.core.camera.Camera;
import com.googlecode.tda367.denty.core.camera.MovingCamera;
import com.googlecode.tda367.denty.core.dynamicbody.Block;
import com.googlecode.tda367.denty.core.dynamicbody.SimpleEnemy;

public class ClimbLevel extends AbstractLevel implements Level {
	
	private Camera camera;
	private TiledMap map;
	private int counter;
	
	public ClimbLevel(){
		try {
			this.map = new TiledMap(this.getTiledMapPath());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.parseTiledMap(this.map);

		Dimension dimension = this.getDimension();
		this.camera = new MovingCamera(dimension.width, dimension.height,
				ResolutionConstants.WIDTH, ResolutionConstants.HEIGHT);
		
		this.addBlocks(22, 275, 2);
		this.addBlocks(22, 276, 2);
		this.addBlocks(22, 251, 2);
		this.placeHiddenBlocks();
		
		this.counter = 0;
	}
	
	private void placeHiddenBlocks() {
		float X = 31f;
		float Y = 296f;
		
		for(int i=0; i<2; i++){
			for(int j=0; j<3; j++){
				this.addDynamicBody(new Block(X+i, Y+j, this.getWorld()));
			}
		}
		
	}
	
	private void addEnemy(float x, float y) {
		SimpleEnemy enemy = new SimpleEnemy(x, y, this.getWorld());
		enemy.setXBounds(-1, 68);
		this.addDynamicBody(enemy);
		
	}
	
	private void addBlocks(float x, float y, int n) {
		for (int i = 0; i < n; i++) {
			this.newThrowableBlock(i + x + 0.5f, y + 0.5f);
		}
	}

	@Override
	public String getName() {
		return "Level 3 - The journey goes on, upwards!";
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(this.map.getWidth(), this.map.getHeight());
	}

	@Override
	public Point getDentyStartPosition() {
		return new Point(4,297);
	}

	@Override
	public Point getGoalPosition() {
		return new Point(51,232);
	}

	@Override
	public int getMaxAvailableBlocks() {
		return 20;
	}

	@Override
	public int getBlocksAvailableFromStart() {
		return 5;
	}

	@Override
	public String getTiledMapPath() {
		return "res/lvl/denty_climblevel1.tmx";
	}

	@Override
	public Camera getCamera() {
		return this.camera;
	}

	@Override
	public void update() {
		if((this.counter++ % 150) == 0)
			this.addEnemy(21f, 193f);
		
		super.update();
	}
	
	

}
