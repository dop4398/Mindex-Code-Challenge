package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.dao.EmployeeRepository;

public interface ReportingStructureService {
    ReportingStructure read(String id);
}
