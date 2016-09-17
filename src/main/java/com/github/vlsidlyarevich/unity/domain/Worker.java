package com.github.vlsidlyarevich.unity.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * Created by vlad on 15.09.16.
 */
@Data
@ToString
public class Worker {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Integer age;

    public Worker() {
    }

    public Worker(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
