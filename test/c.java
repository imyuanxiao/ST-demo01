package test;

import test.entity.Dog;

public class c {

    double x, y, ground, canjump, speed, onplat, t3;

    public static void main(String[] args) throws Exception {
        Dog dog = (Dog)Class.forName("test.entity.Dog").getDeclaredConstructor().newInstance();
        dog.run();
    }

}
