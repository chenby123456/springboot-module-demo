package com.cby.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName:
 * @Description:
 *      动态代理的实现有两张方式，一种是JDK的动态代理，一个是cglib动态代理
 *      jdk动态代理模式里面有个拦截器的概念，在jdk中，只要实现了InvocationHandler这个接口的类就是一个拦截器类。
还使用了些反射的相关概念。
拦截器的概念不了解没关系，假如写了个请求到action，经过拦截器，然后才会到action。然后继续有之后的操作。
拦截器就像一个过滤网，一层层的过滤，只要满足一定条件，才能继续向后执行。
拦截器的作用：控制目标对象的目标方法的执行。

拦截器的具体操作步骤：
1.引入类：目标类和一些扩展方法相关的类。
2.赋值：调用构造函数给相关对象赋值o
3.合并逻辑处理：在invoke方法中把所有的逻辑结合在一起。最终决定目标方法是否被调用。
 * @Author: bingyang.chen
 * @Date: 2019/10/14
 * @Version: 1.0.0
 */
public class DynamicProxy implements InvocationHandler {

    private Object obj;

    public DynamicProxy() {
    }

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("交学费！！！");
        method.invoke(obj,args);
        return proxy;
    }

    // 生成代理类
    public Object CreatProxyedObj()
    {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
}
