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

    // Total number of reports under the given employee.
    // The number of reports is determined to be the number of directReports for an employee and all of their distinct reports.
    public int getNumberOfReports() {
        numberOfReports = 0;
        findReports(employee);
        return numberOfReports;
    }

    private void findReports(Employee empBase) {
        if(empBase.getDirectReports() != null) {
            for(Employee empSub : empBase.getDirectReports()) {
                numberOfReports++;
                // PROBLEM - directReports list only contain employee IDs; all other values are NULL.
                findReports(empSub);
            }
        }
    }

    // private void findReports(String empBaseId) {
    //     if(employeeRepository.findByEmployeeId(empBaseId).getDirectReports() != null) {
    //         for(Employee empSub : employeeRepository.findByEmployeeId(empBaseId).getDirectReports()) {
    //             numberOfReports++;
    //             // PROBLEM - directReports list only contain employee IDs; all other values are NULL.
    //             findReports(empSub.getEmployeeId());
    //         }
    //     }
    // }
}
