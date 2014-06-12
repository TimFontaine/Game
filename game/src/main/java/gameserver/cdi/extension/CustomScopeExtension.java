package gameserver.cdi.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class CustomScopeExtension implements Extension {

	public void registerContext(@Observes final AfterBeanDiscovery event) {
        event.addContext(new CustomScopeContext());
    }
}
