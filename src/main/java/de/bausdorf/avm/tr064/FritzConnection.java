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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bausdorf.avm.tr064.beans.DeviceDesc;
import de.bausdorf.avm.tr064.beans.RootType;
import de.bausdorf.avm.tr064.beans.ServiceDesc;

public class FritzConnection {
	private static final Logger LOG = LoggerFactory.getLogger(FritzConnection.class);

	private static final int DEFAULT_PORT = 49000;
	private static final String FRITZ_IGD_DESC_FILE = "igddesc.xml";
	private static final String FRITZ_TR64_DESC_FILE = "tr64desc.xml";

	private Map<String, Service> services;
	private String user = null;
	private String pwd = null;
	private HttpHost targetHost;
	private CloseableHttpClient httpClient;
	private HttpClientContext context;

	private String name;

	FritzConnection(String address, int port) {

		targetHost = new HttpHost(address, port);
		httpClient = HttpClients.createDefault();
		context = HttpClientContext.create();
		services = new HashMap<>();
	}

	public FritzConnection(String address) {
		this(address, DEFAULT_PORT);
	}

	public FritzConnection(String address, int port, String user, String pwd) {
		this(address, port);
		this.user = user;
		this.pwd = pwd;
	}

	public FritzConnection(String address, String user, String pwd) {
		this(address);
		this.user = user;
		this.pwd = pwd;
	}

	public void init(String scpdUrl) throws IOException, ParseException {
		if (user != null && pwd != null) {
			LOG.debug("try to connect to " + this.targetHost.getAddress() + " with credentials " + this.user + "/"
					+ this.pwd);
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, pwd));
			AuthCache authCache = new BasicAuthCache();
			DigestScheme digestScheme = new DigestScheme();
			digestScheme.overrideParamter("realm", "F!Box SOAP-Auth");
			digestScheme.overrideParamter("nonce", Long.toString(new Random().nextLong(), 36));
			digestScheme.overrideParamter("qop", "auth");
			digestScheme.overrideParamter("nc", "0");
			digestScheme.overrideParamter("cnonce", DigestScheme.createCnonce());
			authCache.put(targetHost, digestScheme);
			context.setCredentialsProvider(credsProvider);
			context.setAuthCache(authCache);
			readTR64(scpdUrl);
		} else {
			LOG.debug("read igddesc, because credentials are " + this.user + "/" + this.pwd);
			readIGDDESC();
		}
	}

	private void readTR64(String scpdUrl) throws IOException, ParseException {
		scpdUrl = scpdUrl == null ? FRITZ_TR64_DESC_FILE : scpdUrl;
		InputStream xml = getXMLIS("/" + scpdUrl);

		RootType root = (RootType) JAXBUtilities.unmarshallInput(xml);
		LOG.debug(root.toString());
		DeviceDesc device = root.getDevice();
		name = device.getFriendlyName();
		getServicesFromDevice(device);
	}

	private void readIGDDESC() throws IOException {
		InputStream xml = getXMLIS("/" + FRITZ_IGD_DESC_FILE);
		try {
			RootType root = (RootType) JAXBUtilities.unmarshallInput(xml);
			LOG.debug(root.toString());
			DeviceDesc device = root.getDevice();
			name = device.getFriendlyName();
			getServicesFromDevice(device);
		} catch (ParseException e) {
			LOG.error(e.getLocalizedMessage(), e);
			throw new IOException(e);
		}
	}

	private void getServicesFromDevice(DeviceDesc device) throws IOException, ParseException {

		for (Object sT : device.getServiceList()) {
			LOG.info("Service {} {}", sT, sT.getClass().getName());
		}

		for (ServiceDesc sT : device.getServiceList()) {
			String[] tmp = sT.getServiceType().split(":");
			String key = tmp[tmp.length - 2] + ":" + tmp[tmp.length - 1];
			LOG.debug("adding service " + key + " to inventory");
			services.put(key, new Service(sT, this));
		}
		if (device.getDeviceList() != null)
			for (DeviceDesc d : device.getDeviceList()) {
				getServicesFromDevice(d);
			}
	}

	private InputStream httpRequest(HttpHost target, HttpRequest request, HttpContext context) throws IOException {
		byte[] content = null;
		LOG.debug("try to request " + request.getRequestLine() + " from " + target.toURI());
		try (CloseableHttpResponse response = httpClient.execute(target, request, context)) {
			LOG.debug("got response " + response.getStatusLine());
			content = EntityUtils.toByteArray(response.getEntity());
			EntityUtils.consume(response.getEntity());
			LOG.debug("got content: " + new String(content));
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new IOException(response.getStatusLine().toString());
			}

			if (content != null) {
				return new ByteArrayInputStream(content);
			} else {
				return new ByteArrayInputStream(new byte[0]);
			}
		} catch (IOException e) {
			LOG.error(e.getLocalizedMessage(), e);
			throw e;
		}

	}

	protected InputStream getXMLIS(String fileName) throws IOException {
		HttpGet httpget = new HttpGet(fileName);
		return httpRequest(targetHost, httpget, context);

	}

	protected InputStream getSOAPXMLIS(String fileName, String urn, HttpEntity entity) throws IOException {
		HttpPost httppost = new HttpPost(fileName);
		httppost.addHeader("soapaction", urn);
		httppost.addHeader("charset", "utf-8");
		httppost.addHeader("content-type", "text/xml");
		httppost.setEntity(entity);
		return httpRequest(targetHost, httppost, context);
	}

	public Map<String, Service> getServices() {
		return services;
	}

	public Service getService(String name) {
		return getServices().get(name);
	}

	public void printInfo() {
		this.print(name);
		this.print("----------------------------------");
		for (String a : services.keySet()) {
			this.print(">>> service: " + a);
			Service s = services.get(a);
			for (String b : s.getActions().keySet()) {
				this.print("action: " + b);
				this.print("arguments: " + s.getActions().get(b).getArguments().toString() + "\n");
			}
			this.print("<<< service: " + a + "\n");
		}
	}

	@SuppressWarnings({ "squid:S106", "squid:CommentedOutCodeLine" })
	private void print(String msg) {
		LOG.info("{}", msg);
	}
}
