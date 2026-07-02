package org.springframework.test.bean;

import org.springframework.test.ioc.bean.Car;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Car, String> map = new HashMap<>();
        Car car = new Car();
        car.setBrand("123");
        map.put(car, "123");
        System.out.println(map.get(car));
        System.out.println("id  " + System.identityHashCode(car));
        car = null;
        System.out.println("id2  " + System.identityHashCode(car));
        System.out.println("size"+map.size());
        for (Map.Entry<Car, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println("id3  " + System.identityHashCode(entry.getKey()));
            System.out.println(map.get(entry.getKey()));
            System.out.println(entry.getValue());
        }
        System.out.println(map.get(car));
    }
}
