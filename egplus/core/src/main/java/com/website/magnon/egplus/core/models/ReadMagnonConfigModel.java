/**
 * 
 */
package com.website.magnon.egplus.core.models;

import org.osgi.annotation.versioning.ConsumerType;

/**
 * @author karttik.mishra
 *
 */
@ConsumerType
public interface ReadMagnonConfigModel {

	public String getMagnonIndiaAddress();

	public String getMagnonIndiaContactNumber();

	public String getMagnonEmployeeName();

	public String getMagnonEmployeeLocation();

}
