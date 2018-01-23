/**
 * 
 */
package com.website.magnon.egplus.core.configurations;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;


/**
 * @author karttik.mishra
 *
 */
@Component(immediate = true, service = MagnonConfigService.class)
@Designate(ocd = MagnonConfiguration.class)
public class MagnonConfigServiceImpl implements MagnonConfigService {

	private MagnonConfiguration config;
	
	@Activate
	public void activate(MagnonConfiguration config) {
		this.config = config;
	}
	
	public MagnonConfiguration getMagnonConfiguration(){
		return this.config;
	}	
}
