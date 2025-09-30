package model.services;

import java.util.Arrays;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
    public List<Department> findAll() {
        List<Department> list = Arrays.asList(
                new Department(1, "Books"),
                new Department(2, "Computers"),
                new Department(3, "Electronics"));
        return list;
    }
}
