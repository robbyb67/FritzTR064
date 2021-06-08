/***********************************************************************************************************************
 *
 * javaAVMTR064 - open source Java TR-064 API
 *===========================================
 *
 * Copyright 2015 Marin Pollmann <pollmann.m@gmail.com>
 * 
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/
package de.bausdorf.avm.tr064;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bausdorf.avm.tr064.beans.ActionType;
import de.bausdorf.avm.tr064.beans.ScpdType;
import de.bausdorf.avm.tr064.beans.ServiceDesc;

public class Service {
	private static final Logger LOG = LoggerFactory.getLogger(FritzConnection.class);

	private ServiceDesc serviceXML;
	private Map<String, Action> actions;

	public Service(ServiceDesc serviceXML, FritzConnection connection) throws IOException, ParseException {
		this.serviceXML = serviceXML;
		actions = new HashMap<String, Action>();

		try (InputStream is = connection.getXMLIS(serviceXML.getScpdurl())) {

			ScpdType scpd = (ScpdType) JAXBUtilities.unmarshallInput(is);

			LOG.debug(scpd.toString());
			for (ActionType a : scpd.getActionList()) {
				actions.put(a.getName(), new Action(a, scpd.getServiceStateTable(), connection, this.serviceXML));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			InputStream is = connection.getXMLIS(serviceXML.getScpdurl());
			ScpdType scpd = (ScpdType) JAXBUtilities.unmarshallInput(is);

			LOG.debug("scpd {}", scpd.toString());
			for (ActionType a : scpd.getActionList()) {
				actions.put(a.getName(), new Action(a, scpd.getServiceStateTable(), connection, this.serviceXML));
			}
		}

	}

	public Map<String, Action> getActions() {
		return actions;
	}

	public Action getAction(String name) {
		return getActions().get(name);
	}
	@Override
	public String toString(){
		return serviceXML.getServiceType();
	}
}
