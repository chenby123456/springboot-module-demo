package com.cby.proxy.dynamicproxy;

import com.cby.proxy.entrust.Student;
import com.cby.proxy.interfacef.Person;

public class Test {
    public static void main(String[] args) throws Throwable {
        Student student = new Student();
        DynamicProxy dynamicProxy = new DynamicProxy(student);
        Person proxyedObj = (Person) dynamicProxy.CreatProxyedObj();
        proxyedObj.sayHello();
    }
}
