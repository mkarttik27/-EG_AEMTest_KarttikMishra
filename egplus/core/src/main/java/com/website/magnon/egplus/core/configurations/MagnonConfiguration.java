package com.website.magnon.egplus.core.configurations;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * @author karttik.mishra
 *
 */

@ObjectClassDefinition(name = "Magnon Eg Plus Configuration", description = "Magnon EG Plus Configuration")
public @interface MagnonConfiguration {
	
	@AttributeDefinition(name = "Magnon Address", description = "Magnon Eg Plus India Office Address")
	String magnonIndiaAddress();
	
	@AttributeDefinition(name = "Magnon Contact", description = "Magnon Eg Plus India Office Contact Number")
	String magnonIndiaContactNumber();
	
	@AttributeDefinition(name = "Magnon Employee", description = "Magnon Eg Plus Employee Name")
	String magnonEmployeeName();
	
	@AttributeDefinition(name = "Magnon Employee Location", description = "Magnon Eg Plus Employee Location")
	String magnonEmployeeLocation();
}