package com.example.util;

import com.example.annotation.Excel;
import com.example.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/5/6.
 * java生成Excel文件
 */

public class ExcelUtil {
    /**
     *
     * @param list 要生成excel对象的list
     * @param outputStream 生成文件写入到哪
     */
    public static void createExcel(List list, OutputStream outputStream){
        //创建HSSFWorkBook(一个excel文件)
        HSSFWorkbook workbook=new HSSFWorkbook();
        //HSSFSheet一个表
        HSSFSheet sheet=workbook.createSheet();
        //excel表的行索引
        int row=0;
        //此时是否设置了表格列的名字
        boolean isSetTile=false;
        //遍历list
        for (Object o:list){
            //列的索引
            int y=0;
            HSSFRow row1=sheet.createRow(row);
            HSSFRow row2=sheet.createRow(row+1);
            HSSFRow row3=sheet.createRow(row);
            //遍历对象的所有属性
            for (Field field:o.getClass().getDeclaredFields()){
                field.setAccessible(true);
                if(!isSetTile){//如果没有设置表格列的名字
                    //读取注解的值
                    String propertyName=field.getAnnotation(Excel.class).name();
                    //设置列的名字
                    row1.createCell(y).setCellValue(propertyName);

                    try {
                        //将list里的第一个对象的值写入excel
                        String value=field.get(o).toString();
                        row2.createCell(y).setCellValue(value);
                    }catch (IllegalArgumentException|IllegalAccessException e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        String value=field.get(o).toString();
                        row3.createCell(y).setCellValue(value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                y++;
            }
            if (!isSetTile){isSetTile=true;row++;}
            row++;
        }
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List readExcel(Class c, InputStream inputStream){
        List list=new ArrayList();
        try {
            Workbook workbook=new HSSFWorkbook(inputStream);
            for (int i=0;i<workbook.getNumberOfSheets();i++){
                Sheet sheet=workbook.getSheetAt(i);
                for (int r=0;r<sheet.getLastRowNum();r++){
                    Row row=sheet.getRow(r);
                    Object o=c.newInstance();
                    for (Field field:c.getDeclaredFields()){
                        field.setAccessible(true);
                        if (field.getAnnotation(Excel.class)==null) continue;
                       int cellnum= field.getAnnotation(Excel.class).columnIndex();
                       if (cellnum==-1) continue;
                        if (field.getType().equals(String.class)){
                            if (row==null) continue;
                            String value=row.getCell(cellnum).getStringCellValue();
                            field.set(o,value);
                        }else if (field.getType().equals(Integer.class)||field.getType().equals(int.class)){
                            String value=row.getCell(cellnum).getStringCellValue();
                            field.set(o,Integer.parseInt(value));
                        }else {
                            System.out.println("未识别的类型"+field.getType());
                        }
                    }
                    if (row!=null)
                        list.add(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }

    public static void main(String args[]) throws FileNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("d:\\test\\a.xls");
       List<Student> list= ExcelUtil.readExcel(Student.class,fileInputStream);

    }

}
