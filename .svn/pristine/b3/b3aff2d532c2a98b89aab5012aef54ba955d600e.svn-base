package org.powerstone.acegi.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.dao.UserCache;
import org.acegisecurity.providers.dao.cache.EhCacheBasedUserCache;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.acegi.resource.Resource;
import org.powerstone.acegi.resource.ResourceDetails;
import org.powerstone.acegi.service.AuthenticationService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * AcegiCacheManager 负责初始化缓存
 * 调用 authenticationService 来获取资源和用户实例，并加入UserCache 和 ResourceCache 中
 * 
 * AcegiCacheManager对缓存进行统一管理，以屏蔽其它类对缓存的直接操作
 * 对缓存中的用户和资源进行初始化、增、删、改、清空等操作
 * @author cac
 *
 */
public class AcegiCacheManager implements InitializingBean{
    protected final Log logger = LogFactory.getLog(getClass());
    
    private AuthenticationService authenticationService;
	private UserCache userCache;
	
	private ResourceCache resourceCache;
	
	//rescTypeMapp 映射资源类型对应的资源的一对多关系，以便快速查找。
	//如method类型对应哪些资源实例，url资源类型对应哪些资源实例
	private Map rescTypeMapping;

	//-----constructor using fields
//    public AcegiCacheManager(){
//        
//    }
    
	public void init() {
        logger.info("Initializing AcegiCacheManager");
        Assert.notNull(userCache,"userCache should not be null");
        Assert.notNull(resourceCache,"resourceCache should not be null");
        Assert.notNull(authenticationService,"Authentication Service should not be null");
 
        //初始化缓存
        List users =authenticationService.getUsers();
        for (Iterator iter = users.iterator(); iter.hasNext();) {
            User user = (User) iter.next();
            userCache.putUserInCache(user);
        }
        
        List rescs =authenticationService.getResources();
        for (Iterator iter = rescs.iterator(); iter.hasNext();) {
            Resource resc = (Resource) iter.next();
            resourceCache.putResourceInCache(resc);
        }
        
		// 获取所有的资源,以初始化 rescTypeMapping
		rescTypeMapping = new HashMap();
		List resclist = resourceCache.getAllResources();
		for (Iterator iter = resclist.iterator(); iter.hasNext();) {
			String resString = (String) iter.next();
			ResourceDetails resc = resourceCache.getResourceFromCache(resString);
			List typelist = (List)rescTypeMapping.get(resc.getResType());
			if(typelist==null){
				typelist = new ArrayList();
				rescTypeMapping.put(resc.getResType(), typelist);
			}
			typelist.add(resString);
		}
	}
	
	//-----get from cache methods
	public UserDetails getUser(String username) {
		return userCache.getUserFromCache(username);
	}
	
	public ResourceDetails getResourceFromCache(String resString) {
		return resourceCache.getResourceFromCache(resString);
	}
	
	//-----remove from cache methods
	public void removeUser(String username){
		userCache.removeUserFromCache(username);
	}
	
	public void removeResource(String resString){
		ResourceDetails rd = resourceCache.getResourceFromCache(resString);
		List typelist =(List) rescTypeMapping.get(rd.getResType());
		typelist.remove(resString);
		resourceCache.removeResourceFromCache(resString);
	}
	
	//------add to cache methods
	public void addUser(String username, String password, boolean enabled, boolean accountNonExpired,
		    boolean credentialsNonExpired, boolean accountNonLocked, GrantedAuthority[] authorities){
		User user = new User(username, password, enabled, accountNonExpired,
			    credentialsNonExpired, accountNonLocked, authorities);
		addUser(user);
	}
	
	public void addUser(UserDetails user){
		userCache.putUserInCache(user);
	}
	
	public void addResource(String resString, String resType, GrantedAuthority[] authorities){
		Resource rd = new Resource(resString, resType, authorities);
		addResource(rd);
	}
	
	public void addResource(ResourceDetails rd){
		List typelist = (List)rescTypeMapping.get(rd.getResType());
		if(typelist==null){
			typelist = new ArrayList();
			rescTypeMapping.put(rd.getResType(), typelist);
		}
		typelist.add(rd.getResString());
		resourceCache.putResourceInCache(rd);
	}
	
	//	------renovate cache methods
	public void renovateUser(String orgUsername, String username, String password, boolean enabled, boolean accountNonExpired,
	        boolean credentialsNonExpired, boolean accountNonLocked, GrantedAuthority[] authorities){
		removeUser(orgUsername);
		addUser(username, password, enabled, accountNonExpired,
			    credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public void renovateUser(String orgUsername, UserDetails user){
		removeUser(orgUsername);
		addUser(user);
	}
	
	public void renovateResource(String orgResString,String resString, String resType, GrantedAuthority[] authorities ){
		removeResource(orgResString);
		addResource(resString, resType, authorities);
	}
	
	public void renovateResource(String orgResString,ResourceDetails rd){
		removeResource(orgResString);
		addResource(rd);
	}
	
	//-------getters and setters-------------------
	public void clearResources() {
		rescTypeMapping = new HashMap();
		resourceCache.removeAllResources();
	}

	public void setResourceCache(ResourceCache resourceCache) {
		this.resourceCache = resourceCache;
	}

	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}
	

	/**
	 * 根据资源类型,在rescTypeMapping职工获取所有该类型资源的对应的resource string
	 * @param resType
	 * @return List
	 */
	public List getResourcesByType(String resType) {	
		return (List)rescTypeMapping.get(resType);
	}
	
	/**
	 * 获取所有资源的对应的resource string
	 * @return
	 */
	public List getAllResources(){
		return resourceCache.getAllResources();
	}
	
	/**
	 * 获取所有用户实例对应的user name
	 * @return
	 */
	public List getAllUsers(){
		EhCacheBasedUserCache ehUserCache = (EhCacheBasedUserCache)userCache;
		return ehUserCache.getCache().getKeys();
	}

    public void afterPropertiesSet() throws Exception {
        init();
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
	
}
