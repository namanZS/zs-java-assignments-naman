package com.zs.assignment7.services;

import com.zs.assignment7.Repository.BuildConnection;
import com.zs.assignment7.Repository.DepartmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DepartmentService {
    private static final Logger logger = LogManager.getLogger(DepartmentService.class);

    private static DepartmentRepository departmentRepository;
    private Connection connection;

    public DepartmentService() throws SQLException, IOException {
        departmentRepository=new DepartmentRepository();
        try{
            connection = BuildConnection.getConnection();
            departmentRepository.createDepartmentTable(connection);
            departmentRepository.createDepartments(connection);
        } catch (SQLException e) {
            logger.error("Error in accessing student db connection");
            throw new SQLException(e);

        }
        catch(IOException e){
            throw new IOException(e);

        }
    }

    public void allotDepartments() throws SQLException {
        logger.info("Department Assigning......");
        departmentRepository.assignDepartments(connection);
        logger.info("Department Assigned Successfully!!");


    }
}
