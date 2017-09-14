package com.zviolet.dao;

import com.zviolet.entity.Teacher;

public interface TeacherDao {
    List<Teacher> findByPaging(PagingVO pagingVO) throws Exception;

    /**
     * 插入一条教师记录
     * @param record
     * @return
     */
    int insert(Teacher record);

    /**
     * 修改选定的教师记录
     * @param record
     * @return
     */
    int updateById(Teacher record);

    /**
     * 根据id查询课程信息
     * @param id
     * @return
     */
    Teacher selectById(Integer id);

    /**
     * 查询所有的教师信息
     * @return
     */
    List<Teacher> selectAll();

    /**
     * 根据id删除课程信息
     * @param id
     */
    void deleteById(Integer id);
}
