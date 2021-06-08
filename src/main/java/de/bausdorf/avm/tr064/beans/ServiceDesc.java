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

package de.bausdorf.avm.tr064.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java-Klasse für serviceType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="serviceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serviceId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="controlURL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="eventSubURL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SCPDURL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement (name="service")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"serviceType",
		"serviceId",
		"controlURL",
		"eventSubURL",
		"scpdurl"
})
public class ServiceDesc
{
	
	@XmlElement(name = "serviceType", required = true)
	protected String serviceType;
	@XmlElement(required = true)
	protected String serviceId;
	@XmlElement(required = true)
	protected String controlURL;
	@XmlElement(required = true)
	protected String eventSubURL;
	@XmlElement(name = "SCPDURL", required = true)
	protected String scpdurl;
	
	/**
	 * @return the serviceType
	 */
	public String getServiceType()
	{
		return serviceType;
	}
	
	/**
	 * @return the serviceId
	 */
	public String getServiceId()
	{
		return serviceId;
	}
	
	/**
	 * @return the controlURL
	 */
	public String getControlURL()
	{
		return controlURL;
	}
	
	/**
	 * @return the eventSubURL
	 */
	public String getEventSubURL()
	{
		return eventSubURL;
	}
	
	/**
	 * @return the scpdurl
	 */
	public String getScpdurl()
	{
		return scpdurl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("serviceId", this.getServiceId())
				.append("serviceType", this.getServiceType())
				.append("controlURL", this.getControlURL())
				.append("eventSubURL", this.getEventSubURL())
				.append("scpdurl", this.getScpdurl())
				.toString();
	}
}
