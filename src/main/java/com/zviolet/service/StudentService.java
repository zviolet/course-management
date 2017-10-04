package com.zviolet.service;

import com.zviolet.entity.Student;

import java.util.List;

public interface StudentService {

    /**
     *
     * @param page
     * @return
     * @throws Exception
     */
    List<Student> findByPaging(Integer page) throws Exception;

    /**
     * 添加一条学生信息
     * @param student
     * @return 如果该学生信息已添加，返回false；未添加则返回true
     * @throws Exception
     */
    boolean save(Student student) throws Exception;

    /**
     * 根据id查找学生记录
     * @param id
     * @return
     */
    Student findById(Integer id) throws Exception;

    /**
     * 根据id更改学生记录
     * @param student
     * @throws Exception
     */
    void updateById(Student student) throws Exception;

    /**
     * 根据id删除学生
     * @param id
     * @throws Exception
     */
    void removeById(Integer id) throws Exception;

}
