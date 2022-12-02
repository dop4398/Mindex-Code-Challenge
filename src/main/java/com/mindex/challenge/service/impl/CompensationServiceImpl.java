package com.mindex.challenge.service.impl;

import org.springframework.stereotype.Service;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.dao.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class CompensationServiceImpl  implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        // compensation.setEmployee(null);
        // compensation.setSalary(0);
        // compensation.setEffectiveDate(null);

        // add compensation to some list?

        return compensation;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Retrieving compensation for employee with id [{}]", id);

        Compensation compensation = new Compensation();
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        compensation.setEmployee(employee);
    
        // persistance...? read from a persistant list?
        return compensation;
    }
}
