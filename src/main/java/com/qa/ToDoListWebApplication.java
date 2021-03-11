package com.qa;

import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class ToDoListWebApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ToDoListWebApplication.class, args);
		
		// Getting some beans from the application context
				LocalTime time = context.getBean("localTime", LocalTime.class);
				LocalTime time2 = context.getBean("localTime", LocalTime.class);

				// Logging and comparing the beans for equality
				System.out.println(time);
				System.out.println(time2);
				System.out.println(time.equals(time2));
			}

			@Bean // Indicates this is a bean, controlled by Spring (added to the app context)
			@Scope("singleton") // This bean is only created once, and then reused whenever requested
			public static LocalTime localTime() {
				return LocalTime.now();
			}
	}

