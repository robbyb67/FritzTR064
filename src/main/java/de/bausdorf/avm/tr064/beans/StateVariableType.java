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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>Java-Klasse f�r stateVariableType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="stateVariableType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="sendEvents" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement (name = "stateVariable")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stateVariableType", propOrder = {
    "name",
    "dataType",
    "allowedValueList",
    "allowedValueRange",
    "defaultValue"
})
public class StateVariableType {

    @XmlAttribute(name = "sendEvents")
    protected String sendEvents;
    @XmlElement(name="name", required = true)
    protected String name;
    @XmlElement(required = true)
    protected String dataType;
    @XmlElementWrapper(name="allowedValueList")
    @XmlAnyElement (lax = true)
    private List<String> allowedValueList;
    private AllowedValueRangeType allowedValueRange;
    private String defaultValue;
    
    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der dataType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Legt den Wert der dataType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataType(String value) {
        this.dataType = value;
    }

    /**
     * Ruft den Wert der sendEvents-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendEvents() {
        return sendEvents;
    }

    /**
     * Legt den Wert der sendEvents-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendEvents(String value) {
        this.sendEvents = value;
    }

	/**
	 * @return the allowedValueList
	 */
	public List<String> getAllowedValueList()
	{
		if( this.allowedValueList == null ) {
			this.allowedValueList = new ArrayList<String>();
		}
		return allowedValueList;
	}

	/**
	 * @param allowedValueList the allowedValueList to set
	 */
	public void setAllowedValueList(List<String> allowedValueList)
	{
		this.allowedValueList = allowedValueList;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue()
	{
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the allowedValueRange
	 */
	public AllowedValueRangeType getAllowedValueRange()
	{
		return allowedValueRange;
	}

	/**
	 * @param allowedValueRange the allowedValueRange to set
	 */
	public void setAllowedValueRange(AllowedValueRangeType allowedValueRange)
	{
		this.allowedValueRange = allowedValueRange;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("name", this.name)
				.append("dataType", this.dataType)
				.append("sendEvents", this.sendEvents)
				.append("allowedValueList", this.allowedValueList)
				.append("allowedValueRange", this.allowedValueRange)
				.append("defaultValue", this.defaultValue)
				.toString();
	}

}
