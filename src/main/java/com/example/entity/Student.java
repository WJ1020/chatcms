package com.example.entity;

import com.example.annotation.Excel;

/**
 * Created by WangShiXiang on 2017/5/3.
 * Excel表格
 */
public class Student {
    @Excel(name = "学号",columnIndex = 0)
    private String sno;
    @Excel(name = "姓名",columnIndex = 1)
    private String name;
    @Excel(name = "性别",columnIndex = 2)
    private int sex;
    @Excel(name = "学院",columnIndex = 3)
    private String college;
    @Excel(name = "专业",columnIndex = 4)
    private String major;
    @Excel(name="年级",columnIndex = 5)
    private String grade;

    public Student(String sno, String name, int sex, String college, String major, String grade) {
        this.sno = sno;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.major = major;
        this.grade = grade;
    }
    public Student(){}

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                '}'+"\n";
    }
}
