package com.github.olivier.ccmanager.service.user;

import com.github.olivier.ccmanager.entity.user.PersistUserStatus;
import com.github.olivier.ccmanager.entity.user.User;

/**
 * Interface defining the way to interact with {@link User} Objects
 * @author odonn
 *
 */
public interface UserService {

    /**
     * Persist a user with the default USER role
     * @param username
     * @param password
     */
    public PersistUserStatus saveUser(String username, String password);
    
    /**
     * Persist a user
     * @param username
     * @param password
     * @param admin
     */
    public PersistUserStatus saveUser(String username, String password, boolean admin);
    
    /**
     * Returns the users associated to the given username
     * @param login
     * @return
     */
    public User getUserByLogin(String login);

}
