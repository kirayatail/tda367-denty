package com.googlecode.tda367.denty.core.dynamicbody;

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
import com.googlecode.tda367.denty.core.data.GameData;
import com.googlecode.tda367.denty.util.PhysicalBodiesUtil;

/**
 * A block that may fall from the sky, fly through the air, bounce and hit
 * stuff. Conjured by the Mouse Player, ShootingEnemy, or slouches about in the
 * various levels. These blocks are dangerous, and may harm Denty if the
 * collision speed is too great. At low velocities, these blocks can be picked
 * up, given that Denty can carry them.
 * 
 */
public class ThrowableBlock implements MoveableBody {
	private InnerBody body;

	public ThrowableBlock(float x, float y, World world) {
		Body physicalBody = this.createPhysicalRepresentation(world, new Vec2(
				x, y));
		this.body = new InnerBody(physicalBody);
		this.defineConstants();
	}

	public ThrowableBlock(float x, float y, World world, boolean fast) {
		this(x, y, world);
		this.body.addDynamicConstant(DynamicConstant.VELOCITY_H_MAX, 20);
		this.body.addDynamicConstant(DynamicConstant.VELOCITY_H_INC, 20);
	}

	private Body createPhysicalRepresentation(World world, Vec2 position) {
		BodyDef bodyDef = PhysicalBodiesUtil.createBodyDef(position,
				BodyType.DYNAMIC, this);
		PolygonShape shape = PhysicalBodiesUtil.createBoxShape();
		FixtureDef fixtureDef = PhysicalBodiesUtil.createFixtureDef(shape,
				0.2f, 0f);
		Body body = PhysicalBodiesUtil.createBody(world, bodyDef, fixtureDef);
		return body;
	}

	private void defineConstants() {
		this.body.addDynamicConstant(DynamicConstant.VELOCITY_H_MAX, 6);
		this.body.addDynamicConstant(DynamicConstant.VELOCITY_H_INC, 4);
		this.body.addDynamicConstant(DynamicConstant.VELOCITY_V_MAX, 6);
		this.body.addDynamicConstant(DynamicConstant.VELOCITY_V_INC, 4);
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

	@Override
	public boolean getState(StateConstant sc) {
		return this.body.getState(sc);
	}

	@Override
	public void setState(StateConstant sc, boolean b) {
		this.body.setState(sc, b);
	}

	@Override
	public void beginContact(Fixture hitFixture, Collection<Hit> hitConstants) {
		GameData gameData = GameData.getInstance();
		if (hitConstants.contains(Hit.DENTY)
				&& !this.getHitConstants(hitFixture).contains(Hit.PIERCING_DMG)
				&& this.isAlive()
				&& gameData.getBlocks() < gameData.getMaxBlocks()) {
			this.setAlive(false);
			gameData.setBlocks(gameData.getBlocks() + 1);
		}
	}

	@Override
	public Collection<Hit> getHitConstants(Fixture fixture) {
		List<Hit> hitConstants = new ArrayList<Hit>();
		Vec2 linV = this.getLinearVelocity();
		double velocity = Math.sqrt(linV.x * linV.x + linV.y * linV.y);
		if (velocity > 15) {
			hitConstants.add(Hit.PIERCING_DMG);
			hitConstants.add(Hit.DMG_HALF);
		}
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

	public void setAlive(boolean value) {
		this.body.setAlive(value);
	}

	@Override
	public String getImagePath() {
		return "res/img/block.png";
	}

	@Override
	public void endContact(Fixture hitFixture, Collection<Hit> hitConstants) {

	}

	@Override
	public Dimension getDimension() {
		return new Dimension(1, 1);
	}
}
