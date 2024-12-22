package com.crud.crud.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mohamad = new Student(
                    "Mohamad",
                    "m@m.com",
                    LocalDate.of(2000, 12, 19)
                    );

            Student alex = new Student(
                    "Alex",
                    "a@a.com",
                    LocalDate.of(2001, 12, 19)
                );

            repository.saveAll(List.of(mohamad, alex));
            
        };
    }
}
