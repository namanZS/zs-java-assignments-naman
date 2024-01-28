package com.zs.assignment7.services;

import com.zs.assignment7.Repository.DepartmentRepository;

import java.sql.SQLException;

public class DepartmentService {
    static DepartmentRepository departmentRepository=new DepartmentRepository();
    public void AllotDepartments() throws SQLException {
        departmentRepository.createDepartmentTable();
        departmentRepository.createDepartments();
        departmentRepository.assignDepartments();

    }
}
