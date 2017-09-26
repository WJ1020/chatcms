package com.example.controller;

import com.example.entity.Absences;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.entity.excel.AbsencesExcel;
import com.example.entity.excel.Course;
import com.example.entity.excel.StudentScore;
import com.example.server.UserServer;
import com.example.util.ExcelUtil;
import com.example.util.HttpUtil;
import com.example.util.JsonUtil;
import com.example.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/5/6.
 * 使用模板生成的视图
 */
@Controller
public class ViewController {

    private UserServer userServer;
    private final String url="http://123.206.80.72";

    @Autowired
    public void setUserServer(UserServer userServer) {
        this.userServer = userServer;
    }

    @RequestMapping("/userList")
    public String  findAllUser(Model model){
        List<User> users=userServer.findAll();
        model.addAttribute("users",users);
        return "userlist";
    }
    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET,produces = "application/octet-stream;charset=UTF-8")
    public void downloadFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String fileName=URLEncoder.encode("测试文件.xls","utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        List<User> users=userServer.findAll();
            ExcelUtil.createExcel(users,response.getOutputStream());
    }
    @RequestMapping(value="/dowlodstudentbymajorandgrade",method = RequestMethod.GET,produces = "application/octet-stream;charset=UTF-8")
    public void findStudentMajorAndGrade(String major, String grade, HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String fileName=URLEncoder.encode(grade+major+".xls","utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        Map<String,String> map=new HashMap<>();
        map.put("major",major);
        map.put("grade",grade);
        String address=url+"/data/findstudentbymajorandgrade";
        String result= HttpUtil.get(address,map);
        List<Student> students= JsonUtil.JsonToList(Student.class,result);
        ExcelUtil.createExcel(students,response.getOutputStream());
    }
    @RequestMapping(value = "/downloadstudentscorebyid",method = RequestMethod.GET,produces = "application/octet-stream;charset-UTF-8")
    public void downloadStudentScoreExcel(String id,HttpServletResponse response){
        response.setContentType("application/octet-stream");
        Map<String,String> map=new HashMap<>();
        map.put("course_id",id);
        String address=url+"/data/findstudentscore";
        String result=HttpUtil.get(address,map);
        List<StudentScore> studentScores= JsonUtil.JsonToList(StudentScore.class,result);
        if (studentScores==null|| studentScores.isEmpty()) return;
        String fileName= null;
        try {
            fileName = URLEncoder.encode(studentScores.get(0).getCourseName()+"的课堂提问情况.xls","utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        try {
            ExcelUtil.createExcel(studentScores,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/dowloadabsencesstudentbyid",method = RequestMethod.GET,produces = "application/octet-stream;charset=UTF-8")
    public void downloadAbsencesExcel(String id,HttpServletResponse response){
        response.setContentType("application/octet-stream");
        String address=url+"/data/findabsencesstudent";
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        String json=HttpUtil.get(address,map);
        List<Absences> absences= JsonUtil.JsonToList(Absences.class,json);
        List<AbsencesExcel> absencesExcels= ListUtil.convertAbsencesToAbsencesExcel(absences);
        if (absencesExcels.isEmpty()) return;
        String fileName=absencesExcels.get(0).getGrade()+"级"+ absencesExcels.get(0).getMajor()+"缺课名单.xls";
        try {
            fileName=URLEncoder.encode(fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        try {
            ExcelUtil.createExcel(absencesExcels,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ResponseBody
    @RequestMapping(value = "/uploadstudent",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String  uploadFile(@RequestParam("file") MultipartFile mdfile) throws IOException {
        if (!mdfile.isEmpty()){
            List<Student> students=ExcelUtil.readExcel(Student.class,mdfile.getInputStream());
            String json=JsonUtil.ObjectforJson(students);
            System.out.println(json);
            String address=url+"/data//batchinsertstudent";
            String result=HttpUtil.post(address,json);
            List<Integer> list=JsonUtil.JsonToList(Integer.class,result);
            return "success"+list.size();
        }
        return "error:error";
    }
    @ResponseBody
    @RequestMapping(value = "/uploadcourse",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String uploadCourseFile(@RequestParam("file") MultipartFile file)throws IOException{
        if (!file.isEmpty()){
            List<Course> courses=ExcelUtil.readExcel(Course.class,file.getInputStream());
            String json=JsonUtil.ObjectforJson(courses);
            System.out.println(json);
//            String address=url+"/data/batchinsertcourse";
//            String result=HttpUtil.post(address,json);
//            System.out.println(result);
//            List<Integer> list=JsonUtil.JsonToList(Integer.class,result);
//            return "success"+list.size();
        }
        return "error:error";
    }

}
