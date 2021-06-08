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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java-Klasse für deviceType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="deviceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="deviceType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="friendlyName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="manufacturerURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="modelDescription" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="modelName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="modelNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="modelURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="udn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="upc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="iconList" type="{urn:dslforum-org:device-1-0}iconListType" minOccurs="0"/&gt;
 *         &lt;element name="serviceList" type="{urn:dslforum-org:device-1-0}serviceListType"/&gt;
 *         &lt;element name="deviceList" type="{urn:dslforum-org:device-1-0}deviceListType" minOccurs="0"/&gt;
 *         &lt;element name="presentationURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement (name="device")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="device", 
	propOrder = {
    "deviceType",
    "friendlyName",
    "manufacturer",
    "manufacturerURL",
    "modelDescription",
    "modelName",
    "modelNumber",
    "modelURL",
    "udn",
    "upc",
    "iconList",
    "serviceList",
    "deviceList",
    "presentationURL"
})
public class DeviceDesc {

    @XmlElement(required = true)
    private String deviceType;
    @XmlElement(required = true)
    private String friendlyName;
    @XmlElement(required = true)
    private String manufacturer;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    private String manufacturerURL;
    @XmlElement(required = true)
    private String modelDescription;
    @XmlElement(required = true)
    private String modelName;
    @XmlElement(required = true)
    private String modelNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    private String modelURL;
    @XmlElement(name = "UDN", required = true)
    private String udn;
    @XmlElement(name = "UPC")
    private String upc;

    @XmlElementWrapper(name="iconList")
    @XmlAnyElement (lax = true)
    private List<IconType> iconList;

    @XmlElementWrapper(name="serviceList", required = true)
    @XmlAnyElement (lax = true)
    private List<ServiceDesc> serviceList;
    
    @XmlElementWrapper(name="deviceList")
    @XmlAnyElement (lax = true)
    private List<DeviceDesc> deviceList;
    
    @XmlSchemaType(name = "anyURI")
    private String presentationURL;

    /**
     * Ruft den Wert der deviceType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * Legt den Wert der deviceType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceType(String value) {
        this.deviceType = value;
    }

    /**
     * Ruft den Wert der friendlyName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * Legt den Wert der friendlyName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFriendlyName(String value) {
        this.friendlyName = value;
    }

    /**
     * Ruft den Wert der manufacturer-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Legt den Wert der manufacturer-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    /**
     * Ruft den Wert der manufacturerURL-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturerURL() {
        return manufacturerURL;
    }

    /**
     * Legt den Wert der manufacturerURL-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturerURL(String value) {
        this.manufacturerURL = value;
    }

    /**
     * Ruft den Wert der modelDescription-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelDescription() {
        return modelDescription;
    }

    /**
     * Legt den Wert der modelDescription-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelDescription(String value) {
        this.modelDescription = value;
    }

    /**
     * Ruft den Wert der modelName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Legt den Wert der modelName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelName(String value) {
        this.modelName = value;
    }

    /**
     * Ruft den Wert der modelNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * Legt den Wert der modelNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelNumber(String value) {
        this.modelNumber = value;
    }

    /**
     * Ruft den Wert der modelURL-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelURL() {
        return modelURL;
    }

    /**
     * Legt den Wert der modelURL-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelURL(String value) {
        this.modelURL = value;
    }

    /**
     * Ruft den Wert der udn-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdn() {
        return udn;
    }

    /**
     * Legt den Wert der udn-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdn(String value) {
        this.udn = value;
    }

    /**
     * Ruft den Wert der upc-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPC() {
        return upc;
    }

    /**
     * Legt den Wert der upc-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPC(String value) {
        this.upc = value;
    }

    /**
     * Ruft den Wert der iconList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link IconListType }
     *     
     */
    public List<IconType> getIconList() {
        return iconList;
    }

    /**
     * Legt den Wert der iconList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link IconListType }
     *     
     */
    public void setIconList(List<IconType> value) {
        this.iconList = value;
    }

    /**
     * Ruft den Wert der serviceList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ServiceListType }
     *     
     */
    public List<ServiceDesc> getServiceList() {
        return serviceList;
    }

    /**
     * Legt den Wert der serviceList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceListType }
     *     
     */
    public void setServiceList(List<ServiceDesc> value) {
        this.serviceList = value;
    }

    /**
     * Ruft den Wert der deviceList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DeviceListType }
     *     
     */
    public List<DeviceDesc> getDeviceList() {
        return deviceList;
    }

    /**
     * Legt den Wert der deviceList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceListType }
     *     
     */
    public void setDeviceList(List<DeviceDesc> value) {
        this.deviceList = value;
    }

    /**
     * Ruft den Wert der presentationURL-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresentationURL() {
        return presentationURL;
    }

    /**
     * Legt den Wert der presentationURL-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresentationURL(String value) {
        this.presentationURL = value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("deviceType", this.deviceType)
				.append("friendlyName", this.friendlyName)
				.append("manufacturer", this.manufacturer)
				.append("modelName", this.modelName)
				.append("modelNumber", this.modelNumber)
				.append("modelDescription", this.modelDescription)
				.append("udn", this.udn)
				.append("upc", this.upc)
				.append(this.serviceList)
				.append(this.deviceList)
				.toString();
	}

}
