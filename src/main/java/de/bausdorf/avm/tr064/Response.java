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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

public class Response {

	private SOAPMessage response;
	private Map<String, Class<?>> stateToType;
	private Map<String, String> argumentState;
	private Map<String, String> data;

	public Response(SOAPMessage response, Map<String, Class<?>> stateToType, Map<String, String> argumentState)
			throws SOAPException {
		this.response = response;
		this.stateToType = stateToType;
		this.argumentState = argumentState;
		this.data = new HashMap<>();

		NodeList nodes = null;
		NodeList tmp = null;
		try {
			tmp = response.getSOAPBody().getChildNodes();
			for (int i = 1; i < tmp.getLength() && nodes == null; i++) {
				if ("#text".equals(tmp.item(i).getNodeName()))
					continue;
				nodes = tmp.item(i).getChildNodes();
			}
		} catch (SOAPException e) {
			throw e;
		}

		if (nodes != null) {
			for (int i = 1; i < nodes.getLength(); i++) {
				if ("#text".equals(nodes.item(i).getNodeName()))
					continue;
				data.put(nodes.item(i).getNodeName(), nodes.item(i).getTextContent());
			}
		}
	}

	public SOAPMessage getSOAPMessage() {
		return response;
	}
	
	public Map<String, String> getData() {
		return data;
	}

	public String getValueAsString(String argument) throws NoSuchFieldException {
		if (!argumentState.containsKey(argument) || !data.containsKey(argument))
			throw new NoSuchFieldException(argument);
		return data.get(argument);
	}

	public int getValueAsInteger(String argument) throws ClassCastException, NoSuchFieldException {
		if (!argumentState.containsKey(argument) || !data.containsKey(argument))
			throw new NoSuchFieldException(argument);
		if (stateToType.get(argumentState.get(argument)) != Integer.class)
			throw new ClassCastException(argument);

		int ret = -1;

		try {
			ret = Integer.parseInt(data.get(argument));
		} catch (NumberFormatException e) {
			throw new ClassCastException(argument + " " + e.getMessage());
		}

		return ret;
	}

	public boolean getValueAsBoolean(String argument) throws ClassCastException, NoSuchFieldException {
		if (!argumentState.containsKey(argument) || !data.containsKey(argument))
			throw new NoSuchFieldException(argument);
		if (stateToType.get(argumentState.get(argument)) != Boolean.class)
			throw new ClassCastException(argument);

		boolean ret;

		if ("1".equals(data.get(argument)) || "true".equalsIgnoreCase(data.get(argument)))
			ret = true;
		else if ("0".equals(data.get(argument)) || "false".equalsIgnoreCase(data.get(argument)))
			ret = false;
		else
			throw new ClassCastException(argument);

		return ret;
	}

	public Date getValueAsDate(String argument) throws ClassCastException, NoSuchFieldException {
		if (!argumentState.containsKey(argument) || !data.containsKey(argument))
			throw new NoSuchFieldException(argument);
		if (stateToType.get(argumentState.get(argument)) != Date.class)
			throw new ClassCastException(argument);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		Date ret = null;

		try {
			ret = dateFormat.parse(data.get(argument));
		} catch (ParseException e) {
			throw new ClassCastException(argument + " " + e.getMessage());
		}

		return ret;
	}

	public UUID getValueAsUUID(String argument) throws NoSuchFieldException {
		if (!argumentState.containsKey(argument) || !data.containsKey(argument))
			throw new NoSuchFieldException(argument);
		if (stateToType.get(argumentState.get(argument)) != UUID.class)
			throw new ClassCastException(argument);
		
		return UUID.fromString(data.get(argument));
	}

}
