package newtime.net.http.resource;

import newtime.net.http.response.resource.Resource;

public interface IResourceManager {

	public abstract Resource get(String key);
	public abstract Resource put(String key, Resource view);
	public abstract Resource create(String key, String path);	
	
}
