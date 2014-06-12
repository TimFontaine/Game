package gameserver.cdi.extension;

import gameserver.cdi.extension.CustomScopeContextHolder.CustomScopeInstance;
import gameserver.cdi.qualifiers.GameScope;

import java.lang.annotation.Annotation;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;

public class CustomScopeContext implements Context {

	private CustomScopeContextHolder holder;
	
	public CustomScopeContext() {
		
		holder = CustomScopeContextHolder.getInstance();
		System.out.println("extesion loading");
	}
	
	@Override
	public <T> T get(Contextual<T> contextual) {
		System.out.println("get short method");
		Bean bean = (Bean) contextual;
		return null;
	}

	@Override
	public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
		Bean bean = (Bean) contextual;
		if (holder.getBeans().containsKey(generateName(bean))) {
			return (T) holder.getBean(generateName(bean)).instance;
		} else {
			T t =  (T) bean.create(creationalContext);
			CustomScopeInstance instance = new CustomScopeInstance();
			instance.instance = t;
			instance.ctx = creationalContext;
			instance.bean = bean;
			holder.putBean(generateName(bean) ,instance);
			return t;
		}
	}
	
	private String generateName(Bean bean) {
		return Thread.currentThread().getName() + bean.getBeanClass();
	}

	@Override
	public Class<? extends Annotation> getScope() {
		return GameScope.class;
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
