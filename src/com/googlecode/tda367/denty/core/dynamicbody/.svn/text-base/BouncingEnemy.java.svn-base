package com.googlecode.tda367.denty.core.dynamicbody;

import static com.googlecode.tda367.denty.constants.DynamicConstant.BOUNDS_EAST;
import static com.googlecode.tda367.denty.constants.DynamicConstant.BOUNDS_WEST;
import static com.googlecode.tda367.denty.constants.DynamicConstant.VELOCITY_H_INC;
import static com.googlecode.tda367.denty.constants.DynamicConstant.VELOCITY_H_MAX;
import static com.googlecode.tda367.denty.constants.StateConstant.AUTO_MOVE;
import static com.googlecode.tda367.denty.constants.StateConstant.FOLLOWING_DENTY;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import com.googlecode.tda367.denty.constants.DynamicConstant;
import com.googlecode.tda367.denty.constants.Hit;
import com.googlecode.tda367.denty.constants.StateConstant;
import com.googlecode.tda367.denty.util.PhysicalBodiesUtil;

/**
 * This enemy bounces at a target height. When Denty gets close enough, the
 * enemy moves towards him, still bouncing.
 * 
 */
public class BouncingEnemy implements MoveableBody {
	private InnerBody body;

	public BouncingEnemy(float x, float y, World physicsWorld) {
		Body physicalBody = this.createPhysicalRepresentation(physicsWorld,
				new Vec2(x, y));
		this.body = new InnerBody(physicalBody, 20);
		this.defineConstants();
	}

	private Body createPhysicalRepresentation(World world, Vec2 position) {
		CircleShape shape = PhysicalBodiesUtil.createCircleShape();
		FixtureDef fixtureDef = PhysicalBodiesUtil.createFixtureDef(shape,
				0.5f, 1.3f);
		BodyDef bodyDef = PhysicalBodiesUtil.createBodyDef(position,
				BodyType.DYNAMIC, this, true, 2);
		Body body = PhysicalBodiesUtil.createBody(world, bodyDef, fixtureDef);
		return body;
	}

	@Override
	public Vec2 getPosition() {
		return new Vec2(this.body.getPosition());
	}

	@Override
	public Vec2 getLinearVelocity() {
		return new Vec2(this.body.getLinearVelocity());
	}

	@Override
	public void applyForce(Vec2 force) {
		this.body.applyForce(force);
	}

	@Override
	public void applyVelocity(Vec2 velocity) {
		this.body.applyVelocity(velocity);
	}

	@Override
	public float getRotation() {
		return this.body.getRotation();
	}

	@Override
	public float getConstant(DynamicConstant dc) {
		return this.body.getConstant(dc);
	}

	private void defineConstants() {
		this.body.addDynamicConstant(VELOCITY_H_MAX, 5f);
		this.body.addDynamicConstant(VELOCITY_H_INC, 1f);

		this.body.addAllowedState(AUTO_MOVE);
		this.body.addAllowedState(FOLLOWING_DENTY);
		this.body.setState(AUTO_MOVE, true);
		this.body.setState(FOLLOWING_DENTY, true);
	}

	public void setXBounds(float west, float east) {
		this.body.addDynamicConstant(BOUNDS_WEST, west);
		this.body.addDynamicConstant(BOUNDS_EAST, east);
	}

	@Override
	public void beginContact(Fixture hitFixture, Collection<Hit> hitConstants) {
		if (!hitConstants.contains(Hit.ENEMY)
				&& (hitConstants.contains(Hit.NORMAL_DMG) || hitConstants
						.contains(Hit.PIERCING_DMG))) {
			int dmg;
			if (hitConstants.contains(Hit.DMG_HALF))
				dmg = -5;
			else
				dmg = -10;
			this.body.changeHP(dmg);
		}
	}

	@Override
	public Collection<Hit> getHitConstants(Fixture fixture) {
		List<Hit> hitConstants = new ArrayList<Hit>();
		hitConstants.add(Hit.ENEMY);
		hitConstants.add(Hit.NORMAL_DMG);
		return hitConstants;
	}

	@Override
	public void destroy() {
		this.body.destroy();
	}

	@Override
	public boolean getState(StateConstant sc) {
		return this.body.getState(sc);
	}

	@Override
	public void setState(StateConstant sc, boolean b) {
		this.body.setState(sc, b);
	}

	@Override
	public boolean isAlive() {
		return this.body.isAlive();
	}

	@Override
	public String getImagePath() {
		return "res/img/bouncing-enemy.png";
	}

	@Override
	public void endContact(Fixture hitFixture, Collection<Hit> hitConstants) {

	}

	public void setAlive(boolean alive) {
		body.setAlive(alive);
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(1, 1);
	}

	int getHP() {
		return body.getHP();
	}

	void setHP(int i) {
		body.changeHP(i - body.getHP());
	}
}
