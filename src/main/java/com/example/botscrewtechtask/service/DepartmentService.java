package com.example.botscrewtechtask.service;

import java.util.Map;

public interface DepartmentService {

    String getHeadName(String departmentName);

    Map<String, Integer> getStatistic(String departmentName);

    Integer getAverageSalary(String departmentName);

    Integer getCountOfEmployee(String departmentName);

}
