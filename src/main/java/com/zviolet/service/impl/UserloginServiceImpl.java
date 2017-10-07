package com.zviolet.service.impl;

import com.zviolet.dao.UserloginDao;
import com.zviolet.entity.Userlogin;
import com.zviolet.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserloginServiceImpl implements UserloginService {
    @Autowired
    private UserloginDao userloginDao;

    public Userlogin findByName(String username) throws Exception {
        return userloginDao.selectByName(username);
    }

    public void save(Userlogin userlogin) throws Exception {
        userloginDao.insert(userlogin);
    }

    public void removeByName(String username) throws Exception {
        userloginDao.deleteByName(username);
    }

    public void updateByName(String username) throws Exception {
        userloginDao.updateByName(username);
    }
}
