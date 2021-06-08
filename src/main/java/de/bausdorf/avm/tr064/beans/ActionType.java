/***********************************************************************************************************************
 *
 * javaAVMTR064 - open source Java TR-06
4 API
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
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>Java-Klasse für actionType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="actionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="argumentList" type="{urn:dslforum-org:service-1-0}argumentListType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actionType", propOrder = {
    "name",
    "argumentList"
})
public class ActionType {

    @XmlElement(required = true)
    protected String name;
    @XmlElementWrapper(name="argumentList")
    @XmlAnyElement (lax = true)
    protected List<ArgumentType> argumentList;

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
     * Ruft den Wert der argumentList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ArgumentListType }
     *     
     */
    public List<ArgumentType> getArgumentList() {
        return argumentList;
    }

    /**
     * Legt den Wert der argumentList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ArgumentListType }
     *     
     */
    public void setArgumentList(List<ArgumentType> value) {
        this.argumentList = value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
	public String toString()
	{
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("name", this.name)
				.append("argumentList", this.argumentList)
				.toString();
	}
}
