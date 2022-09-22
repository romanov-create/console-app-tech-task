package com.example.botscrewtechtask.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Department {

    @Id
    private Long id;

    private String name;

    @OneToOne(mappedBy = "department")
    private Lector headLector;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
    private List<Lector> lectors;

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
