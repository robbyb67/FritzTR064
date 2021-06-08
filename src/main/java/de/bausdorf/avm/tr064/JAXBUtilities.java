package de.bausdorf.avm.tr064;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import de.bausdorf.avm.tr064.beans.ActionType;
import de.bausdorf.avm.tr064.beans.AllowedValueRangeType;
import de.bausdorf.avm.tr064.beans.ArgumentType;
import de.bausdorf.avm.tr064.beans.DeviceDesc;
import de.bausdorf.avm.tr064.beans.IconType;
import de.bausdorf.avm.tr064.beans.RootType;
import de.bausdorf.avm.tr064.beans.ScpdType;
import de.bausdorf.avm.tr064.beans.ServiceDesc;
import de.bausdorf.avm.tr064.beans.SpecVersionType;
import de.bausdorf.avm.tr064.beans.StateVariableType;

/**
 * helper to create a JAXB unmarshaller which removed the namespace, namespaces
 * seem to be different across different fritzOS versions and we dont seem to
 * need them, so we use a filter to remove em
 *
 */
public class JAXBUtilities {

	public static JAXBContext getContext() throws ParseException {
		try {

			JAXBContext context = JAXBContext.newInstance(RootType.class, ScpdType.class, ActionType.class,
					AllowedValueRangeType.class, ArgumentType.class, DeviceDesc.class, ServiceDesc.class,
					IconType.class, SpecVersionType.class, StateVariableType.class

			);
			return context;
		} catch (JAXBException e) {
			throw new ParseException(e);
		}
	}

	public static Object unmarshallInput(InputStream in) throws ParseException {
		try {
			JAXBContext context = getContext();
			javax.xml.bind.Unmarshaller um = context.createUnmarshaller();
			XMLReader reader = XMLReaderFactory.createXMLReader();
			NamespaceFilter inFilter = new NamespaceFilter(null, false);
			inFilter.setParent(reader);
			InputSource is = new InputSource(in);
			SAXSource source = new SAXSource(inFilter, is);
			return um.unmarshal(source);
		} catch (SAXException | JAXBException e) {
			throw new ParseException(e);
		}
	}

	// utility class
	private JAXBUtilities() {
	}
}