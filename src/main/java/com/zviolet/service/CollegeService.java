package com.zviolet.service;

import com.zviolet.entity.College;

import java.util.List;

public interface CollegeService {
    /**
     * 查询所有的学院信息
     * @return
     */
    List<College> findAll();
}
