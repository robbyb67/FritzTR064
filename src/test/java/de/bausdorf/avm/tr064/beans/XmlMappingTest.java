package de.bausdorf.avm.tr064.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.sax.SAXSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import de.bausdorf.avm.tr064.JAXBUtilities;
import de.bausdorf.avm.tr064.NamespaceFilter;
import de.bausdorf.avm.tr064.ParseException;

public class XmlMappingTest {
	private static final String ROOT_TEST_XML = "rootTest.xml";
	private static final String SCPD_TEST_XML = "scpdTest.xml";
	private static final String IGD_DESC_TEST_XML = "igd_desc_test.xml";
	private static final String ANY_TEST_XML = "any_test.xml";
	private static final String IGDICFG_SCPD_TEST_XML = "igdicfgSCPD_test.xml";
	private static final Logger LOG = LoggerFactory.getLogger(XmlMappingTest.class);

	private Object unmarshallInput(InputStream in) throws ParseException {
		JAXBContext context = JAXBUtilities.getContext();

		try {
			javax.xml.bind.Unmarshaller um = context.createUnmarshaller();

			XMLReader reader = XMLReaderFactory.createXMLReader();
			NamespaceFilter inFilter = new NamespaceFilter(null, false);
			inFilter.setParent(reader);
			InputSource is = new InputSource(in);
			SAXSource source = new SAXSource(inFilter, is);

			um.setEventHandler(new ValidationEventHandler() {

				@Override
				public boolean handleEvent(ValidationEvent event) {
					LOG.error("ERROR {}", event);
					fail("cant unmarshal given input");
					return true;
				}
			});
			return um.unmarshal(source);
		} catch (JAXBException | SAXException e) {
			throw new ParseException(e);
		}

	}

	@Test
	public void testRootMapping() {

		try {
			LOG.info("testing " + ROOT_TEST_XML);

			InputStream xml = ClassLoader.getSystemResourceAsStream(ROOT_TEST_XML);
			LOG.info("xml {}", xml);

			RootType root = (RootType) unmarshallInput(xml);

			assertEquals(root.getSpecVersion().getMajor(), 1);
			assertEquals(root.getDevice().getServiceList().size(), 15);

			for (Object s : root.getDevice().getServiceList()) {
				LOG.trace(" service {}", s);
				if (!(s instanceof ServiceDesc)) {
					fail("service type was not mapped correctly");

				}
			}

		} catch (ParseException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void testIgdDescMapping() {
		InputStream igdDescIS;

		try {
			LOG.info("testing " + IGD_DESC_TEST_XML);
			igdDescIS = ClassLoader.getSystemResourceAsStream(IGD_DESC_TEST_XML);

			RootType root = (RootType) unmarshallInput(igdDescIS);

			assertEquals(root.getSpecVersion().getMajor(), 1);
			assertEquals(root.getDevice().getServiceList().size(), 1);

			LOG.info("{}", root.toString());

		} catch (ParseException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void testAnyMapping() {
		InputStream anyIS;
		try {
			LOG.info("testing " + ANY_TEST_XML);
			anyIS = ClassLoader.getSystemResourceAsStream(ANY_TEST_XML);
			ScpdType root = (ScpdType) unmarshallInput(anyIS);
			assertEquals(root.getServiceStateTable().size(), 1);


		} catch (ParseException e) {
			fail(e.getLocalizedMessage());
		}

	}

	@Test
	public void testScpdMapping() {
		InputStream anyIS;
		try {
			LOG.info("testing " + SCPD_TEST_XML);
			anyIS = ClassLoader.getSystemResourceAsStream(SCPD_TEST_XML);
			ScpdType scpd = (ScpdType) unmarshallInput(anyIS);

			for (Object o : scpd.getServiceStateTable()) {
				if (!(o instanceof StateVariableType)) {
					fail("parser error, type StateVariableType was not resolved");
				}
			}


		} catch (ParseException e) {
			fail(e.getLocalizedMessage());
		}

	}

	@Test
	public void testIgdicfgSCPDMapping() {
		InputStream igdicfgIS;
		try {
			LOG.info("testing " + IGDICFG_SCPD_TEST_XML);
			igdicfgIS = ClassLoader.getSystemResourceAsStream(IGDICFG_SCPD_TEST_XML);
			ScpdType root = (ScpdType) unmarshallInput(igdicfgIS);
			assertEquals(root.getActionList().size(), 6);


		} catch (ParseException e) {
			fail(e.getLocalizedMessage());
		}
	}

}
