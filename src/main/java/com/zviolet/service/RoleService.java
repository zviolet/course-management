package com.zviolet.service;

import com.zviolet.entity.Role;

public interface RoleService {

    /**
     * 根据角色id查询角色
     * @param id
     * @return
     * @throws Exception
     */
    Role findById(Integer id) throws Exception;
}
