package com.zviolet.service;

import com.zviolet.entity.Userlogin;

public interface UserloginService {

    /**
     * 根据用户查询用户记录
     * @param username
     * @return
     * @throws Exception
     */
    Userlogin findByName(String username) throws Exception;

    /**
     * 保存一条用户记录
     * @param userlogin
     * @throws Exception
     */
    void save(Userlogin userlogin) throws Exception;

    /**
     * 根据用户名删除用户记录
     * @param username
     * @throws Exception
     */
    void removeByName(String username) throws Exception;

    /**
     * 根据用户名更新用户记录
     * @param username
     * @throws Exception
     */
    void updateByName(String username) throws Exception;
}
