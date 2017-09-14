package com.zviolet.dao;

import com.zviolet.entity.College;

import java.util.List;

public interface CollegeDao {
    /**
     * 查询所有的学院
     * @return
     */
    List<College> selectAll();
}

