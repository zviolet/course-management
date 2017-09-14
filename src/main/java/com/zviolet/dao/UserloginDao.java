package com.zviolet.dao;

import com.zviolet.entity.Userlogin;

public interface UserloginDao {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     * @throws Exception
     */
    Userlogin selectByName(String username) throws Exception;

    /**
     * 插入一条用户记录
     * @param record
     * @return
     * @throws Exception
     */
    int insert(Userlogin record) throws Exception;

    /**
     * 根据用户名删除用户记录
     * @param username
     * @return
     * @throws Exception
     */
    int deleteByName(String username) throws Exception;

    /**
     * 根据用户名更新用户记录
     * @param username
     * @return
     * @throws Exception
     */
    int updateByName(String username) throws Exception;
}
