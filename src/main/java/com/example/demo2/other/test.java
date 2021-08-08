package com.example.demo2.other;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijiang04
 * @version 1.0
 * @description: TODO
 * @date 2021/8/6 9:52
 */
public class test {


    public static void main(String[] args) {
        String s = "311415926";

        System.out.println(s.indexOf("."));

        System.out.println(s.substring(s.indexOf(".") + 1));

        double   f   =   111231.5555;
        BigDecimal b   =   new   BigDecimal(f);
        BigDecimal bigDecimal = b.setScale(3, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);
        float b2   =   new   BigDecimal(f).setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
        float b3   =   new   BigDecimal(f).setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
        System.out.println("---------------");
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b3 == b2);

        BigDecimal b4  =   new   BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal b5  =   new   BigDecimal(f).setScale(3, BigDecimal.ROUND_HALF_UP);
        System.out.println("---------------");
        System.out.println(b4);
        System.out.println(b5);
        System.out.println(b4.compareTo(b5));
        //保留2位小数
        float   f1   =   b.setScale(0,   BigDecimal.ROUND_HALF_UP).floatValue();
        float f2 = 111232f;
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f1 == f2);

        List list = Arrays.asList("111232","111231","111230");
        System.out.println(list.size());
//        Object collect = list.stream().map(s1 -> b.setScale(0, BigDecimal.ROUND_HALF_UP).floatValue()).collect(Collectors.toList());
        System.out.println(list.contains(f2));
//        System.out.println(collect);

        System.out.println(StringUtils.isNoneBlank(""));
        System.out.println(StringUtils.isNoneBlank(null));

        User u = new User("111", "222");
        new test().set(u);
        new test().set2(u);
        System.out.println(u);
    }

    private  void set(User user){
        user.setName("123");
    }

    private void set2(User user){
        user.setPwd("345");
    }



}
