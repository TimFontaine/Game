package gameserver.server.executors;

import javax.enterprise.util.AnnotationLiteral;

public class ModusQualifier extends AnnotationLiteral<Modus> implements Modus {

	ModusEnum modus;
	
	public ModusQualifier(ModusEnum modus) {
		this.modus = modus;
	}
	
	@Override
	public ModusEnum value() {
		return modus;
	}

}
