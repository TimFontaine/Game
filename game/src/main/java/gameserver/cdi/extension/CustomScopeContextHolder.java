package gameserver.cdi.extension;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;

public class CustomScopeContextHolder {

	private static CustomScopeContextHolder INSTANCE;
	
	private Map<String, CustomScopeInstance> beans;
	
	private CustomScopeContextHolder() {
		beans = Collections.synchronizedMap(new HashMap<String, CustomScopeInstance>());
	}
	
	public static CustomScopeContextHolder getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CustomScopeContextHolder();
		}
		return INSTANCE;
	}
	
	public Map<String, CustomScopeInstance> getBeans() {
		return beans;
	}
	
	public CustomScopeInstance getBean(String name) {
		return getBeans().get(name);
	}
	
	public void  putBean(String name, CustomScopeInstance bean) {
		getBeans().put(name, bean);
	}
	
	
	
	
	
	 public static class CustomScopeInstance<T> {
		 
	        Bean<T> bean;
	        CreationalContext<T> ctx;
	        T instance;
	 }
}
