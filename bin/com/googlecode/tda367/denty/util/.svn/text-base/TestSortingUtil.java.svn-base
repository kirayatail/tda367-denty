package com.googlecode.tda367.denty.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.BeforeClass;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import com.googlecode.tda367.denty.core.dynamicbody.Block;
import com.googlecode.tda367.denty.core.dynamicbody.Denty;
import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.dynamicbody.SimpleEnemy;
import com.googlecode.tda367.denty.core.dynamicbody.ThrowableBlock;

public class TestSortingUtil {
	private static List<DynamicBody> bodyList;
	private static List<SimpleEnemy> enemyList;
	private static List<Denty> dentyList;
	private static List<Block> blockList;
	private static List<ThrowableBlock> throwableBlockList;
	private static World world;

	@BeforeClass
	public static void initiateLists() throws SlickException {
		world = new World(new Vec2(0, 50), true);

		bodyList = new ArrayList<DynamicBody>();
		enemyList = new ArrayList<SimpleEnemy>();
		dentyList = new ArrayList<Denty>();
		blockList = new ArrayList<Block>();
		throwableBlockList = new ArrayList<ThrowableBlock>();

		dentyList.add(new Denty(1, 2, world));
		blockList.add(new Block(4, 3, world));
		blockList.add(new Block(5, 5, world));
		throwableBlockList.add(new ThrowableBlock(10, 10, world));
		throwableBlockList.add(new ThrowableBlock(11, 11, world));
		throwableBlockList.add(new ThrowableBlock(12, 12, world));
		enemyList.add(new SimpleEnemy(1, 1, world));
		enemyList.add(new SimpleEnemy(1, 3, world));
		enemyList.add(new SimpleEnemy(1, 4, world));
		enemyList.add(new SimpleEnemy(1, 5, world));

		bodyList.addAll(dentyList);
		bodyList.addAll(enemyList);
		bodyList.addAll(blockList);
		bodyList.addAll(throwableBlockList);
	}

	@Test
	public void testDentyIsADentyAndADynamicBody() {
		assertTrue(FilteringUtil.bodyIsA(dentyList.get(0), Denty.class));
		assertTrue(FilteringUtil.bodyIsA(dentyList.get(0), DynamicBody.class));
	}

	@Test
	public void testSimpleEnemyIsNotBlockAndIsADynamicBody() {
		assertFalse(FilteringUtil.bodyIsA(enemyList.get(0), Denty.class));
		assertTrue(FilteringUtil.bodyIsA(enemyList.get(0), DynamicBody.class));
	}

	@Test
	public void testBodyListContainsSimpleEnemiesAndDynamicBodies() {
		assertTrue(FilteringUtil.listContainsA(bodyList, SimpleEnemy.class));
		assertTrue(FilteringUtil.listContainsA(bodyList, DynamicBody.class));
	}

	@Test
	public void testEnemyListContainsNoBlocks() {
		assertFalse(FilteringUtil.listContainsA(enemyList, Block.class));
	}

	@Test
	public void testBodyListContainsOnlyDynamicBodies() {
		assertTrue(FilteringUtil.listContainsOnly(bodyList, DynamicBody.class));
	}

	@Test
	public void testBodyListNotContainsOnlyBlocks() {
		assertFalse(FilteringUtil.listContainsOnly(bodyList, Block.class));
	}

	@Test
	public void testGetAllSimpleEnemiesFromBodyList() {
		List<SimpleEnemy> newList = FilteringUtil.getListOf(bodyList,
				SimpleEnemy.class);
		assertTrue(newList.size() == 4);
		assertTrue(newList.containsAll(enemyList));
	}

	@Test
	public void testGetAllBlocksFromBlockList() {
		List<Block> newList = FilteringUtil.getListOf(blockList, Block.class);
		assertTrue(newList.size() == 2);
		assertTrue(newList.containsAll(blockList));
	}

	@Test
	public void testGetNoThrowableBlocksFromEnemyList() {
		List<ThrowableBlock> newList = FilteringUtil.getListOf(enemyList,
				ThrowableBlock.class);
		assertTrue(newList.size() == 0);
	}

}
