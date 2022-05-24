package edu.miu.cs544;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Greeting greeting = context.getBean("greeting",Greeting.class);
        greeting.sayHello();
    } 
}
