package com.meetkiki.circulardependencies_v5;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

public class JdkProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

	/**
	 * Obtain a reference for early access to the specified bean,
	 * typically for the purpose of resolving a circular reference.
	 * <p>This callback gives post-processors a chance to expose a wrapper
	 * early - that is, before the target bean instance is fully initialized.
	 * The exposed object should be equivalent to the what
	 * {@link #postProcessBeforeInitialization} / {@link #postProcessAfterInitialization}
	 * would expose otherwise. Note that the object returned by this method will
	 * be used as bean reference unless the post-processor returns a different
	 * wrapper from said post-process callbacks. In other words: Those post-process
	 * callbacks may either eventually expose the same reference or alternatively
	 * return the raw bean instance from those subsequent callbacks (if the wrapper
	 * for the affected bean has been built for a call to this method already,
	 * it will be exposes as final bean reference by default).
	 * <p>The default implementation returns the given {@code bean} as-is.
	 *
	 * @param bean     the raw bean instance
	 * @param beanName the name of the bean
	 * @return the object to expose as bean reference
	 * (typically with the passed-in bean instance as default)
	 * @throws BeansException in case of errors
	 */
	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		if (bean instanceof InstanceA) {
			// 假设 A 使用了AOP，@Pointcut("execution(* *..InstanceA.*(..))") //要给A创建动态代理
			return new JdkDynamicAopProxy().getProxy();
		}
		return bean;
	}
}
