package com.cby.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName:
 * @Description:
 * @Author: bingyang.chen
 * @Date: 2019/9/27
 * @Version: 1.0.0
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MineAnnotation {
    String value() default "";
}
