package com.cby.proxy.entrust;

import com.cby.proxy.interfacef.Person;

/**
 * @ClassName:
 * @Description:
 * @Author: bingyang.chen
 * @Date: 2019/10/14
 * @Version: 1.0.0
 */
public class Student implements Person{
    @Override
    public void sayHello() {
        System.out.println("Student sayHello!");
    }

    @Override
    public void sayGoodBye() {
        System.out.println("Student sayGoodBye!");
    }
}
