package com.googlecode.tda367.denty.ctrl;

import org.jbox2d.common.Vec2;

import com.googlecode.tda367.denty.constants.DynamicConstant;
import com.googlecode.tda367.denty.constants.StateConstant;
import com.googlecode.tda367.denty.core.dynamicbody.MoveableBody;

/**
 * Applies forces and velocities to MoveableBodys, according to the constants
 * they provide.
 */
public class MoveController {

	public static void moveLeft(MoveableBody db) {
		db.setState(StateConstant.MOVING_LEFT, true);
		Vec2 dbLinearVelocity = db.getLinearVelocity();
		if (dbLinearVelocity.x > -db
				.getConstant(DynamicConstant.VELOCITY_H_MAX))
			dbLinearVelocity.x -= db
					.getConstant(DynamicConstant.VELOCITY_H_INC);
		db.applyVelocity(dbLinearVelocity);
	}

	public static void moveRight(MoveableBody db) {
		db.setState(StateConstant.MOVING_RIGHT, true);
		Vec2 dbLinearVelocity = db.getLinearVelocity();
		if (dbLinearVelocity.x < db.getConstant(DynamicConstant.VELOCITY_H_MAX))
			dbLinearVelocity.x += db
					.getConstant(DynamicConstant.VELOCITY_H_INC);
		db.applyVelocity(dbLinearVelocity);
	}

	public static void jump(MoveableBody db) {
		Vec2 jumpForce = new Vec2(0, 0);
		if (Math.abs(db.getLinearVelocity().y) < 0.1) {
			jumpForce.y += db.getConstant(DynamicConstant.JUMP_FORCE);
		}
		throwBody(db, jumpForce);

	}

	public static void throwBody(MoveableBody db, Vec2 force) {
		db.applyForce(force);
	}

}
