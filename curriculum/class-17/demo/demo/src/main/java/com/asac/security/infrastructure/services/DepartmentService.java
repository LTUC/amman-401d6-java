package com.asac.security.infrastructure.services;

import com.asac.security.domain.Account;
import com.asac.security.domain.Department;

import java.util.List;

public interface DepartmentService {
    void calculateExamMarks();
    Account onBoardUser(Account user);
    void testUsers(List<Account> users);
    Account findUserByUsername(String username);
    List<Department> getAllDepartments();
    Department createNewDepartment(Department department);
    Department findASingleDepartment(Long departmentId);
    void deleteProfile(Long profileId);
}
