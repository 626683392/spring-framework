package com.meetkiki.circulardependencies_v5;

import org.springframework.beans.BeansException;

@FunctionalInterface
public interface ObjectFactory {

	/**
	 * Return an instance (possibly shared or independent)
	 * of the object managed by this factory.
	 *
	 * @return the resulting instance
	 * @throws BeansException in case of creation errors
	 */
	Object getObject();
}
