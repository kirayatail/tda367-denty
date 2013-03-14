package com.googlecode.tda367.denty.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;
import com.googlecode.tda367.denty.core.dynamicbody.TestBody;

/*
 * Bounds: (0,0) to (10,10)
 * 
 */

public class TestDBController {
	private TestBody body;
	private List<DynamicBody> bodyList;

	@Before
	public void init() {
		body = new TestBody();
		body.setPosition(0f, 0f);
		bodyList = new ArrayList<DynamicBody>();
	}

	@Test
	public void testBodyWithinBounds() {
		body.setPosition(5f, 5f);
		bodyList.add(body);
		Assert.assertFalse("Pre-condition, testBodyWithinBounds",
				body.isDestroyed);
		Assert.assertTrue("Pre-condition list, testBodyWithinBounds",
				bodyList.contains(body));

		RecycleController.update(bodyList, 10f, 10f);

		Assert.assertFalse("Post-condition, testBodyWithinBounds",
				body.isDestroyed);
		Assert.assertTrue("Post-condition list, testBodyWithinBounds",
				bodyList.contains(body));
	}

	@Test
	public void testBodyOutOfXBounds() {
		body.setPosition(11f, 5f);
		bodyList.add(body);
		Assert.assertFalse("Pre-condition, testBodyOutOfXBounds",
				body.isDestroyed);
		Assert.assertTrue("Pre-condition list, testBodyOutOfXBounds",
				bodyList.contains(body));

		RecycleController.update(bodyList, 10f, 10f);

		Assert.assertFalse("Post-condition, testBodyOutOfXBounds",
				body.isDestroyed);
		Assert.assertTrue("Post-condition list, testBodyOutOfXBounds",
				bodyList.contains(body));
	}

	@Test
	public void testBodyOutOfYBounds() {
		body.setPosition(5f, 11f);
		bodyList.add(body);
		Assert.assertFalse("Pre-condition, testBodyOutOfYBounds",
				body.isDestroyed);
		Assert.assertTrue("Pre-condition list, testBodyOutOfYBounds",
				bodyList.contains(body));

		RecycleController.update(bodyList, 10f, 10f);

		Assert.assertTrue("Post-condition, testBodyOutOfYBounds",
				body.isDestroyed);
		Assert.assertFalse("Post-condition list, testBodyOutOfYBounds",
				bodyList.contains(body));
	}

	@Test
	public void testBodyIsAlive() {

	}

}
