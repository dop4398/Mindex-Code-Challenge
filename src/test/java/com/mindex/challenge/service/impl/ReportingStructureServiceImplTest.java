package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    private String reportingStructureIdUrl;
    private String employeeUrl;
    
    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureIdUrl = "http://localhost:" + port + "/reportingstructure/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
    }

    @Test
    public void testRead() {
        Employee testEmployee1 = new Employee();
        testEmployee1.setFirstName("John");
        testEmployee1.setLastName("Doe");
        testEmployee1.setDepartment("Engineering");
        testEmployee1.setPosition("Developer");

        Employee testEmployee2 = new Employee();
        testEmployee1.setFirstName("Mary");
        testEmployee1.setLastName("Sue");
        testEmployee1.setDepartment("Engineering");
        testEmployee1.setPosition("Developer");

        Employee createdEmployee1 = restTemplate.postForEntity(employeeUrl, testEmployee1, Employee.class).getBody();
        Employee createdEmployee2 = restTemplate.postForEntity(employeeUrl, testEmployee2, Employee.class).getBody();

        List<Employee> directReports1 = new ArrayList<Employee>();
        directReports1.add(createdEmployee2);
        createdEmployee1.setDirectReports(directReports1);

        ReportingStructure testReportingStructure = new ReportingStructure();
        testReportingStructure.setEmployee(createdEmployee1);

        // Read checks
        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, createdEmployee1.getEmployeeId()).getBody();
        assertNotNull(readReportingStructure.getEmployee().getEmployeeId());
        assertReportingStructureEquivalence(testReportingStructure, readReportingStructure);
    }

    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
    }
}
