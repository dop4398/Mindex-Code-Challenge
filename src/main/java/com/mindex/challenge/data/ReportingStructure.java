package com.mindex.challenge.data;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
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
                findReports(empSub);
            }
        }
    }
}
