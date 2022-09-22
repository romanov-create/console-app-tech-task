package com.example.botscrewtechtask.service.impl;

import com.example.botscrewtechtask.domain.Lector;
import com.example.botscrewtechtask.repository.LectorRepository;
import com.example.botscrewtechtask.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectorServiceImpl implements LectorService {

    @Autowired
    private LectorRepository lectorRepository;

    @Override
    public List<String> searchAllByName(String name) {
        List<Lector> lectors = lectorRepository.searchAllByName(name);

        return lectors.stream()
                .map(lector -> lector.getName() + " " + lector.getSurname())
                .collect(Collectors.toList());
    }
}
