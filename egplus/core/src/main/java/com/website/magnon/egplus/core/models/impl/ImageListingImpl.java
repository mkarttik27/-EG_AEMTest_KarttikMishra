
package com.website.magnon.egplus.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.tagging.TagManager;
import com.website.magnon.egplus.core.models.ImageListingModel;
import com.website.magnon.egplus.core.services.ImageListingService;

/**
 * @author karttik.mishra
 *
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class },
		adapters = ImageListingModel.class, resourceType = ImageListingImpl.RESOURCE_TYPE,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json")
public class ImageListingImpl implements ImageListingModel {

	protected static final String	RESOURCE_TYPE	= "magnonegplus/components/content/imagelisting";

	@ValueMapValue
	private String[]					tags;

	@ValueMapValue
	private boolean					needAllTags;

	@OSGiService
	ImageListingService				imageListingService;

	@Self
	SlingHttpServletRequest			request;

	@Override
	public List<String> getNewTaggedImages() {
		TagManager tagMgr = request.getResourceResolver().adaptTo(TagManager.class);

		for (int i = 0; i < tags.length; i++) {
			tags[i] = tagMgr.resolve(tags[i]).getPath();
		}
		return imageListingService.getTaggedImages(needAllTags, tags);
	}


}
