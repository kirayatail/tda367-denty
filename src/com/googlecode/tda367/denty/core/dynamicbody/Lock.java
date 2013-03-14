package com.googlecode.tda367.denty.core.dynamicbody;

import static com.googlecode.tda367.denty.constants.Hit.KEY;
import static com.googlecode.tda367.denty.constants.Hit.LOCK;

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
import org.jbox2d.dynamics.World;

import com.googlecode.tda367.denty.constants.Hit;
import com.googlecode.tda367.denty.util.PhysicalBodiesUtil;

/**
 * A big block that cannot be manipulated except by the key. Disappears on
 * contact with the key.
 * 
 */
public class Lock implements DynamicBody {

	private InnerBody body;

	public Lock(float x, float y, World world) {
		Vec2 pos = new Vec2(x, y);
		body = new InnerBody(this.createPhysicalRep(world, pos));
	}

	private Body createPhysicalRep(World world, Vec2 pos) {
		BodyDef def = PhysicalBodiesUtil.createBodyDef(pos, BodyType.STATIC,
				this);
		PolygonShape shape = PhysicalBodiesUtil.createBoxShape(2, 3);

		return PhysicalBodiesUtil.createBody(world, def, shape);
	}

	@Override
	public String getImagePath() {
		return "res/img/door-lock.png";
	}

	@Override
	public void beginContact(Fixture hitFixture, Collection<Hit> hitConstants) {
		if (hitConstants.contains(KEY))
			this.setAlive(false);
	}

	@Override
	public Collection<Hit> getHitConstants(Fixture fixture) {
		List<Hit> hitConstants = new ArrayList<Hit>();
		hitConstants.add(LOCK);

		return hitConstants;
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
		return new Dimension(2, 3);
	}

}
