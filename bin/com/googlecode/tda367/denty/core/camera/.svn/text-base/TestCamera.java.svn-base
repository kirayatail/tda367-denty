package com.googlecode.tda367.denty.core.camera;

import java.awt.Dimension;

import junit.framework.Assert;

import org.jbox2d.common.Vec2;
import org.junit.Before;
import org.junit.Test;

public class TestCamera {
	private Camera cameraAuto, cameraMoving;
	private float xOffset, yOffset;

	@Before
	public void setUp() throws Exception {
		xOffset = 20;
		yOffset = 15;
		cameraAuto = new AutoscrollingCamera(5000, 2000, xOffset, yOffset);
		cameraMoving = new MovingCamera(5000, 2000, xOffset, yOffset);
	}

	@Test
	public void testPosition() {
		Vec2 aPosition = cameraAuto.getPosition();
		Vec2 mPosition = cameraMoving.getPosition();

		Assert.assertTrue(aPosition.x == 0);
		Assert.assertTrue(aPosition.y == 0);
		Assert.assertTrue(mPosition.x == 0);
		Assert.assertTrue(mPosition.y == 0);
		Assert.assertTrue(aPosition.x == cameraAuto.getCameraX());
		Assert.assertTrue(aPosition.y == cameraAuto.getCameraY());
		Assert.assertTrue(mPosition.x == cameraMoving.getCameraX());
		Assert.assertTrue(mPosition.y == cameraMoving.getCameraY());
	}

	@Test
	public void testOffset() {
		Assert.assertTrue(cameraAuto.getCameraXOffset() == xOffset);
		Assert.assertTrue(cameraAuto.getCameraYOffset() == yOffset);
		Assert.assertTrue(cameraMoving.getCameraXOffset() == xOffset);
		Assert.assertTrue(cameraMoving.getCameraYOffset() == yOffset);
	}

	@Test
	public void testUpdate() {
		Vec2 newPosition = new Vec2(35, 100);
		cameraAuto.updateCamera(newPosition);
		cameraMoving.updateCamera(newPosition);
		Assert.assertTrue(cameraAuto.getCameraX() == 1 / 16f);
		Assert.assertTrue(cameraAuto.getCameraY() == 92.5f);
		Assert.assertTrue(cameraMoving.getCameraX() == 25);
		Assert.assertTrue(cameraMoving.getCameraY() == 92.5f);
		Assert.assertFalse(cameraAuto.getCameraX() == 0);
		Assert.assertFalse(cameraAuto.getCameraY() == 0);
		Assert.assertFalse(cameraMoving.getCameraX() == 0);
		Assert.assertFalse(cameraMoving.getCameraY() == 0);
	}

	@Test
	public void testRestart() {
		cameraAuto.restart();
		cameraMoving.restart();
		Assert.assertTrue(cameraAuto.getCameraX() == 0);
		Assert.assertTrue(cameraAuto.getCameraY() == 0);
		Assert.assertTrue(cameraMoving.getCameraX() == 0);
		Assert.assertTrue(cameraMoving.getCameraY() == 0);
	}

	@Test
	public void testIsOnCamera() {
		Assert.assertTrue(cameraAuto.isOnCamera(new Vec2(5, 5), new Dimension(
				1, 1)));
		Assert.assertTrue(cameraMoving.isOnCamera(new Vec2(5, 5),
				new Dimension(1, 1)));
		Assert.assertFalse(cameraAuto.isOnCamera(new Vec2(100, 100),
				new Dimension(1, 1)));
		Assert.assertFalse(cameraMoving.isOnCamera(new Vec2(100, 100),
				new Dimension(1, 1)));
	}
}
