package com.zviolet.dao;

import com.zviolet.entity.Student;

public interface StudentDao {
    /**
     * 插入一条学生记录
     * @param record
     * @return
     */
    int insert(Student record);

    /**
     * 修改选定的学生记录
     * @param record
     * @return
     */
    int updateById(Student record);
}
