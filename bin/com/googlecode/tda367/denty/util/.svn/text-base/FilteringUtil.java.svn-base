package com.googlecode.tda367.denty.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Methods for filtering out MoveableBodys from DynamicBody-lists.
 * 
 * @author max
 * 
 */
public class FilteringUtil {

	public static <T> boolean bodyIsA(Object obj, Class<T> classType) {
		return obj != null && classType.isAssignableFrom(obj.getClass());
	}

	public static <E, T> boolean listContainsA(List<E> objList,
			Class<T> classType) {
		if (objList != null) {
			for (Object obj : objList) {
				if (bodyIsA(obj, classType)) {
					return true;
				}
			}
		}
		return false;
	}

	public static <E, T> boolean listContainsOnly(List<E> objList,
			Class<T> classType) {
		if (objList != null) {
			for (Object obj : objList) {
				if (!bodyIsA(obj, classType)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <E, T> List<T> getListOf(List<E> objList, Class<T> classType) {
		List<T> list = new ArrayList<T>();
		if (objList != null) {
			for (Object obj : objList) {
				if (bodyIsA(obj, classType)) {
					list.add((T) obj);
				}
			}
		}
		return list;
	}

}
