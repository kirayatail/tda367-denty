package com.googlecode.tda367.denty.ctrl;

import junit.framework.Assert;

import org.jbox2d.common.Vec2;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import com.googlecode.tda367.denty.constants.StateConstant;
import com.googlecode.tda367.denty.core.dynamicbody.Denty;
import com.googlecode.tda367.denty.core.level.Level;
import com.googlecode.tda367.denty.core.level.TestLevel;

public class TestMoveController {
	private Level level;
	private Denty denty;
	private Vec2 startPosition;

	@Before
	public void init() throws SlickException {
		level = new TestLevel();
		denty = new Denty(50f, 5f, level.getWorld());
		startPosition = new Vec2();
		startPosition.set(denty.getPosition());
		Vec2 currentPosition = new Vec2(0, 0);
		while (!startPosition.equals(currentPosition)) {
			currentPosition.set(startPosition);
			level.update();
			startPosition.set(denty.getPosition());
		}
	}

	@Test
	public void testMoveLeftRight() {
		float startX = denty.getPosition().x;
		float nextX = startX;

		for (int i = 0; i < 30; i++) {
			MoveController.moveRight(denty);
			level.update();
		}
		Assert.assertFalse(denty.getLinearVelocity().equals(new Vec2(0, 0)));
		Assert.assertFalse(denty.getState(StateConstant.MOVING_LEFT));
		Assert.assertTrue(denty.getState(StateConstant.MOVING_RIGHT));

		nextX = denty.getPosition().x;

		Assert.assertTrue(nextX > startX);

		startX = nextX;
		for (int i = 0; i < 30; i++) {
			MoveController.moveLeft(denty);
			level.update();
		}
		nextX = denty.getPosition().x;
		Assert.assertTrue(nextX < startX);
	}

	@Test
	public void testJump() {
		float startY = denty.getPosition().y;
		float nextY = startY;

		MoveController.jump(denty);
		for (int i = 0; i < 5; i++)
			level.update();

		nextY = denty.getPosition().y;
		Assert.assertTrue(nextY < startY); // Higher than before

		startY = nextY;
		MoveController.jump(denty);
		for (int i = 0; i < 5; i++)
			level.update();

		nextY = denty.getPosition().y;
		Assert.assertFalse(nextY > startY); // Cannot jump while in the air
	}
}
