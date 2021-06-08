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

/**
 * <p>Java-Klasse für rootType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="rootType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="specVersion" type="{urn:dslforum-org:device-1-0}specVersionType"/&gt;
 *         &lt;element name="device" type="{urn:dslforum-org:device-1-0}deviceType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement ( name="root")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rootType", propOrder = {
"specVersion",
"device"
})
public class RootType {
	
   @XmlElement(name="specVersion", required = true)
    private SpecVersionType specVersion;
    @XmlElement(name="device", required = true)
    private DeviceDesc device;

    /**
     * Ruft den Wert der specVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SpecVersionType }
     *     
     */
    public SpecVersionType getSpecVersion() {
        return specVersion;
    }

    /**
     * Legt den Wert der specVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SpecVersionType }
     *     
     */
    public void setSpecVersion(SpecVersionType value) {
        this.specVersion = value;
    }

    /**
     * Ruft den Wert der device-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DeviceDesc }
     *     
     */
    public DeviceDesc getDevice() {
        return device;
    }

    /**
     * Legt den Wert der device-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceDesc }
     *     
     */
    public void setDevice(DeviceDesc value) {
        this.device = value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder(this)
				.append(this.getSpecVersion())
				.append(this.getDevice())
				.toString();
	}

}
