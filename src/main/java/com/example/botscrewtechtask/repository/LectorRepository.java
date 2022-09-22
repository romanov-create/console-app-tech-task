package com.example.botscrewtechtask.repository;

import com.example.botscrewtechtask.domain.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query("SELECT l FROM Lector l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Lector> searchAllByName(String name);

}
