package com.example.demo.test.base.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author raining_heavily
 * @date 2021/1/20 21:15
 **/
public interface FanxinInterface<E> {

    default void getEType(){
//        Class<E> dc = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//       dc.getName();
        Type[] types = getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0];
        Type type = parameterizedType.getActualTypeArguments()[0];
        System.out.println(type.getTypeName());
    }

    void doSomething();
}
