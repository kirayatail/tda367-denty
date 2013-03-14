package com.googlecode.tda367.denty.core.dynamicbody;

import java.awt.Dimension;
import java.util.Collection;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import com.googlecode.tda367.denty.constants.Hit;
import com.googlecode.tda367.denty.util.PhysicalBodiesUtil;

/**
 * Represents a solid block that is snapped to the level grid, and may be
 * released (see ThrowableBlock).
 * 
 */
public class Block implements DynamicBody {
	private InnerBody body;

	public Block(float x, float y, World world) {
		this(world, x, y, 0.5f, 0.5f);
	}

	public Block(World world, float x, float y, float xs, float ys) {
		Body physicalBody = this.createPhysicalRepresentation(world, new Vec2(
				x, y), new Vec2(xs, ys));
		this.body = new InnerBody(physicalBody);
	}

	private Body createPhysicalRepresentation(World world, Vec2 position,
			Vec2 boxSides) {
		BodyDef bodyDef = PhysicalBodiesUtil.createBodyDef(new Vec2(
				position.x + 0.5f, position.y + 0.5f), BodyType.STATIC, this);
		PolygonShape blockShape = PhysicalBodiesUtil.createBoxShape(
				boxSides.x * 2, boxSides.y * 2);
		Body body = PhysicalBodiesUtil
				.createBody(world, bodyDef, blockShape, 0);
		return body;
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

	}

	@Override
	public Collection<Hit> getHitConstants(Fixture fixture) {
		return null;
	}

	@Override
	public void destroy() {
		body.destroy();
	}

	@Override
	public boolean isAlive() {
		return body.isAlive();
	}

	public void setAlive(boolean alive) {
		body.setAlive(alive);
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
