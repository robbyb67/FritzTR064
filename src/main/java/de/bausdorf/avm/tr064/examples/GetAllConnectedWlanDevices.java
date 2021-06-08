package de.bausdorf.avm.tr064.examples;
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

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bausdorf.avm.tr064.Action;
import de.bausdorf.avm.tr064.FritzConnection;
import de.bausdorf.avm.tr064.ParseException;
import de.bausdorf.avm.tr064.Response;
import de.bausdorf.avm.tr064.Service;

public class GetAllConnectedWlanDevices {
	private static final Logger LOG = LoggerFactory.getLogger(GetAllConnectedWlanDevices.class);
	static String ip = null;
	static String user = null;
	static String password = null;
	
	private GetAllConnectedWlanDevices() {
		super();
	}

	public static void main(String[] args){
		if( args.length < 2 ) {
			LOG.error("args: <fb-ip> <password> [user]");
			System.exit(1);
		} else {
			ip = args[0];
			password = args[1];
			if( args.length > 2 )
			{
				user = args[2];
			}
		}
			
		//Create a new FritzConnection with username and password
		FritzConnection fc = new FritzConnection(ip,user,password);
		try {
			//The connection has to be initiated. This will load the tr64desc.xml respectively igddesc.xml 
			//and all the defined Services and Actions. 

			fc.init(null);
		} catch (IOException | ParseException e2) {
			//Any Network related error.
			LOG.error(e2.getMessage(), e2);
		}
		
		for (int i = 1; i<=3 ;i++){
			//Get the Service. In this case WLANConfiguration:X 
			Service service = fc.getService("WLANConfiguration:" + i);
			//Get the Action. in this case GetTotalAssociations
			Action action = service.getAction("GetTotalAssociations");
			Response response1 = null;
			try {
				//Execute the action without any In-Parameter.
				response1 = action.execute();
			} catch (UnsupportedOperationException | IOException e1) {
				LOG.error(e1.getMessage(), e1);
			}
			int deviceCount = -1;
			try {
				//Get the value from the field NewTotalAssociations as an integer. Values can have the Types: String, Integer, Boolean, DateTime and UUID
				if (response1 != null) {
					deviceCount = response1.getValueAsInteger("NewTotalAssociations");
				}
			} catch (ClassCastException | NoSuchFieldException e) {
				LOG.error(e.getMessage(), e);
			}
			LOG.info("WLAN " + i + ":" + deviceCount);
			for (int j = 0; j < deviceCount; j++){
				//Create a map for the arguments of an action. You have to do this, if the action has IN-Parameters.
				HashMap<String, Object> arguments = new HashMap<>();
				//Set the argument NewAssociatedDeviceIndex to an integer value.
				arguments.put("NewAssociatedDeviceIndex", j);
				try {
					Response response2 = fc.getService("WLANConfiguration:" + i).getAction("GetGenericAssociatedDeviceInfo").execute(arguments);
					LOG.info("    " + response2.getData());
				} catch (UnsupportedOperationException | IOException e) {
					LOG.error(e.getMessage(), e);
				}
				
			}
			
		}
			
		
		
		
	}
}
