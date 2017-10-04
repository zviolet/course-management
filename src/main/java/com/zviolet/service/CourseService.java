package com.zviolet.service;

import com.zviolet.entity.Course;

import java.util.List;

public interface CourseService {
    /**
     *
     * @param page
     * @return
     * @throws Exception
     */
    List<Course> findByPaging(Integer page) throws Exception;

    /**
     * 添加一条课程信息
     * @param course
     * @return 如果该课程信息已添加，返回false；未添加则返回true
     * @throws Exception
     */
    boolean save(Course course) throws Exception;

    /**
     * 根据id查找课程记录
     * @param id
     * @return
     */
    Course findById(Integer id) throws Exception;

    /**
     * 根据id更改课程记录
     * @param course
     * @throws Exception
     */
    void updateById(Course course) throws Exception;

    /**
     * 根据id课程学生
     * @param id
     * @throws Exception
     */
    void removeById(Integer id) throws Exception;
}
