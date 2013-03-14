package com.googlecode.tda367.denty.core.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.tiled.TiledMap;

import com.googlecode.tda367.denty.core.data.GameData;
import com.googlecode.tda367.denty.core.dynamicbody.Block;
import com.googlecode.tda367.denty.core.dynamicbody.CollisionListener;
import com.googlecode.tda367.denty.core.dynamicbody.Denty;
import com.googlecode.tda367.denty.core.dynamicbody.DBQueryCallback;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.dynamicbody.Goal;
import com.googlecode.tda367.denty.core.dynamicbody.MoveableBody;
import com.googlecode.tda367.denty.core.dynamicbody.ThrowableBlock;
import com.googlecode.tda367.denty.util.FilteringUtil;
import com.googlecode.tda367.denty.util.PhysicalBodiesUtil;

/**
 * Provides all common logic for the levels.
 * 
 */
public abstract class AbstractLevel implements Level {

	private World world;
	private Denty denty;
	private List<DynamicBody> bodyList;
	private GameData gameData;

	public AbstractLevel() {
		this.gameData = GameData.getInstance();
		this.initPhysics();
		this.bodyList = new ArrayList<DynamicBody>();
		this.addGoal();
		this.addDenty();
		gameData.setBlocks(this.getBlocksAvailableFromStart());
		gameData.setMaxBlocks(this.getMaxAvailableBlocks());
	}

	private void addDenty() {
		Point dentyPos = this.getDentyStartPosition();
		this.denty = new Denty(dentyPos.x, dentyPos.y, this.world);
		this.addDynamicBody(this.denty);
	}

	private void addGoal() {
		Point goalPos = this.getGoalPosition();
		Goal goal = new Goal(goalPos.x, goalPos.y, this.world);
		this.addDynamicBody(goal);
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	@Override
	public MoveableBody getDenty() {
		return this.denty;
	}

	protected void initPhysics() {
		this.world = new World(new Vec2(0, 50f), true);
		this.world.setContactListener(CollisionListener.getInstance());
	}

	// Testing not possible because of TiledMap.
	public void parseTiledMap(TiledMap map) {
		BodyDef staticBodyDef = PhysicalBodiesUtil.createBodyDef(
				new Vec2(0, 0), BodyType.STATIC, null, true, 0);
		Body tileBody = this.world.createBody(staticBodyDef);
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				if (map.getTileId(x, y, 1) != 0) {
					PolygonShape ps = new PolygonShape();
					ps.setAsBox(0.5f, 0.5f, new Vec2(x + 0.5f, y + 0.5f), 0);
					tileBody.createFixture(ps, 0);
				}
			}
		}
	}

	@Override
	public List<DynamicBody> getDynamicBodies() {
		return this.bodyList;
	}

	public void addDynamicBody(DynamicBody body) {
		bodyList.add(body);
	}

	public void addDynamicBodies(Collection<DynamicBody> bodies) {
		bodyList.addAll(bodies);
	}

	@Override
	public void placeBlock(int x, int y) {
		if (canAddBlock()) {
			DynamicBody block = new Block(x, y, this.world);
			this.bodyList.add(block);
			this.decNoOfAvailableBlocks();
		}
	}

	private void decNoOfAvailableBlocks() {
		gameData.setBlocks(gameData.getBlocks() - 1);
	}

	@Override
	public void restart() {
		this.addDenty();
		GameData gameData = GameData.getInstance();
		gameData.setLives(gameData.getLives() - 1);
		if (gameData.getLives() < 0) {
			gameData.fireGameReset();
		}
	}

	@Override
	public boolean canReleaseBody(float x1, float y1, float x2, float y2) {
		DBQueryCallback callback = this.queryCallback(x1, y1, x2, y2);
		return callback.areaContains(Block.class);
	}

	@Override
	public void releaseBodies(float x1, float y1, float x2, float y2) {
		DBQueryCallback callback = this.queryCallback(x1, y1, x2, y2);
		List<Block> list = FilteringUtil.getListOf(callback.getDynamicBodies(),
				Block.class);
		for (Block block : list) {
			Vec2 pos = new Vec2(block.getPosition());
			block.setAlive(false);
			this.newThrowableBlock(pos.x, pos.y);
		}

	}

	@Override
	public void update() {
		if (denty.getPosition().x < this.getCamera().getCameraX()) {
			denty.setAlive(false);
		}
		if (denty.getPosition().x > this.getCamera().getCameraX()
				+ this.getCamera().getCameraXOffset()) {
			this.denty.setAlive(false);
		}
		this.world.step(1.0f / 60.0f, 10, 10);
	}

	@Override
	public void throwNewBlock(float x, float y, Vec2 force) {
		if (canAddBlock()) {
			MoveableBody block = newThrowableBlock(x, y);
			block.applyForce(force);
			this.decNoOfAvailableBlocks();
		}
	}

	protected ThrowableBlock newThrowableBlock(float x, float y) {
		ThrowableBlock block = new ThrowableBlock(x, y, this.world);
		this.bodyList.add(block);
		return block;
	}

	private DBQueryCallback queryCallback(float x1, float y1, float x2, float y2) {
		AABB box = new AABB(new Vec2(x1, y1), new Vec2(x2, y2));
		DBQueryCallback callback = new DBQueryCallback();
		world.queryAABB(callback, box);
		return callback;
	}

	@Override
	public boolean isAreaFree(float x1, float y1, float x2, float y2) {
		DBQueryCallback callback = this.queryCallback(x1, y1, x2, y2);
		return callback.isAreaFree();
	}

	@Override
	public boolean canAddBlock() {
		return gameData.getBlocks() > 0;
	}
}
