package com.cby.utils;

import com.cby.annotation.MineAnnotation;

import java.lang.reflect.Field;

/**
 * @ClassName:
 * @Description:
 * @Author: bingyang.chen
 * @Date: 2019/10/10
 * @Version: 1.0.0
 */
public class ReflectUtils {

    public static void test(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null && fields.length>0){
            for (int i = 0;i < fields.length; i++){
                Field fields1 = fields[i];
                String name = fields1.getName();
                MineAnnotation annotation = fields[i].getAnnotation(MineAnnotation.class);
                if(annotation != null){
                    String value = annotation.value();
                    System.out.println(name+"==="+value);
                }

            }
        }
    }

}
