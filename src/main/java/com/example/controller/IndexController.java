package com.example.controller;

import com.example.entity.Absences;
import com.example.entity.excel.AbsencesExcel;
import com.example.util.HttpUtil;
import com.example.util.JsonUtil;
import com.example.util.ListUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/5/2.
 * 主控制器
 */
@RestController
@RequestMapping("data")
public class IndexController {

    private final String url="http://123.206.80.72";

    @RequestMapping(value = "test",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String Test(){
        return "test";
    }
    @RequestMapping(value = "/findstudentbymajorandgrade",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String findStudentMajorAndGrade(String major,String grade){
        Map<String,String> map=new HashMap<>();
        map.put("major",major);
        map.put("grade",grade);
        String address=url+"/data/findstudentbymajorandgrade";
        String result= HttpUtil.get(address,map);
        StringBuilder sb=new StringBuilder();
        sb.append("{\"code\": 0,\"msg\": \"获取成功\",\"count\": 1,\"list\":").append(result).append("}");
        return sb.toString();
    }

    @RequestMapping(value = "findallcourse",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String findAllCourse(){
        String address=url+"/data/findallcourse";
        return HttpUtil.get(address,null);
    }
    @RequestMapping(value = "findabsencestudent",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String findAbsenceStudent(String id){
        String address=url+"/data/findabsencesstudent";
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        String json=HttpUtil.get(address,map);
        List<Absences> absences= JsonUtil.JsonToList(Absences.class,json);
        List<AbsencesExcel> absencesExcels= ListUtil.convertAbsencesToAbsencesExcel(absences);
        String result=JsonUtil.toString(absencesExcels);
        StringBuilder sb=new StringBuilder();
        sb.append("{\"code\": 0,\"msg\": \"获取成功\",\"count\": 1,\"list\":").append(result).append("}");
        return sb.toString();
    }
    @RequestMapping(value="findstudentscore",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String findStudentCourse(String id){
        Map<String,String> map=new HashMap<>();
        map.put("course_id",id);
        String address=url+"/data/findstudentscore";
        String result=HttpUtil.get(address,map);
        System.out.println(result);
        StringBuilder sb=new StringBuilder();
        sb.append("{\"code\": 0,\"msg\": \"获取成功\",\"count\": 1,\"list\":").append(result).append("}");
        return  sb.toString();
    }


}
