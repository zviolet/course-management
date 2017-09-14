package com.zviolet.dao;

import com.zviolet.entity.Student;

public interface StudentDao {
     
    List<Student> findByPaging(PagingVO pagingVO) throws Exception;

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
    void updateById(Student record);

    /**
     * 根据id查询学生信息
     * @param id
     * @return
     */
    Student selectById(Integer id);

    /**
     * 根据id删除学生信息
     * @param id
     */
    void deleteById(Integer id);
}
