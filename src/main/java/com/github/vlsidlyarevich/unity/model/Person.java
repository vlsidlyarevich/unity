package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@Data
@ToString
@NodeEntity(label = "Person")
public class Person extends BaseEntity {

    @Relationship(type = "HAS_NAME", direction = Relationship.OUTGOING)
    private Name name;
    private Integer age;
    private Gender gender;
    private String birthday;

    public Person() {

    }

    public Person(Name name) {
        this.name = name;
    }

    public Person(String firstName, String lastName) {
        this.name = new Name(firstName, lastName);
    }

    public Person(Name name, Integer age, Gender gender, String birthday) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        if (EnumUtils.isValidEnum(Gender.class, gender)) {
            this.gender = Gender.valueOf(gender);
        }
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
