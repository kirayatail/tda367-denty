package com.googlecode.tda367.denty.core.dynamicbody;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.tda367.denty.constants.StateConstant;

public class TestDBStates {
	private TestBody enemy;

	@Before
	public void setUp() throws Exception {
		enemy = new TestBody();
		enemy.states.clear();
		enemy.states.put(StateConstant.MOVING_LEFT, false);
		// Only existing state in the test is "Moving left". Testing will
		// revolve around it.
	}

	@Test
	public void testStateDoesntExist() {
		// Try setting a state that isn't initiated
		enemy.setState(StateConstant.AUTO_MOVE, true);

		// Should be false. Call above normally sets to true, but as it doesn't
		// exist in list - returns false.
		Assert.assertFalse(enemy.getState(StateConstant.AUTO_MOVE));
	}

	@Test
	public void testSettingState() {
		boolean rawState = enemy.states.get(StateConstant.MOVING_LEFT);
		// Check pre-test = false
		Assert.assertFalse("Pre Test Setting state failed", rawState);

		// Try setting state that is initiated
		enemy.setState(StateConstant.MOVING_LEFT, true);

		// Check if true
		Assert.assertTrue("Test Setting state failed",
				enemy.getState(StateConstant.MOVING_LEFT));

	}

}
