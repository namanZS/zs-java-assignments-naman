package com.zs.assignment7.controller;

import com.zs.assignment7.services.DepartmentService;

import java.sql.SQLException;

public class DepartmentController {

    public  void AssignDepartments() throws SQLException {
        DepartmentService departmentService=new DepartmentService();
        departmentService.AllotDepartments();
    }
}
