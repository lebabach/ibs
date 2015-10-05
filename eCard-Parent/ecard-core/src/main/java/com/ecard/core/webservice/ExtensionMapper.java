
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

package com.ecard.core.webservice;

/**
 * ExtensionMapper class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class ExtensionMapper {

	public static java.lang.Object getTypeObject(java.lang.String namespaceURI, java.lang.String typeName,
			javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {

		if ("http://tempuri.org/".equals(namespaceURI) && "Status".equals(typeName)) {

			return Status.Factory.parse(reader);

		}

		if ("http://tempuri.org/".equals(namespaceURI) && "CardInfoData".equals(typeName)) {

			return CardInfoData.Factory.parse(reader);

		}

		if ("http://tempuri.org/".equals(namespaceURI) && "CardDetail".equals(typeName)) {

			return CardDetail.Factory.parse(reader);

		}

		throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
	}

}
