package com.googlecode.tda367.denty.core.dynamicbody;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.dynamics.Fixture;

import com.googlecode.tda367.denty.util.FilteringUtil;

/**
 * Identifies objects in the level environment.
 * 
 */
public class DBQueryCallback implements QueryCallback {

	private List<Fixture> fixtures;

	public DBQueryCallback() {
		this.fixtures = new ArrayList<Fixture>();
	}

	@Override
	public boolean reportFixture(Fixture fixture) {
		if (fixture != null) {
			this.fixtures.add(fixture);
		}
		return true;
	}

	public boolean isAreaFree() {
		return this.fixtures.isEmpty();
	}

	private <T> boolean bodyIsA(int i, Class<T> classType) {
		if (i < fixtures.size() && i >= 0) {
			try {
				Object gameObject = fixtures.get(i).m_body.m_userData;
				return FilteringUtil.bodyIsA(gameObject, classType);
			} catch (NullPointerException e) {
			}
		}
		return false;
	}

	public <T> boolean areaContains(Class<T> classType) {
		for (int i = 0; i < fixtures.size(); i++) {
			if (bodyIsA(i, classType)) {
				return true;
			}
		}
		return false;
	}

	public List<DynamicBody> getDynamicBodies() {
		List<DynamicBody> list = new ArrayList<DynamicBody>();
		for (Fixture f : fixtures) {
			try {
				Object gameObject = f.m_body.m_userData;
				if (FilteringUtil.bodyIsA(gameObject, DynamicBody.class)) {
					list.add((DynamicBody) gameObject);
				}
			} catch (NullPointerException e) {
			}
		}
		return list;
	}

}
