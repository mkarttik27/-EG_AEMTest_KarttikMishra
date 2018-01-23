/**
 * 
 */
package com.website.magnon.egplus.core.services;

import java.util.List;

/**
 * @author karttik.mishra
 *
 */
public interface ImageListingService {

	public List<String> getTaggedImages(final boolean taggedWithAllTags, final String... tags);
}
