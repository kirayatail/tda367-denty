package com.googlecode.tda367.denty.core.dynamicbody;

import static com.googlecode.tda367.denty.constants.DynamicConstant.VELOCITY_H_INC;
import static com.googlecode.tda367.denty.constants.DynamicConstant.VELOCITY_H_MAX;
import static com.googlecode.tda367.denty.constants.StateConstant.*;
import static com.googlecode.tda367.denty.util.PhysicalBodiesUtil.createFixtureDef;

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
 * A dumb enemy that forcefully moves from point A to B, and back again. It
 * carries on in the same direction, even when obstacles are present.
 * 
 */
public class SimpleEnemy implements MoveableBody {
	private InnerBody body;
	private float stdRestitution = 1.4f;
	private float density = 0.5f;
	private Fixture leftBumper;
	private Fixture rightBumper;

	public SimpleEnemy(float x, float y, World physicsWorld) {
		Body physicalBody = this.createPhysicalRepresentation(physicsWorld,
				new Vec2(x, y));
		this.leftBumper = physicalBody.createFixture(createLeftBumper(density));
		this.rightBumper = physicalBody.createFixture(createRightBumper(density));
		this.body = new InnerBody(physicalBody, 20);
		
		this.defineConstants();
	}

	private Body createPhysicalRepresentation(World world, Vec2 position) {
		BodyDef bodyDef = PhysicalBodiesUtil.createBodyDef(position,
				BodyType.DYNAMIC, this, true, 2);
		
		ArrayList<FixtureDef> parts = new ArrayList<FixtureDef>();
		parts.add(this.createBottomShape(density));
		parts.add(this.createTopShape(density));
		return PhysicalBodiesUtil.createBody(world, bodyDef, parts);
	}

	private FixtureDef createBottomShape(float density) {
		Vec2[] vertices = { new Vec2(-0.6f, 0.38f), new Vec2(0.6f, 0.38f),
				new Vec2(0.49f, 0.49f), new Vec2(-0.49f, 0.49f) };
		PolygonShape bottomShape = PhysicalBodiesUtil
				.createPolygonShape(vertices);
		return PhysicalBodiesUtil.createFixtureDef(bottomShape, density, 0);
	}

	private FixtureDef createTopShape(float density) {
		Vec2[] vertices = { new Vec2(-0.98f, -0.49f), new Vec2(0.98f, -0.49f),
				new Vec2(0.98f, 0f), new Vec2(0.6f, 0.38f),
				new Vec2(-0.6f, 0.38f), new Vec2(-0.98f, 0f) };
		PolygonShape topShape = PhysicalBodiesUtil.createPolygonShape(vertices);
		return createFixtureDef(topShape, density, this.stdRestitution);
	}
	
	private FixtureDef createRightBumper(float density){
		Vec2[] vertices = {new Vec2(0.75f, -0.25f), new Vec2(1f, -0.25f), 
				new Vec2(1f, 0f), new Vec2(0.75f, 0f) };
		PolygonShape leftBumperShape = PhysicalBodiesUtil.createPolygonShape(vertices);
		return PhysicalBodiesUtil.createFixtureDef(leftBumperShape, density, stdRestitution);
	}
	
	private FixtureDef createLeftBumper(float density){
		Vec2[] vertices = {new Vec2(-1f, -0.25f), new Vec2(-0.75f, -0.25f), 
				new Vec2(-0.75f, 0f), new Vec2(-1f, 0f) };
		PolygonShape leftBumperShape = PhysicalBodiesUtil.createPolygonShape(vertices);
		return PhysicalBodiesUtil.createFixtureDef(leftBumperShape, density, stdRestitution);
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
		return 0;
	}

	@Override
	public float getConstant(DynamicConstant dc) {
		return body.getConstant(dc);
	}

	protected void defineConstants() {
		this.body.addDynamicConstant(VELOCITY_H_MAX, 8);
		this.body.addDynamicConstant(VELOCITY_H_INC, .5f);

		this.body.addAllowedState(AUTO_MOVE);
		this.body.addAllowedState(MOVING_LEFT);
		this.body.addAllowedState(MOVING_RIGHT);
		this.body.addAllowedState(CONTACT_LEFT);
		this.body.addAllowedState(CONTACT_RIGHT);
		this.body.setState(AUTO_MOVE, true);
		this.body.setState(MOVING_LEFT, true);
	}

	public void setXBounds(float west, float east) {
		this.body.setXBounds(west, east);
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
		
		if(hitFixture.equals(leftBumper)){
			this.body.setState(CONTACT_LEFT, true);
		}
		if(hitFixture.equals(rightBumper)){
			this.body.setState(CONTACT_RIGHT, true);
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
		return "res/img/simpleEnemy.png";
	}

	@Override
	public void endContact(Fixture hitFixture, Collection<Hit> hitConstants) {
		if(hitFixture.equals(leftBumper)){
			this.body.setState(CONTACT_LEFT, false);
		}
		if(hitFixture.equals(rightBumper)){
			this.body.setState(CONTACT_RIGHT, false);
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