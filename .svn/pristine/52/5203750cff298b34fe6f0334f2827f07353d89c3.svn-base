package org.appfuse.service;

import org.appfuse.dao.UserDAO;
import org.appfuse.model.User;

import java.util.List;

public interface UserManager {
    public void setUserDAO(UserDAO dao);
    public List getUsers();
    public List getUsersByPage(int beginNo,int pageSize);
    public User getUser(String userId);
    public void saveUser(User user);
    public void removeUser(String userId);
}
