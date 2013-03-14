package com.googlecode.tda367.denty.core.dynamicbody;

import static com.googlecode.tda367.denty.constants.DynamicConstant.VELOCITY_H_INC;
import static com.googlecode.tda367.denty.constants.DynamicConstant.VELOCITY_H_MAX;
import static com.googlecode.tda367.denty.constants.StateConstant.SHOOTING;

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
 * Still enemy that fires blocks and aims for Denty. Built after the idea of
 * "Artillerikanin"
 * 
 */
public class ShootingEnemy implements MoveableBody {
	private InnerBody body;
	private int shootTimer;

	public ShootingEnemy(float x, float y, World physicsWorld) {
		Body physicalBody = this.createPhysicalRepresentation(physicsWorld,
				new Vec2(x, y));
		this.body = new InnerBody(physicalBody, 40);
		this.defineConstants();
		this.shootTimer = 0;
	}

	private Body createPhysicalRepresentation(World world, Vec2 position) {
		BodyDef bodyDef = PhysicalBodiesUtil.createBodyDef(position,
				BodyType.STATIC, this, true, 2);
		Body body = this.createBody(world, bodyDef);
		return body;
	}

	private Body createBody(World world, BodyDef bodyDef) {
		PolygonShape ps = PhysicalBodiesUtil.createBoxShape(2, 2);
		FixtureDef fd = PhysicalBodiesUtil.createFixtureDef(ps, 0, 1.4f);
		return PhysicalBodiesUtil.createBody(world, bodyDef, fd);
	}

	@Override
	public Vec2 getPosition() {
		return new Vec2(this.body.getPosition());
	}

	@Override
	public float getRotation() {
		return 0;
	}

	@Override
	public float getConstant(DynamicConstant dc) {
		return this.body.getConstant(dc);
	}

	protected void defineConstants() {
		this.body.addDynamicConstant(VELOCITY_H_MAX, 0);
		this.body.addDynamicConstant(VELOCITY_H_INC, 0);

		this.body.addAllowedState(SHOOTING);
		this.setState(SHOOTING, true);
	}

	@Override
	public void beginContact(Fixture hitFixture, Collection<Hit> hitConstants) {
		if (!hitConstants.contains(Hit.ENEMY)
				&& hitConstants.contains(Hit.NORMAL_DMG)) {
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
	public boolean isAlive() {
		return this.body.isAlive();
	}

	@Override
	public String getImagePath() {
		return "res/img/rabbitEnemy.png";
	}

	@Override
	public Vec2 getLinearVelocity() {
		return new Vec2(0, 0);
	}

	@Override
	public void applyForce(Vec2 force) {
		// should not move!
	}

	@Override
	public void applyVelocity(Vec2 velocity) {
		// should not move!
	}

	@Override
	public boolean getState(StateConstant sc) {
		return this.body.getState(sc);
	}

	@Override
	public void setState(StateConstant sc, boolean b) {
		this.body.setState(sc, b);
	}

	public boolean canShoot() {
		return ++this.shootTimer % 240 == 0;
	}

	@Override
	public void endContact(Fixture hitFixture, Collection<Hit> hitConstants) {

	}

	public void setAlive(boolean alive) {
		body.setAlive(alive);
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(2, 2);
	}

	int getHP() {
		return body.getHP();
	}

	void setHP(int i) {
		body.changeHP(i - body.getHP());
	}
}
