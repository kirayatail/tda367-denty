package com.googlecode.tda367.denty.core.dynamicbody;

import static com.googlecode.tda367.denty.constants.Hit.KEY;
import static com.googlecode.tda367.denty.constants.Hit.LOCK;

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
 * Resembles a throwable block that cannot be picked up. Disappears when it
 * collides with a lock.
 * 
 */
public class Key implements MoveableBody {

	private InnerBody body;

	public Key(float x, float y, World world) {
		Body keyBody = createPhysicalRepresentation(world, new Vec2(x, y));
		this.body = new InnerBody(keyBody);
	}

	public Key(float x, float y, World world, boolean test) {
		this(x, y, world);
		if (test)
			defineConstants();
	}

	private Body createPhysicalRepresentation(World world, Vec2 position) {
		BodyDef bodyDef = PhysicalBodiesUtil.createBodyDef(position,
				BodyType.DYNAMIC, this);
		CircleShape shape = PhysicalBodiesUtil.createCircleShape();
		FixtureDef fixtureDef = PhysicalBodiesUtil.createFixtureDef(shape,
				0.2f, 0f);
		fixtureDef.friction = 10;
		Body body = PhysicalBodiesUtil.createBody(world, bodyDef, fixtureDef);
		body.setAngularDamping(2f);
		return body;
	}

	private void defineConstants() {
		body.addDynamicConstant(DynamicConstant.VELOCITY_H_MAX, 7);
		body.addDynamicConstant(DynamicConstant.VELOCITY_H_INC, 5);
	}

	@Override
	public void beginContact(Fixture hitFixture, Collection<Hit> hitConstants) {
		if (hitConstants.contains(LOCK))
			body.setAlive(false);
	}

	@Override
	public Collection<Hit> getHitConstants(Fixture fixture) {
		List<Hit> hitConstants = new ArrayList<Hit>();
		hitConstants.add(KEY);
		return hitConstants;
	}

	@Override
	public String getImagePath() {
		return "res/img/circle-key.png";
	}

	@Override
	public Vec2 getPosition() {
		return body.getPosition();
	}

	@Override
	public float getRotation() {
		return body.getRotation();
	}

	@Override
	public void endContact(Fixture hitFixture, Collection<Hit> hitConstants) {
		// Nothing needed

	}

	@Override
	public void destroy() {
		body.destroy();

	}

	@Override
	public boolean isAlive() {
		return body.isAlive();
	}

	@Override
	public void setAlive(boolean alive) {
		body.setAlive(alive);
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(1, 1);
	}

	@Override
	public Vec2 getLinearVelocity() {
		return body.getLinearVelocity();
	}

	@Override
	public void applyForce(Vec2 force) {
		body.applyForce(force);

	}

	@Override
	public void applyVelocity(Vec2 velocity) {
		body.applyVelocity(velocity);

	}

	@Override
	public float getConstant(DynamicConstant dc) {
		return body.getConstant(dc);
	}

	@Override
	public boolean getState(StateConstant sc) {
		return body.getState(sc);
	}

	@Override
	public void setState(StateConstant sc, boolean b) {
		body.setState(sc, b);

	}
}
