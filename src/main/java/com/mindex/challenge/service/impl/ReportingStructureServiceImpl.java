package com.mindex.challenge.service.impl;

import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    private int reportCounter;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating reporting structure for employee with id [{}]", id);

        ReportingStructure reportingStructure = new ReportingStructure();
        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        reportingStructure.setEmployee(employee);

        reportCounter = 0;
        findReports(employee.getEmployeeId());
        reportingStructure.setNumberOfReports(reportCounter);

        return reportingStructure;
    }

    private void findReports(String empBaseId) {
        if(employeeRepository.findByEmployeeId(empBaseId).getDirectReports() != null) {
            for(Employee empSub : employeeRepository.findByEmployeeId(empBaseId).getDirectReports()) {
                reportCounter++;
                findReports(empSub.getEmployeeId());
            }
        }
    }
}
