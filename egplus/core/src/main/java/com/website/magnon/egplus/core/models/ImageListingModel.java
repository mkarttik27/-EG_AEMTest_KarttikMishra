/**
 * 
 */
package com.website.magnon.egplus.core.models;

import java.util.List;

import org.osgi.annotation.versioning.ConsumerType;

/**
 * @author karttik.mishra
 *
 */
@ConsumerType
public interface ImageListingModel {

	public List<String> getNewTaggedImages();

}
