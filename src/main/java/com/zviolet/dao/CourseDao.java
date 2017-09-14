package com.zviolet.dao;

import com.zviolet.entity.Course;

public interface CourseDao {

    List<Course> findByPaging(PagingVO pagingVO) throws Exception;

    /**
     * 插入一条课程记录
     * @param record
     * @return
     */
    int insert(Course record);

    /**
     * 修改选定的课程记录
     * @param record
     */
    void updateById(Course record);

    /**
     * 根据id查询课程信息
     * @param id
     * @return
     */
    Course selectById(Integer id);

    /**
     * 根据id删除课程信息
     * @param id
     */
    void deleteById(Integer id);
}
