package com.example.entity.excel;

import com.example.annotation.Excel;

import java.util.Date;

/**
 * Created by WangShiXiang on 2017/5/12.
 * 导出缺课名单的格式
 */
public class AbsencesExcel {
    @Excel(name = "学号")
    private String sno;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "专业")
    private String major;
    @Excel(name = "年级")
    private String grade;
    @Excel(name = "状态")
    private String state;
    @Excel(name = "时间")
    private Date date;


    public AbsencesExcel(){}

    public AbsencesExcel(String sno, String name, String major, String grade, String state, Date date) {
        this.sno = sno;
        this.name = name;
        this.major = major;
        this.grade = grade;
        this.state = state;
        this.date = date;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
