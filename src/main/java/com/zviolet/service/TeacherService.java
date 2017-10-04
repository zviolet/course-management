package com.zviolet.service;

import com.zviolet.entity.Teacher;

import java.util.List;

public interface TeacherService {

    /**
     * 查询所有的教师信息
     * @return
     */
    List<Teacher> findAll();


    /**
     *
     * @param page
     * @return
     * @throws Exception
     */
    List<Teacher> findByPaging(Integer page) throws Exception;

    /**
     * 添加一条教师信息
     * @param teacher
     * @return 如果该教师信息已添加，返回false；未添加则返回true
     * @throws Exception
     */
    boolean save(Teacher teacher) throws Exception;

    /**
     * 根据id查找教师记录
     * @param id
     * @return
     */
    Teacher findById(Integer id) throws Exception;

    /**
     * 根据id更改教师记录
     * @param teacher
     * @throws Exception
     */
    void updateById(Teacher teacher) throws Exception;

    /**
     * 根据id删除教师信息
     * @param id
     * @throws Exception
     */
    void removeById(Integer id) throws Exception;
}
