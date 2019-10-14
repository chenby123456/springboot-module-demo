package com.cby.proxy.dynamicproxy;

import com.cby.proxy.entrust.Student;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Throwable {
        Student student = new Student();
        DynamicProxy dynamicProxy = new DynamicProxy(student);
        Object proxyedObj = dynamicProxy.CreatProxyedObj();
        Method sayHello = student.getClass().getMethod("sayHello", null);
        Object invoke = dynamicProxy.invoke(proxyedObj, sayHello, args);
    }
}
