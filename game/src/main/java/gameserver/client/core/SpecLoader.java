package gameserver.client.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.tim.game.generated.gui.GameActionProp;
import com.tim.game.generated.gui.GuiData;

@ApplicationScoped
public class SpecLoader {

	private static SpecLoader INSTANCE;
	
	@Inject private GuiWrapper guiWrapper;
	
	public SpecLoader() {
		
	}
	
	public void loadGUI() { 
		try {
			JAXBContext context = JAXBContext.newInstance(GuiData.class);
			Unmarshaller um = context.createUnmarshaller();
			JAXBElement<GuiData> guiActions =  um.unmarshal(new StreamSource(new File("gui.xml")), GuiData.class);
			guiActions.getValue().getGameActionProps();
			//@TODO refactor to cdi di
			guiWrapper.transform(guiActions.getValue());
//			this.gameActionProps = guiActions.getValue().getGameActionProps();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	
	public GuiWrapper getGuiData() {
		return guiWrapper;
	}

//	public static void main(String args[]) {
//		SpecLoader specLoader = new SpecLoader();
//		specLoader.loadGUI();
//		
//	}

	
	
}
