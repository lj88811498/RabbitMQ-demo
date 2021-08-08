package com.example.demo2.other;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
/**
 *
 * @author Monkey
 * 2015-5-28
 * version 1.0
 * 小说阅读器
 */
public class TestThread extends Thread{
 static int t = 0;
 static TestThread t1;
 static TestThread t2;
 static boolean flag = true;
 String name;
 String url;
 TestThread(String name,String url){
  this.name = name;
  this.url = url;
 }
 TestThread(){}
 public void run(){
  if(name.equals("t1")){
   read();
  }else if(name.equals("t2")){
   write();
  }
 }
 public synchronized void read(){
  FileInputStream fis  =  null;
  BufferedInputStream bis  =  null;
  DataInputStream dis  =  null;
  try {
   fis = new FileInputStream(url);
   bis = new BufferedInputStream(fis);
   dis = new DataInputStream(bis);
   String temp;
   String u = url.substring(0, url.lastIndexOf("."));
   while(true){
    if(t>0){
     //自动
     if(flag){
      if((temp = new String(dis.readLine().getBytes("ISO-8859-1"),"GBK"))!= null){
       for(int i = 0;i<(temp.length()/60);i++){
        temp = temp.substring(0, (i+1)*60)+"\n"+temp.substring((i+1)*60);
       }
       System.out.println(temp);
       sleep(t*1000);
      } else{
       System.out.println("已经读取完毕！");
       System.exit(0);
      }
     }
    }else
     if(t == 0){
     //手动
     if(flag){
      if((temp = new String(dis.readLine().getBytes("ISO-8859-1"),"GBK"))!= null){
       for(int i = 0;i<(temp.length()/60);i++){
        temp = temp.substring(0, (i+1)*60)+"\n"+temp.substring((i+1)*60);
       }
       System.out.println(temp);
      }else{
       System.out.println("已经读取完毕！");
       System.exit(0);
      }
      flag = false;
     }
    }else if(t<0){
     FileOutputStream fos = new FileOutputStream(u+"_rem.txt");
     //FileOutputStream fos = new FileOutputStream(url);
     BufferedOutputStream bos = new BufferedOutputStream(fos);
     DataOutputStream dos = new DataOutputStream(bos);
     int temp2;
     long second = new Date().getTime();
     while((temp2 = dis.read())!= -1){
      dos.write(temp2);
     }
     dos.flush();
     fos.close();
     bos.close();
     dos.close();
     
     fis.close();
     bis.close();
     dis.close();
     try{
      File file = new File(url);
      file.delete();
      File f = new File(u+"_rem.txt");
      f.renameTo(file);
      file = null;
      f = null;
      //Runtime.getRuntime().exec("cmd /c start "+url.substring(0, url.indexOf("/")));
     }catch (Exception e) {
      e.printStackTrace();
     }
     System.out.println("保存成功");
     System.out.println("用时："+(new Date().getTime()-second)+"ms");
     System.exit(0);
    }
   }
  } catch (Exception e) {
   e.printStackTrace();
  }finally{
   try {
    fis.close();
    bis.close();
    dis.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }
 public synchronized void write(){
  try {
   while(true){
    int c = System.in.read();
    if(c-13 == 0){//直接回车
     flag = true;
    }else if(c-'c' == 0){//输入c
     System.exit(0);//结束
    }else if(c-43 == 0){//输入+
     if(t>0)t--;//加速看
     System.out.println("-----观看速度:"+t+"s------");
     flag = true;
    }else if(c-45 == 0){//输入-
     t++;//减速
     System.out.println("-----观看速度:"+t+"s------");
     flag = true;
    }else if(c-83 == 0||c-115 == 0){//输入s
     t = -1;//保存
     System.out.println("-----正在保存进度，请稍后------");
    }
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
 
 public static void main(String[] args) {
  String uri = "e:/download/fts.txt";
  t = 1;//自动阅读速度，0为手动
  t1 = new TestThread("t1",uri);
  t2 = new TestThread("t2",uri);
  t1.start();
  t2.start();
 }
}