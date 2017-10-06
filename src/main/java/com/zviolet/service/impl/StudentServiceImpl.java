package com.zviolet.service.impl;

import com.zviolet.dao.StudentDao;
import com.zviolet.entity.PagingVO;
import com.zviolet.entity.Student;
import com.zviolet.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Student> findByPaging(Integer page) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setShowPage(page);
        pagingVO.setShowRecordNo((page - 1) * pagingVO.getPageSize());
        List<Student> studentList = studentDao.findByPaging(pagingVO);
        return studentList;
    }

    public boolean save(Student student) throws Exception {
        Student stu = studentDao.selectById(student.getStudentId());
        if (stu == null) {
            studentDao.insert(student);
            return true;
        }
        return false;
    }

    public Student findById(Integer id) throws Exception {
        return studentDao.selectById(id);
    }

    public void updateById(Student student) throws Exception {
        studentDao.updateById(student);
    }

    public void removeById(Integer id) throws Exception {
        studentDao.deleteById(id);
    }
}
