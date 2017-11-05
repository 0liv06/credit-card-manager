package com.github.olivier.ccmanager.repository.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.olivier.ccmanager.entity.user.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    /**
     * Allows to find a user by its username
     * @param login
     * @return
     */
    public List<User> findByUsername(String username);
}
