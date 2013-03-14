package com.googlecode.tda367.denty.core.dynamicbody;

import static com.googlecode.tda367.denty.constants.DynamicConstant.VELOCITY_H_INC;
import static com.googlecode.tda367.denty.constants.DynamicConstant.VELOCITY_H_MAX;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
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
 * An enemy that cannot be beaten by Denty (due to the hazardous spikes), and
 * must be shot down with blocks. Moves from A to B initially, but turns around
 * if anything is in the way.
 * 
 */

public class SpikeyEnemy implements MoveableBody {
	private InnerBody body;
	private Fixture rightHead;
	private Fixture leftHead;

	public SpikeyEnemy(float x, float y, World physicsWorld) {
		Body physicalBody = this.createPhysicalRepresentation(physicsWorld,
				new Vec2(x, y));
		this.body = new InnerBody(physicalBody, 20);
		this.defineConstants();
	}

	private Body createPhysicalRepresentation(World world, Vec2 position) {
		BodyDef bodyDef = PhysicalBodiesUtil.createBodyDef(position,
				BodyType.DYNAMIC, this, true, 2);
		Body body = this.createBody(world, bodyDef);
		return body;
	}

	private Body createBody(World world, BodyDef bodyDef) {
		Body body = world.createBody(bodyDef);

		Vec2[] vertexArray = { new Vec2(0f, -0.49f), new Vec2(0.98f, 0.24f),
				new Vec2(0.7f, 0.38f), new Vec2(-0.7f, 0.38f),
				new Vec2(-0.98f, 0.24f) };
		PolygonShape ts = PhysicalBodiesUtil.createPolygonShape(vertexArray);
		FixtureDef tf = PhysicalBodiesUtil.createFixtureDef(ts, 0.5f, 1.4f);
		body.createFixture(tf);

		vertexArray = new Vec2[4];
		vertexArray[0] = new Vec2(0.7f, 0.38f);
		vertexArray[1] = new Vec2(0.49f, 0.49f);
		vertexArray[2] = new Vec2(-0.49f, 0.49f);
		vertexArray[3] = new Vec2(-0.7f, 0.38f);
		PolygonShape bs = PhysicalBodiesUtil.createPolygonShape(vertexArray);
		body.createFixture(bs, 0.5f);

		vertexArray = new Vec2[3];
		vertexArray[0] = new Vec2(1f, 0f);
		vertexArray[1] = new Vec2(1f, 0.25f);
		vertexArray[2] = new Vec2(0.75f, 0.25f);
		PolygonShape rs = PhysicalBodiesUtil.createPolygonShape(vertexArray);
		FixtureDef rf = PhysicalBodiesUtil.createFixtureDef(rs, 0.5f, 1.4f);
		this.rightHead = body.createFixture(rf);

		vertexArray[0] = new Vec2(-1f, 0f);
		vertexArray[1] = new Vec2(-0.75f, 0.25f);
		vertexArray[2] = new Vec2(-1f, 0.25f);
		PolygonShape ls = PhysicalBodiesUtil.createPolygonShape(vertexArray);
		FixtureDef lf = PhysicalBodiesUtil.createFixtureDef(ls, 0.5f, 1.8f);
		this.leftHead = body.createFixture(lf);

		return body;
	}

	private void defineConstants() {
		body.addAllowedState(StateConstant.MOVING_LEFT);
		body.addAllowedState(StateConstant.MOVING_RIGHT);
		body.addAllowedState(StateConstant.AUTO_MOVE);
		body.addAllowedState(StateConstant.CONTACT_LEFT);
		body.addAllowedState(StateConstant.CONTACT_RIGHT);
		body.setState(StateConstant.MOVING_LEFT, true);
		body.setState(StateConstant.AUTO_MOVE, true);

		this.body.addDynamicConstant(VELOCITY_H_MAX, 8);
		this.body.addDynamicConstant(VELOCITY_H_INC, .5f);
	}

	public void setXBounds(float a, float b) {
		this.body.setXBounds(a, b);
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
	public void beginContact(Fixture hitFixture, Collection<Hit> hitConstants) {

		if (hitFixture.equals(this.leftHead)) {
			this.setState(StateConstant.CONTACT_LEFT, true);
		} else if (hitFixture.equals(this.rightHead)) {
			this.setState(StateConstant.CONTACT_RIGHT, true);
		}

		if (!hitConstants.contains(Hit.ENEMY)
				&& hitConstants.contains(Hit.PIERCING_DMG)) {
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
		hitConstants.add(Hit.PIERCING_DMG);

		return hitConstants;
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
	public String getImagePath() {
		return "res/img/spikey-enemy.png";
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
		this.body.setState(sc, b);
	}

	@Override
	public void endContact(Fixture hitFixture, Collection<Hit> hitConstants) {
		if (hitFixture.equals(this.leftHead)) {
			this.setState(StateConstant.CONTACT_LEFT, false);
		} else if (hitFixture.equals(this.rightHead)) {
			this.setState(StateConstant.CONTACT_RIGHT, false);
		}

	}

	public void setAlive(boolean alive) {
		body.setAlive(alive);
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(2, 1);
	}

	int getHP() {
		return body.getHP();
	}

	void setHP(int i) {
		body.changeHP(i - body.getHP());
	}

}