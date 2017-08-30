package org.appfuse.dao;

import org.appfuse.model.User;

import java.util.List;


public interface UserDAO extends DAO {
    public List getUsers();
    public List getUsersByPage(int beginNo,int pageSize);
    public User getUser(Long userId);
    public void saveUser(User user);
    public void removeUser(Long userId);
}
