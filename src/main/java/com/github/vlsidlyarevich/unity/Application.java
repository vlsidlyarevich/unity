package com.github.vlsidlyarevich.unity;

import com.github.vlsidlyarevich.unity.domain.Worker;
import com.github.vlsidlyarevich.unity.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by vlad on 14.09.16.
 */
@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    @Autowired
    private WorkerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Worker("Alice", "Smith", 20));
        repository.save(new Worker("Bob", "Smith", 22));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Worker worker : repository.findAll()) {
            System.out.println(worker);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Worker found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with age('22'):");
        System.out.println("--------------------------------");
        for (Worker worker : repository.findAllByAge(22)) {
            System.out.println(worker);
        }
    }
}
