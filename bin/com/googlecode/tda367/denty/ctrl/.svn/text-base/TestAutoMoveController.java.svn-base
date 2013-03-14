package com.googlecode.tda367.denty.ctrl;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.*;

import com.googlecode.tda367.denty.constants.StateConstant;
import com.googlecode.tda367.denty.core.dynamicbody.BouncingEnemy;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.dynamicbody.SimpleEnemy;
import com.googlecode.tda367.denty.core.dynamicbody.SpikeyEnemy;
import com.googlecode.tda367.denty.core.level.Level;
import com.googlecode.tda367.denty.core.level.TestLevel;

public class TestAutoMoveController {
	private Level level;
	private World world;
	private BouncingEnemy bouncingEnemy;
	private SimpleEnemy simpleEnemy;
	private SpikeyEnemy spikeyEnemy;

	@Before
	public void init() {
		this.level = new TestLevel();
		this.world = level.getWorld();
		this.bouncingEnemy = new BouncingEnemy(60, 5, world);
		this.simpleEnemy = new SimpleEnemy(51, 5, world);
		this.simpleEnemy.setXBounds(52, 58);
		this.simpleEnemy.setState(StateConstant.MOVING_LEFT, true);
		this.spikeyEnemy = new SpikeyEnemy(65, 5, world);
	}

	@Test
	public void testModifyStates() {
		ArrayList<DynamicBody> testList = new ArrayList<DynamicBody>();
		Vec2 dummyPos = new Vec2(0, 0);
		testList.add(simpleEnemy);
		AutoMoveController.update(testList, dummyPos);
		Assert.assertTrue(simpleEnemy.getState(StateConstant.MOVING_RIGHT));
		Assert.assertFalse(simpleEnemy.getState(StateConstant.MOVING_LEFT));
		simpleEnemy.destroy();
		testList.clear();

		simpleEnemy = new SimpleEnemy(59, 5, world);
		simpleEnemy.setXBounds(52, 58);
		simpleEnemy.setState(StateConstant.MOVING_RIGHT, true);
		testList.add(simpleEnemy);
		AutoMoveController.update(testList, dummyPos);
		Assert.assertTrue(simpleEnemy.getState(StateConstant.MOVING_LEFT));
		Assert.assertFalse(simpleEnemy.getState(StateConstant.MOVING_RIGHT));
		simpleEnemy.destroy();
		testList.clear();
		simpleEnemy = new SimpleEnemy(55, 5, world);

		spikeyEnemy.setState(StateConstant.MOVING_LEFT, true);
		spikeyEnemy.setState(StateConstant.CONTACT_LEFT, true);
		testList.add(spikeyEnemy);
		AutoMoveController.update(testList, dummyPos);
		Assert.assertTrue(spikeyEnemy.getState(StateConstant.MOVING_RIGHT));
		Assert.assertFalse(spikeyEnemy.getState(StateConstant.MOVING_LEFT));
		testList.clear();

		spikeyEnemy.setState(StateConstant.MOVING_RIGHT, true);
		spikeyEnemy.setState(StateConstant.CONTACT_RIGHT, true);
		testList.add(spikeyEnemy);
		AutoMoveController.update(testList, dummyPos);
		Assert.assertTrue(spikeyEnemy.getState(StateConstant.MOVING_LEFT));
		Assert.assertFalse(spikeyEnemy.getState(StateConstant.MOVING_RIGHT));
		testList.clear();
	}

	@Test
	public void testPerformMoves() {
		ArrayList<DynamicBody> testList = new ArrayList<DynamicBody>();
		testList.add(bouncingEnemy);
		float startX = bouncingEnemy.getPosition().x;
		for (int i = 0; i < 100; i++) {
			AutoMoveController.update(testList, new Vec2(65, 5));
			level.update();
		}
		Assert.assertTrue(bouncingEnemy.getPosition().x > startX);
	}

	@Test
	public void testMoveLeftRight() {
		Vec2 dummyPos = new Vec2(0, 0);
		simpleEnemy.setState(StateConstant.MOVING_RIGHT, true);
		ArrayList<DynamicBody> testList = new ArrayList<DynamicBody>();
		testList.add(simpleEnemy);
		float startX = simpleEnemy.getPosition().x;
		AutoMoveController.update(testList, dummyPos);
		level.update();
		Assert.assertTrue(simpleEnemy.getPosition().x > startX);
	}
}
