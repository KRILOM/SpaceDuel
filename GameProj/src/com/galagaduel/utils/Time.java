package com.galagaduel.utils;

public class Time {//все манипуляции со временем будут работать с помощью наносекунд

    public static final long SECOND = 1000000000l;

    public static long get(){
        return System.nanoTime();//возвращаем текущее время в наносекундах
    }


}
