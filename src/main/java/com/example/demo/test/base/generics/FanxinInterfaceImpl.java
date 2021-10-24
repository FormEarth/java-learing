package com.example.demo.test.base.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author raining_heavily
 * @date 2021/1/20 21:18
 **/
public class FanxinInterfaceImpl<E> implements FanxinInterface<E> {

    @Override
    public void doSomething() {
//        Type[] types = getClass().getGenericInterfaces();
//        ParameterizedType parameterizedType = (ParameterizedType) types[0];
//        Type type = parameterizedType.getActualTypeArguments()[0];
//        System.out.println(type.getTypeName());

        Class<E> dc = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(dc.getTypeName());
    }

    public static void main(String[] args) {
        FanxinInterfaceImpl<String> fi = new FanxinInterfaceImpl<>();
//        String wer = fi.getEType();
//        System.out.println(wer);
//        fi.doSomething();
        //一个类可能实现多个接口,每个接口上定义的泛型类型都可取到
//        Type[] interfacesTypes = fi.getClass().getGenericInterfaces();
//        for (Type t : interfacesTypes) {
//            Type[] genericType2 = ((ParameterizedType) t).getActualTypeArguments();
//            for (Type t2 : genericType2) {
//                System.out.println(t2.getTypeName());
//            }
//        }

        fi.getEType();
    }
}
