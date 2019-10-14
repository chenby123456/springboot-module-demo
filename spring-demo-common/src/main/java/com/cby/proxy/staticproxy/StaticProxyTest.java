package com.cby.proxy.staticproxy;

import com.cby.proxy.entrust.Student;
import com.cby.proxy.interfacef.Person;

/**
 * @ClassName:
 * @Description:
 *          静态代理要求代理类和被代理类必须实现同样的接口
 *          静态代理一个代理类针对一个委托类
 * @Author: bingyang.chen
 * @Date: 2019/10/14
 * @Version: 1.0.0
 */
public class StaticProxyTest implements Person{

    public Student student;

    public StaticProxyTest(Student student) {
        this.student = student;
    }

    public StaticProxyTest() {
    }

    public static void main(String[] args){
        Student student = new Student();
        StaticProxyTest proxy = new StaticProxyTest(student);
        proxy.sayHello();

    }

    @Override
    public void sayHello() {
        System.out.println("StaticProxyTest syaHello Begin!");
        student.sayHello();
        System.out.println("StaticProxyTest syaHello End!");
    }

    @Override
    public void sayGoodBye() {
        System.out.println("StaticProxyTest sayGoodBye Begin!");
        student.sayGoodBye();
        System.out.println("StaticProxyTest sayGoodBye End!");
    }
}
