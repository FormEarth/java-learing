package com.example.demo.test.entity;


/**
 * 外部类
 * @author raining_heavily
 * @date 2019/12/8 16:52
 **/
public class Outer {
    private String name = "name";
    private int age = 23;

    /**
     * 内部类
     **/
    class Inner {
        private String name;
        private int age;

        {
            this.name = "Jake";
            this.age = 10;
            System.out.println(this);
        }

        Inner() {
        }

        Inner(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Inner{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    Bird getBird() {
        Bird bird = new Bird() {
            @Override
            public void fly() {
                System.out.println("i can fly");
            }

            @Override
            public void run() {
                System.out.println("i can run");
            }

            @Override
            public void call() {
                System.out.println("a~ a~ a~");
            }
        };
        bird.fly();
        bird.run();
        bird.call();
        return bird;
    }

    public static void main(String[] args) {
        Inner inner = new Outer().new Inner("wong",20);
        System.out.println(inner);
    }
}
