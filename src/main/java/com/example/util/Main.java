package com.example.util;

import com.example.entity.Absences;
import com.example.entity.Student;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/5/7.
 * 测试
 */
public class Main {

    private static JavaType getCollectionType(ObjectMapper mapper,Class<?> collectionClass,Class<?>... elementClasses){
        return mapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
    }

    public static void main(String args[]) throws IOException {
        String str=HttpUtil.get("http://wx.wodeschool.cn/data/absences",null);
        System.out.println(str);
        ObjectMapper objectMapper=new ObjectMapper();
        List<Absences> absences= objectMapper.readValue(str,getCollectionType(objectMapper,List.class, Absences.class));
        System.out.println(absences.get(0).getStudent().getMajor());
        //        OutputStream outputStream=new FileOutputStream("D:\\test\\student.xls");
//        FileInputStream inputStream=new FileInputStream("D:\\test\\a.xls");
//        ExcelUtil.createExcel(students,outputStream);
//        List<Student> students1= ExcelUtil.readExcel(Student.class,inputStream);
//        String str=objectMapper.writeValueAsString(students1);
//        System.out.println(str);
//
//        str= HttpUtil.post("http://172.18.140.154/data/batchinsertstudent",str);
//        System.out.println(str);
    }
}
