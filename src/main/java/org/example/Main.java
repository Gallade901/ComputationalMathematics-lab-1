package org.example;

import org.example.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        App app = applicationContext.getBean("app", App.class);

        app.run();
    }
}
