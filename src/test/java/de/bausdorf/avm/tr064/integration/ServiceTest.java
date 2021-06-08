package de.bausdorf.avm.tr064.integration;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bausdorf.avm.tr064.Action;
import de.bausdorf.avm.tr064.FritzConnection;
import de.bausdorf.avm.tr064.Response;
import de.bausdorf.avm.tr064.Service;

public class ServiceTest {
	private static final String FB_HOST_PROP = "fritzbox.ip";
	private static final String FB_USER_PROP = "fritzbox.user";
	private static final String FB_PASS_PROP = "fritzbox.password";

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private String ip = null;
	private String user = null;
	private String password = null;
	FritzConnection fc = null;

	@Before
	public void setUp() throws Exception {
		this.ip = System.getProperty(FB_HOST_PROP);
		this.user = System.getProperty(FB_USER_PROP);
		this.password = System.getProperty(FB_PASS_PROP);
		if ((ip != null) && (user != null) && (password != null)) {
			log.info("test connection to host " + this.ip + " (" + this.user + "/" + this.password + ")");
			this.fc = new FritzConnection(ip, user, password);
			try {
				// The connection has to be initiated. This will load the
				// tr64desc.xml respectively igddesc.xml
				// and all the defined Services and Actions.
				log.info("initialize connection ...");
				fc.init(null);
				log.info("... connection initialized");
			} catch (IOException e) {
				// Any HTTP related error.
				log.error(e.getMessage(), e);
			}
		}

	}

	@Test
	public void testGetDeviceLog() {
		if (ip != null) {

			// Get the Service. In this case DeviceInfo:1
			Service service = fc.getService("DeviceInfo:1");

			// Get the Action. in this case GetTotalAssociations
			Action action = service.getAction("GetDeviceLog");
			Response response1 = null;
			try {
				// Execute the action without any In-Parameter.
				response1 = action.execute();
				if (response1 == null) {
					Assert.fail("null response from device");
				}
				String fbLog = response1.getValueAsString("NewDeviceLog");
				log.info("\n" + fbLog + "\n");
			} catch (UnsupportedOperationException | IOException | NoSuchFieldException e) {
				log.error(e.getMessage(), e);
				Assert.fail(e.getMessage());
			}
		} else {
			log.warn("no host ip given, test skipped");

		}
	}

}
