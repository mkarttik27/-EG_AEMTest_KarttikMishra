
package com.website.magnon.egplus.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.website.magnon.egplus.core.configurations.MagnonConfigService;
import com.website.magnon.egplus.core.models.ReadMagnonConfigModel;

/**
 * @author karttik.mishra
 *
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class },
		adapters = ReadMagnonConfigModel.class, resourceType = ReadMagnonConfigImpl.RESOURCE_TYPE,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json")
public class ReadMagnonConfigImpl implements ReadMagnonConfigModel {

	protected static final String	RESOURCE_TYPE	= "magnonegplus/components/content/readmagnonconfig";

	@ValueMapValue
	private String						articleText;

	@OSGiService
	private MagnonConfigService	magnonConfigService;

	public String getSummaryText() {
		return articleText.substring(0, 30).concat("...");
	}

	@Override
	public String getMagnonIndiaAddress() {
		return this.magnonConfigService.getMagnonConfiguration().magnonIndiaAddress();
	}

	@Override
	public String getMagnonIndiaContactNumber() {
		return this.magnonConfigService.getMagnonConfiguration().magnonIndiaContactNumber();
	}

	@Override
	public String getMagnonEmployeeName() {
		return this.magnonConfigService.getMagnonConfiguration().magnonEmployeeName();
	}

	@Override
	public String getMagnonEmployeeLocation() {
		return this.magnonConfigService.getMagnonConfiguration().magnonEmployeeLocation();
	}


}
