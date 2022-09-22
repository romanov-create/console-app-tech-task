package com.example.botscrewtechtask.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Lector {

    public enum Degree {
        ASSISTANT, ASSOCIATE_PROFESSOR, PROFESSOR
    }

    @Id
    private Long id;

    private String name;

    private String surname;

    private Degree degree;

    private int salary;

    @OneToOne
    private Department department;

    @ManyToMany
    private List<Department> departments;

    public Lector(Long id, String name, String surname, Degree degree, int salary, Department department, List<Department> departments) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.degree = degree;
        this.salary = salary;
        this.department = department;
        this.departments = departments;
    }

    public Lector(Long id, String name, String surname, Degree degree, int salary, List<Department> departments) {
        this(id, name, surname, degree, salary, null, departments);
    }

}
