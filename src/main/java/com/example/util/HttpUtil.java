package com.example.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by WangShiXIANG on 2017/5/3.
 * 主要用来发送http请求
 */
public class HttpUtil {

    public static String get(String address, Map<String,String> param){
        String params;
        if (param!=null){
            StringBuilder sb=new StringBuilder(address);
            sb.append("?");
            for(Map.Entry<String,String> entry : param.entrySet()){
                try {
                    sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),"utf-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            params=sb.toString();
            params=params.substring(0,params.length()-1);
        }else {
            params=address;
        }

        System.out.println(params);
        try{
            URL url=new URL(params);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.setReadTimeout(1000);
            if (200==httpURLConnection.getResponseCode()){
                InputStream in=httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                if (in.available()==0) return null;
                byte[] buffer=new byte[in.available()];
                int len;
                System.out.println(in.available());
                while (-1!=(len=in.read(buffer))){
                    byteArrayOutputStream.write(buffer,0,len);
                    byteArrayOutputStream.flush();
                }
                byteArrayOutputStream.close();
                httpURLConnection.disconnect();
                return byteArrayOutputStream.toString("utf-8");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String address, String content){
        try {
            content= URLEncoder.encode(content,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            URL url=new URL(address);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            //设置请求的格式
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            //设置编码格式
            httpURLConnection.setRequestProperty("Accept-Charset","utf-8");
            DataOutputStream outputStream=new DataOutputStream(httpURLConnection.getOutputStream());
            outputStream.writeBytes(content);
            outputStream.flush();
            outputStream.close();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuilder sb=new StringBuilder();
            while ((line=bufferedReader.readLine())!=null){
                sb.append(line);
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
            return sb.toString();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
