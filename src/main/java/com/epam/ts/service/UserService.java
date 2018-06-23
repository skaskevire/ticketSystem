package com.epam.ts.service;

import com.epam.ts.dao.JDBCUserDao;
import com.epam.ts.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    JDBCUserDao jdbcUserDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        jdbcUserDao.saveUser(user);
    }

    public User getUserByEmail(String email) {
        return jdbcUserDao.getUserByEmail(email);
    }

    public User getUserByName(String name)
    {
       return jdbcUserDao.getUsers(name).get(0);
    }
}
