package com.github.olivier.ccmanager.service.user;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.olivier.ccmanager.entity.user.PersistUserStatus;
import com.github.olivier.ccmanager.entity.user.User;
import com.github.olivier.ccmanager.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserByLogin(String login) {

	User result = null;

	final List<User> userList = userRepository.findByUsername(login);

	if (CollectionUtils.isNotEmpty(userList)) {

	    result = userList.get(0);
	}

	return result;
    }

    @Override
    public PersistUserStatus saveUser(String username, String password) {

	return saveUser(username, password, "USER");
    }

    @Override
    public PersistUserStatus saveUser(String username, String password, boolean admin) {

	final String role = (admin ? "ADMIN" : "USER");

	return saveUser(username, password, role);
    }

    private PersistUserStatus saveUser(String username, String password, String userRole) {

	PersistUserStatus result = null;

	final User existingUser = getUserByLogin(username);

	if (existingUser == null) {

	    User user = new User();

	    user.setUsername(username);
	    user.setPassword(passwordEncoder.encode(password));
	    user.setRole(userRole);
	    user.setEnabled(Boolean.TRUE);

	    userRepository.save(user);
	    
	    result = PersistUserStatus.SUCCESS;
	    
	} else {

	    logger.error("A user with this username was found");
	    result = PersistUserStatus.FAILURE;
	}

	return result;
    }
}
