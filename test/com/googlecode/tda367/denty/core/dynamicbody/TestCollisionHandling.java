package com.googlecode.tda367.denty.core.dynamicbody;

import java.util.List;

import junit.framework.Assert;

import org.jbox2d.dynamics.World;

import org.junit.*;

import com.googlecode.tda367.denty.core.data.GameData;
import com.googlecode.tda367.denty.core.level.TestLevel;
import com.googlecode.tda367.denty.ctrl.*;

public class TestCollisionHandling {

	private List<DynamicBody> bodyList;
	private TestLevel level;
	private World world;

	@Before
	public void init() {
		this.level = new TestLevel();
		this.world = level.getWorld();
		this.bodyList = level.getDynamicBodies();
		world.setContactListener(CollisionListener.getInstance());
	}

	@After
	public void cleanup() {
		for (DynamicBody db : bodyList) {
			db.setAlive(false);
		}
		RecycleController.update(bodyList, 100, 100);
	}

	@Test
	public void testDentyWalkIntoEnemy() {
		Denty denty = new Denty(10, 15, world);
		SimpleEnemy enemy = new SimpleEnemy(15, 15, world);
		level.addDynamicBody(denty);
		level.addDynamicBody(enemy);
		level.addDynamicBody(denty);
		int dentyStartHP = denty.getHP();
		int enemyStartHP = enemy.getHP();

		float enemyStartX = enemy.getPosition().x;
		while (enemy.getPosition().x == enemyStartX) { // Walk denty until the
														// enemy is touched.
			MoveController.moveRight(denty);
			level.update();
		}
		Assert.assertTrue(denty.getHP() < dentyStartHP);
		Assert.assertTrue(enemy.getHP() == enemyStartHP);
	}

	@Test
	public void testDentyJumpOnEnemy() {
		Denty denty = new Denty(10, 15, world);
		SimpleEnemy enemy = new SimpleEnemy(10, 19, world);
		level.addDynamicBody(denty);
		level.addDynamicBody(enemy);

		int dentyStartHP = denty.getHP();
		int enemyStartHP = enemy.getHP();

		while (denty.getLinearVelocity().y >= 0) { // when denty moves
													// "upwards"(towards y=0),
													// he has hit the enemy.
			level.update();
		}

		Assert.assertTrue(denty.getHP() == dentyStartHP);
		Assert.assertTrue(enemy.getHP() < enemyStartHP);

	}

	@Test
	public void testDentyPickupBlock() {
		Denty denty = new Denty(10, 19, world);
		ThrowableBlock block = new ThrowableBlock(15, 19, world);
		level.addDynamicBody(denty);
		level.addDynamicBody(block);
		int blocks = GameData.getInstance().getBlocks();

		while (denty.getPosition().x < 15) {
			MoveController.moveRight(denty);
			level.update();
		}

		Assert.assertFalse(block.isAlive()); // block not alive - has been
												// picked up
		Assert.assertTrue(GameData.getInstance().getBlocks() == (blocks + 1)); // check
																				// how
																				// many
																				// blocks
																				// are
																				// "picked up"
	}

	@Test
	public void testDentyJumpOnSpikey() {
		Denty denty = new Denty(10, 10, world);
		SpikeyEnemy spikey = new SpikeyEnemy(10, 19, world);
		level.addDynamicBody(denty);
		level.addDynamicBody(spikey);

		int dentyHP = denty.getHP();
		int spikeyHP = spikey.getHP();

		while (denty.getLinearVelocity().y >= 0)
			level.update();

		// Something weird with the positions of Denty and Spikey in this test,
		// they seem to fall out of the level.
		// Still works though...

		Assert.assertTrue(denty.getHP() < dentyHP);
		Assert.assertTrue(spikey.getHP() == spikeyHP);

	}

	@Test
	public void testBlockFallingOnDenty() {
		Denty denty = new Denty(10, 18, world);
		ThrowableBlock block = new ThrowableBlock(10, 0, world);
		level.addDynamicBody(denty);
		level.addDynamicBody(block);

		int dentyHP = denty.getHP();

		level.update();
		while (block.getLinearVelocity().y > 0)
			level.update();

		Assert.assertTrue(denty.getHP() < dentyHP);
		Assert.assertTrue(block.isAlive());
	}

	@Test
	public void testBlockFallingOnSpikey() {
		SpikeyEnemy enemy = new SpikeyEnemy(10, 19, world);
		ThrowableBlock block = new ThrowableBlock(10, 1, world);
		level.addDynamicBody(enemy);
		level.addDynamicBody(block);

		int enemyHP = enemy.getHP();

		level.update();
		while (block.getLinearVelocity().y > 0)
			level.update();

		// Same weird placement of Spikey at least. Something wrong with that
		// one?
		// Test passes occasionally due to position fluctuations.

		Assert.assertTrue(enemy.getHP() < enemyHP);
	}

	@Test
	public void testKeyAndLock() {
		Lock lock = new Lock(15, 18.5f, world);
		Key key = new Key(5, 19, world, true);
		level.addDynamicBody(lock);
		level.addDynamicBody(key);
		float newPos = key.getPosition().x;
		float prevPos = newPos - 1;
		while (newPos > prevPos) {
			prevPos = newPos;
			MoveController.moveRight(key);
			level.update();
			newPos = key.getPosition().x;
		}

		Assert.assertFalse(key.isAlive()); // Lock and key kill each other when
											// they collide.
		Assert.assertFalse(lock.isAlive());
	}

	@Test
	public void testEnemyHitEnemy() {
		BouncingEnemy bouncer = new BouncingEnemy(19, 10, world);
		ShootingEnemy shooter = new ShootingEnemy(19, 15, world);
		level.addDynamicBody(bouncer);
		level.addDynamicBody(shooter);

		int bouncerHP = bouncer.getHP();
		int shooterHP = shooter.getHP();

		float newPos = bouncer.getPosition().x;
		float prevPos = newPos - 1;
		while (newPos > prevPos) {
			prevPos = newPos;
			MoveController.moveRight(bouncer);
			level.update();
			newPos = bouncer.getPosition().x;
		}

		Assert.assertTrue(bouncer.getHP() == bouncerHP); // Even if bouncer
															// deals normal
															// damage, which
															// shooter is weak
															// to
		Assert.assertTrue(shooter.getHP() == shooterHP); // nothing should
															// happen to either
															// as they are both
															// "Enemy"

	}
}
