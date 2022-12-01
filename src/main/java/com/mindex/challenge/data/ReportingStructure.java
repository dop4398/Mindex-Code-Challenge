package com.mindex.challenge.data;

// import com.mindex.challenge.dao.EmployeeRepository;
// import org.springframework.beans.factory.annotation.Autowired;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    // @Autowired
    // private EmployeeRepository employeeRepository;

    public ReportingStructure() {
        numberOfReports = 0;
    }

    public Employee getEmployee() {
        return employee;
    } 

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int i) {
        numberOfReports = i;
    }
}
