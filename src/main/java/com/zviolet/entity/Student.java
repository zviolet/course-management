package com.zviolet.entity;

import java.util.Date;
import java.util.List;

public class Student {
    //学生id
    private Integer studentId;
    //学生姓名
    private String studentName;
    //性别
    private String sex;
    //出生年月
    private Date birthyear;

    private Date grade;
    //所属学院id
    private Integer collegeId;
    //选修课程列表
    private List<SelectedCourse> selectedCourseList;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(Date birthyear) {
        this.birthyear = birthyear;
    }

    public Date getGrade() {
        return grade;
    }

    public void setGrade(Date grade) {
        this.grade = grade;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public List<SelectedCourse> getSelectedCourseList() {
        return selectedCourseList;
    }

    public void setSelectedCourseList(List<SelectedCourse> selectedCourseList) {
        this.selectedCourseList = selectedCourseList;
    }
}
