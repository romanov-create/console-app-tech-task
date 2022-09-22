package com.example.botscrewtechtask.service.impl;

import com.example.botscrewtechtask.domain.Department;
import com.example.botscrewtechtask.domain.Lector;
import com.example.botscrewtechtask.repository.DepartmentRepository;
import com.example.botscrewtechtask.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public String getHeadName(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        return department.getHeadLector().getName() + " " + department.getHeadLector().getSurname();
    }

    @Override
    public Map<String, Integer> getStatistic(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        Map<String, Integer> departmentStatistic = new HashMap<>();

        List<Lector> lectors = department.getLectors();
        int assistantsCount = 0;
        int associateProfessorsCount = 0;
        int professorsCount = 0;

        for (Lector lector : lectors) {
            if (lector.getDegree() == Lector.Degree.ASSISTANT) {
                assistantsCount++;
            } else if (lector.getDegree() == Lector.Degree.ASSOCIATE_PROFESSOR) {
                associateProfessorsCount++;
            } else if (lector.getDegree() == Lector.Degree.PROFESSOR) {
                professorsCount++;
            }
        }

        departmentStatistic.put("assistantsCount", assistantsCount);
        departmentStatistic.put("associateProfessorsCount", associateProfessorsCount);
        departmentStatistic.put("professorsCount", professorsCount);

        return departmentStatistic;
    }

    @Override
    public Integer getAverageSalary(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);

        List<Lector> lectors = department.getLectors();

        Integer sum = lectors.stream()
                .map(Lector::getSalary)
                .reduce(Integer::sum).get();

        return sum / lectors.size();
    }

    @Override
    public Integer getCountOfEmployee(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        List<Lector> lectors = department.getLectors();

        return lectors.size();
    }
}
