package com.example.botscrewtechtask;

import com.example.botscrewtechtask.domain.Department;
import com.example.botscrewtechtask.domain.Lector;
import com.example.botscrewtechtask.repository.DepartmentRepository;
import com.example.botscrewtechtask.repository.LectorRepository;
import com.example.botscrewtechtask.service.DepartmentService;
import com.example.botscrewtechtask.service.LectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class BotsCrewTechTaskApplication implements CommandLineRunner {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LectorService lectorService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LectorRepository lectorRepository;

    private static Logger LOG = LoggerFactory.getLogger(BotsCrewTechTaskApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(BotsCrewTechTaskApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @PostConstruct
    private void initDB() {
        Department department1 = new Department(1l, "department1");
        Department department2 = new Department(2l, "department2");

        Lector lector1 = new Lector(1L, "John", "Smith", Lector.Degree.PROFESSOR, 2000, department1, Arrays.asList(department1, department2));
        Lector lector2 = new Lector(2L, "John", "Singer", Lector.Degree.ASSOCIATE_PROFESSOR, 1200, Arrays.asList(department1, department2));
        Lector lector3 = new Lector(3L, "Elon", "Musk", Lector.Degree.ASSOCIATE_PROFESSOR, 1500, department2, Arrays.asList(department1, department2));
        Lector lector4 = new Lector(4L, "Harry", "Potter", Lector.Degree.ASSISTANT, 1000, Arrays.asList(department1, department2));

        List<Department> departmentList = Arrays.asList(department1, department2);
        List<Lector> lectorList = Arrays.asList(lector1, lector2, lector3, lector4);

        departmentRepository.saveAll(departmentList);
        lectorRepository.saveAll(lectorList);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("EXECUTING : command line runner");

        Scanner scanner = new Scanner(System.in);

        LOG.info("------------------// 1 command //-----------------------");

        System.out.println("Enter department name:");

        String departmentName1 = scanner.nextLine();
        String headOfDepartment = departmentService.getHeadName(departmentName1);

        System.out.println("Head of " + departmentName1 + " is " + headOfDepartment);

        LOG.info("------------------// 2 command //-----------------------");

        System.out.println("Enter department name:");

        String departmentName2 = scanner.nextLine();
        Map<String, Integer> statistic = departmentService.getStatistic(departmentName2);

        String statisticDepartment = "assistans - " + statistic.get("assistantsCount") + "\nassociate professors - "
                + statistic.get("associateProfessorsCount") + "\nprofessors - "
                + statistic.get("professorsCount");

        System.out.println(statisticDepartment);

        LOG.info("------------------// 3 command //-----------------------");

        System.out.println("Enter department name:");

        String departmentName3 = scanner.nextLine();
        int averageSalary = departmentService.getAverageSalary(departmentName3);

        System.out.println("The average salary " + departmentName3 + " is " + averageSalary);

        LOG.info("------------------// 4 command //-----------------------");

        System.out.println("Enter department name:");

        String departmentName4 = scanner.nextLine();
        int countOfEmployee = departmentService.getCountOfEmployee(departmentName4);

        System.out.println("The " + departmentName3 + " have " + countOfEmployee + " employees");

        LOG.info("------------------// 5 command //-----------------------");

        System.out.println("Enter lector name:");

        String name = scanner.nextLine();
        List<String> lectors = lectorService.searchAllByName(name);

        System.out.println(lectors);
    }
}
