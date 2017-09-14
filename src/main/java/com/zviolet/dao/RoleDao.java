package com.zviolet.dao;

import com.zviolet.entity.Role;

public interface RoleDao {

    /**
     * 根据角色id查询角色
     * @return
     * @throws Exception
     */
    Role selectById(Integer id) throws Exception;
}
