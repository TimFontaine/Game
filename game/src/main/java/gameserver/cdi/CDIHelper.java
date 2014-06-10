package gameserver.cdi;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.InjectionTarget;

public class CDIHelper {
	
	static CDIHelper INSTANCE;
	
	private CDIHelper() {}
	
	public static CDIHelper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CDIHelper();
		}
		return INSTANCE;
	}

	BeanManager manager = CDI.current().getBeanManager();
	
	public void manager(Object aInstance) {
		
		AnnotatedType<Object> theType = (AnnotatedType<Object>) manager.createAnnotatedType(aInstance.getClass());
        InjectionTarget<Object> theTarget = manager.createInjectionTarget(theType);
 
        CreationalContext<Object> cc = manager.createCreationalContext(null);
 
        theTarget.inject(aInstance, cc);
        theTarget.postConstruct(aInstance);
	}
	
	
	
	
}
