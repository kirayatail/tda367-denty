package com.googlecode.tda367.denty.util;

import org.jbox2d.common.Vec2;

/**
 * Mathematical utilities
 * 
 */
public class Util {

	public static float toDegrees(float radians) {
		return radians * 180 / (float) Math.PI;
	}

	public static float distance(Vec2 v1, Vec2 v2) {
		return (float) Math.hypot(v1.x - v2.x, v1.y - v2.y);
	}

}
