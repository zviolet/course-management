package com.zviolet.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zviolet.entity.*;
import com.zviolet.exception.CustomException;
import com.zviolet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private UserloginService userloginService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<学生操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //显示学生信息
    @RequestMapping(value = "/showStudent")
    public String showStudent(Model model, Integer page) throws Exception {
        List<Student> studentList = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalPageCount(5);
        if (page == null || page == 0) {
            pagingVO.setShowPage(1);
            pagingVO.setUpPageNo(0);
            pagingVO.setNextPageNo(2);
            studentList = studentService.findByPaging(1);
        } else {
            pagingVO.setShowPage(page);
            pagingVO.setUpPageNo(page - 1);
            pagingVO.setNextPageNo(page + 1);
            studentList = studentService.findByPaging(page);
        }

        model.addAttribute("studentList", studentList);
        model.addAttribute("pagingVO", pagingVO);
        return "admin/showStudent";
    }

    //添加学生信息页面显示
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudentUI(Model model, Student student) throws Exception {
        List<College> collegeList = collegeService.findAll();
        model.addAttribute("collegeList", collegeList);
        return "admin/addStudent";
    }

    //添加学生信息操作
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(Student student, Model model) throws Exception {
        Boolean result = studentService.save(student);
        //重复添加
        if (!result) {
            model.addAttribute("message", "学号重复");
            return "error";
        }
        //添加成功后也添加到登录表
        Userlogin userlogin = new Userlogin();
        userlogin.setUsername(student.getStudentId().toString());
        userlogin.setPassword("123");
        userlogin.setRoleId(2);
        userloginService.save(userlogin);

        return "redirect:/admin/showStudent";
    }

    // 修改学生信息页面显示
    @RequestMapping(value = "/editStudent", method = {RequestMethod.GET})
    public String editStudentUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showStudent";
        }
        Student student = studentService.findById(id);
        if (student == null) {
            throw new CustomException("未找到该名学生");
        }
        List<College> collegeList = collegeService.findAll();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("student", student);

        return "admin/editStudent";
    }

    // 修改学生信息处理
    @RequestMapping(value = "/editStudent", method = {RequestMethod.POST})
    public String editStudent(Student student) throws Exception {
        studentService.updateById(student);

        return "redirect:/admin/showStudent";
    }

    // 删除学生
    @RequestMapping(value = "/removeStudent", method = {RequestMethod.GET})
    private String removeStudent(Integer id) throws Exception {
        if (id == null) {
            //加入没有带学生id就进来的话就返回学生显示页面
            return "admin/showStudent";
        }
        studentService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:/admin/showStudent";
    }


    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


    // 课程信息显示
    @RequestMapping("/showCourse")
    public String showCourse(Model model, Integer page) throws Exception {

        List<Course> courseList = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalPageCount(5);
        if (page == null || page == 0) {
            pagingVO.setShowPage(1);
            pagingVO.setUpPageNo(0);
            pagingVO.setNextPageNo(2);
            courseList = courseService.findByPaging(1);
        } else {
            pagingVO.setShowPage(page);
            pagingVO.setUpPageNo(page - 1);
            pagingVO.setNextPageNo(page + 1);
            courseList = courseService.findByPaging(page);
        }

        model.addAttribute("courseList", courseList);
        model.addAttribute("pagingVO", pagingVO);

        return "admin/showCourse";
    }

    //添加课程信息显示
    @RequestMapping(value = "/addCourse", method = {RequestMethod.GET})
    public String addCourseUI(Model model) throws Exception {

        List<Teacher> teacherList = teacherService.findAll();
        List<College> collegeList = collegeService.findAll();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("teacherList", teacherList);

        return "admin/addCourse";
    }

    // 添加课程信息处理
    @RequestMapping(value = "/addCourse", method = {RequestMethod.POST})
    public String addCourse(Course course, Model model) throws Exception {
        Boolean result = courseService.save(course);
        if (!result) {
            model.addAttribute("message", "课程号重复");
            return "error";
        }

        return "redirect:/admin/showCourse";
    }


    // 修改课程信息页面显示
    @RequestMapping(value = "/editCourse", method = {RequestMethod.GET})
    public String editCourseUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showCourse";
        }
        Course course = courseService.findById(id);
        if (course == null) {
            throw new CustomException("未找到该课程");
        }
        List<Teacher> teacherList = teacherService.findAll();
        List<College> collegeList = collegeService.findAll();

        model.addAttribute("teacherList", teacherList);
        model.addAttribute("collegeList", collegeList);
        model.addAttribute("course", course);


        return "admin/editCourse";
    }

    // 修改课程信息页面处理
    @RequestMapping(value = "/editCourse", method = {RequestMethod.POST})
    public String editCourse(Course course) throws Exception {
        courseService.updateById(course);

        return "redirect:/admin/showCourse";
    }


    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<教师操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    //显示教师信息
    @RequestMapping(value = "/showTeacher")
    public String showTeacher(Model model, Integer page) throws Exception {
        List<Teacher> teacherList = null;
        PagingVO pagingVO = new PagingVO();

        pagingVO.setTotalPageCount(5);
        if (page == null || page == 0) {
            pagingVO.setShowPage(1);
            pagingVO.setUpPageNo(0);
            pagingVO.setNextPageNo(2);
            teacherList = teacherService.findByPaging(1);
        } else {
            pagingVO.setShowPage(page);
            pagingVO.setUpPageNo(page - 1);
            pagingVO.setNextPageNo(page + 1);
            teacherList = teacherService.findByPaging(page);
        }

        model.addAttribute("teacherList", teacherList);
        model.addAttribute("pagingVO", pagingVO);
        return "admin/showTeacher";
    }

    // 添加教师信息显示
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET})
    public String addTeacherUI(Model model) throws Exception {
        List<College> collegeList = collegeService.findAll();
        model.addAttribute("collegeList", collegeList);

        return "admin/addTeacher";
    }

    // 添加教师信息处理
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.POST})
    public String addTeacher(Teacher teacher, Model model) throws Exception {
        Boolean result = teacherService.save(teacher);

        if (!result) {
            model.addAttribute("message", "工号重复");
            return "error";
        }
        //添加成功后，也添加到登录表
        Userlogin userlogin = new Userlogin();
        userlogin.setUsername(teacher.getTeacherId().toString());
        userlogin.setPassword("123");
        userlogin.setRoleId(1);
        userloginService.save(userlogin);

        //重定向
        return "redirect:/admin/showTeacher";
    }

    // 修改教师信息页面显示
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.GET})
    public String editTeacherUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showTeacher";
        }
        Teacher teacher = teacherService.findById(id);
        if (teacher == null) {
            throw new CustomException("未找到该名教师");
        }
        List<College> collegeList = collegeService.findAll();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("teacher", teacher);

        return "admin/editTeacher";
    }

    // 修改教师信息页面处理
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.POST})
    public String editTeacher(Teacher teacher) throws Exception {
        teacherService.updateById(teacher);

        //重定向
        return "redirect:/admin/showTeacher";
    }

    //删除教师
    @RequestMapping("/removeTeacher")
    public String removeTeacher(Integer id) throws Exception {
        if (id == null) {
            return "admin/showTeacher";
        }
        teacherService.removeById(id);

        return "redirect:/admin/showTeacher";
    }
}
