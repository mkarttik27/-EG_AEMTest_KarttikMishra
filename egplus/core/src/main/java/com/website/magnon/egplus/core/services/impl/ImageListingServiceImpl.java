/**
 * 
 */
package com.website.magnon.egplus.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.RangeIterator;
import com.day.cq.tagging.TagManager;
import com.website.magnon.egplus.core.services.ImageListingService;

/**
 * @author karttik.mishra
 *
 */
@Component(immediate = true, service = ImageListingService.class)
public class ImageListingServiceImpl implements ImageListingService {

	/** The Constant LOGGER. */
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ImageListingServiceImpl.class);


	private ResourceResolver		resourceResolver;

	private static final String	DAM_PATH	= "/content/dam/";

	public List<String> getTaggedImages(final boolean taggedWithAllTags, final String... tags) {
		System.out.println("No. of tags received are " + tags.length);
		try {
			getResourceResolver();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> imagesList = new ArrayList<String>();
		TagManager tagMgr = resourceResolver.adaptTo(TagManager.class);
		RangeIterator<Resource> taggedResources = null;
		if (taggedWithAllTags) {
			taggedResources = tagMgr.find(DAM_PATH, tags);
		}
		if (!taggedWithAllTags) {
			taggedResources = tagMgr.find(DAM_PATH, tags, true);
		}
		if (taggedResources != null) {
			while (taggedResources.hasNext()) {
				Resource taggedResource = taggedResources.next();
				Resource imageResource = taggedResource.getParent().getParent();
				if (imageResource.getValueMap().get("jcr:primaryType").equals("dam:Asset")) {
					imagesList.add(imageResource.getPath());
				}
			}
		}
		return imagesList;
	}


	/**
	 * Gets the resource resolver.
	 *
	 * @return the resource resolver
	 * @throws Exception
	 */
	private void getResourceResolver() throws Exception {
		BundleContext bundleContext = FrameworkUtil.getBundle(ImageListingServiceImpl.class)
				.getBundleContext();
		ServiceReference factoryRef = bundleContext
				.getServiceReference(ResourceResolverFactory.class.getName());
		ResourceResolverFactory resourceresolverFactory = (ResourceResolverFactory) bundleContext
				.getService(factoryRef);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "magnonReadUser");
		try {
			this.resourceResolver = resourceresolverFactory.getServiceResourceResolver(param);

			if (resourceResolver == null) {
				throw new Exception("ResourceResolver couldn't be retrieved");
			}
		} catch (org.apache.sling.api.resource.LoginException e) {
			LOGGER.error("LoginException occurred in getResourceResolver method" + e);
			throw new LoginException(e.getMessage());
		}
	}

}
