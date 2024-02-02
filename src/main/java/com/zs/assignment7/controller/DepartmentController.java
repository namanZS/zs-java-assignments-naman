package com.zs.assignment7.controller;

import com.zs.assignment7.services.DepartmentService;

import java.io.IOException;
import java.sql.SQLException;

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController() throws SQLException, IOException {
        this.departmentService = new DepartmentService();
    }
    public  void assignDepartments() throws SQLException {
        departmentService.allotDepartments();
    }
}
