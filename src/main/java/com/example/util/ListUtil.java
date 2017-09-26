package com.example.util;

import com.example.entity.Absences;
import com.example.entity.excel.AbsencesExcel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/5/12.
 * 常用的list转换
 */
public class ListUtil {

    public static List<AbsencesExcel> convertAbsencesToAbsencesExcel(List<Absences> absencess){
        List<AbsencesExcel> absencesExcels=new LinkedList<>();
        if (absencess==null)
            return null;
        int state1;
        String state2=null;
        for (Absences absences:absencess){
            state1=absences.getState();
            switch (state1){
                case 0:state2="迟到";break;
                case 1:state2="请假";break;
                case 2:state2="缺课";break;
                default:state2="未找到匹配状态";
            }
            AbsencesExcel absencesExcel=new AbsencesExcel(absences.getStudent().getSno(),absences.getStudent().getName(),absences.getStudent().getMajor(),absences.getStudent().getGrade(),state2,absences.getDate());
            absencesExcels.add(absencesExcel);
        }
        return absencesExcels;
    }
}
