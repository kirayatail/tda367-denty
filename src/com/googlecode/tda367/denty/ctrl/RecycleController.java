package com.googlecode.tda367.denty.ctrl;

import java.util.List;

import com.googlecode.tda367.denty.core.dynamicbody.DynamicBody;

/**
 * Removes DynamicBodys that have died logically (either by falling out of
 * bounds or by other means)
 * 
 */
public class RecycleController {

	public static void update(List<DynamicBody> bodies, float x, float y) {
		removeOutOfBounds(bodies, x, y);
		removeKilled(bodies);
	}

	private static void removeKilled(List<DynamicBody> bodies) {
		for (int i = 0; i < bodies.size(); i++) {
			DynamicBody body = bodies.get(i);
			if (!body.isAlive()) {
				body.destroy();
				bodies.remove(i);
			}
		}

	}

	private static void removeOutOfBounds(List<DynamicBody> bodies, float x,
			float y) {
		for (int i = 0; i < bodies.size(); i++) {
			DynamicBody body = bodies.get(i);
			if (body.getPosition().y > y) {
				body.destroy();
				bodies.remove(i);
			}
		}
	}

}
