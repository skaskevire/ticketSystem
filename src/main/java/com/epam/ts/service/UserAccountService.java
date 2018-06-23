package com.epam.ts.service;

import com.epam.ts.dao.JDBCUserAccountDao;
import com.epam.ts.entity.User;
import com.epam.ts.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserAccountService {
    @Autowired
    JDBCUserAccountDao jdbcUserAccountDao;

    public void saveUserAccount(UserAccount userAccount)
    {
        jdbcUserAccountDao.saveUserAccount(userAccount);
    }


    public void updatePrepaidAmount(UserAccount userAccount)
    {
        jdbcUserAccountDao.updatePrepaidAmount(userAccount);
    }
}
