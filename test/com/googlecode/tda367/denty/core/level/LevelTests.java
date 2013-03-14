package com.googlecode.tda367.denty.core.level;

import java.util.List;

import junit.framework.Assert;

import org.jbox2d.common.Vec2;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import com.googlecode.tda367.denty.core.data.GameData;
import com.googlecode.tda367.denty.core.dynamicbody.Block;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.dynamicbody.MoveableBody;
import com.googlecode.tda367.denty.core.dynamicbody.ThrowableBlock;

public class LevelTests {
	private TestLevel level;

	@Before
	public void setUp() throws SlickException {
		this.level = new TestLevel();
	}

	@Test
	public void dbListTests() throws SlickException {
		Block block = new Block(5, 5, level.getWorld());
		level.addDynamicBody(block);
		List<DynamicBody> dbList = level.getDynamicBodies();
		Assert.assertTrue("DB not added to list", dbList.contains(block));
		block.setAlive(false);
		level.update();
		Assert.assertFalse("DB not dead when supposed to.", block.isAlive());
	}

	@Test
	public void placeThrowBlocksTests() throws SlickException {
		Assert.assertTrue("Cannot place or throw block when supposed to",
				level.canAddBlock());
		level.placeBlock(0, 0);
		Assert.assertFalse("Position not occupied",
				this.level.isAreaFree(0, 0, 1, 1));

		int blocksToThrow = GameData.getInstance().getBlocks();
		for (int i = 0; i < blocksToThrow; i++) {
			level.throwNewBlock(i, 90, new Vec2(100, 100));
			Assert.assertFalse("Position not occupied",
					this.level.isAreaFree(i, 90, i + 1, 91));
		}
		Assert.assertFalse("Can throw block when not supposed to",
				level.canAddBlock());
	}

	@Test
	public void removalOfTBTest() throws SlickException {
		level.throwNewBlock(level.getDenty().getPosition().x + 1, level
				.getDenty().getPosition().y + 1.3f, new Vec2(0, 0));
		MoveableBody denty = (level.getDenty());
		denty.applyVelocity(new Vec2(20, 0));
		for (int i = 0; i < 10; i++) {
			level.update();
		}

		List<DynamicBody> dbList = level.getDynamicBodies();
		for (DynamicBody body : dbList) {
			if (body instanceof ThrowableBlock) {
				Assert.assertFalse("TB not set to dead colliding with Denty",
						body.isAlive());
			}
		}
	}

	@Test
	public void testReleasingBlocks() {
		level.placeBlock(7, 7);
		if (level.canReleaseBody(6, 6, 7, 7)) {
			level.releaseBodies(6, 6, 7, 7);
		}
		boolean containsTB = false;
		for (DynamicBody body : level.getDynamicBodies()) {
			if (body instanceof ThrowableBlock) {
				containsTB = true;
			}
		}
		Assert.assertTrue("No TB created when releasing", containsTB);

	}

	@Test
	public void restartLevelTest() {
		DynamicBody oldDenty = this.level.getDenty();
		this.level.update();
		level.restart();
		DynamicBody newDenty = this.level.getDenty();
		Assert.assertTrue("Not a new instance of denty", oldDenty != newDenty);
		Assert.assertTrue("Camera not reset.", level.getCamera().getPosition()
				.equals(new Vec2(0, 0)));

	}
}
