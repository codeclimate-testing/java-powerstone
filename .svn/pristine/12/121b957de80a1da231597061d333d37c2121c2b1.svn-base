package org.appfuse.service.impl;

import org.appfuse.dao.UserDAO;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;

import java.util.List;


public class UserManagerImpl implements UserManager {
    private UserDAO dao;

    public void setUserDAO(UserDAO dao) {
        this.dao = dao;
    }

    public List getUsers() {
        return dao.getUsers();
    }
    
    public List getUsersByPage(int beginNo,int pageSize){
    	return dao.getUsersByPage(beginNo,pageSize);
    }

    public User getUser(String userId) {
        return dao.getUser(Long.valueOf(userId));
    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }

    public void removeUser(String userId) {
        dao.removeUser(Long.valueOf(userId));
    }
}
