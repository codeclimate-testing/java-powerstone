package org.powerstone.acegi.cache;

import java.util.List;

import org.powerstone.acegi.resource.ResourceDetails;

/**
 * 为 {@link org.springside.components.acegi.resource.Resource} 实例提供缓存.
 * @author cac
 *
 */
public interface ResourceCache {

	public ResourceDetails getResourceFromCache(String resString);
	
	public void putResourceInCache(ResourceDetails resourceDetails);
	
	public void removeResourceFromCache(String resString);
	
	public List getAllResources();

	public void removeAllResources();
	
}