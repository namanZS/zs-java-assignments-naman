package com.zs.assignment7.services;

import com.zs.assignment7.Repository.DepartmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DepartmentService {
    private static final Logger logger = LogManager.getLogger(DepartmentService.class);

    private static final DepartmentRepository departmentRepository;

    static {
        try {
            departmentRepository = new DepartmentRepository();
        } catch (SQLException ex) {
            logger.error("Error in initialising department connection");
            throw new RuntimeException(ex);
        }
    }


    public void allotDepartments() throws SQLException {
        logger.info("Department Assigning......");
        departmentRepository.createDepartmentTable();
        departmentRepository.createDepartments();
        departmentRepository.assignDepartments();
        logger.info("Department Assigned Successfully!!");


    }
}
