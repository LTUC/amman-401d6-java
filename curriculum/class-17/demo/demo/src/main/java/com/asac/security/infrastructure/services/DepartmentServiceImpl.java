package com.asac.security.infrastructure.services;

import com.asac.security.domain.Account;
import com.asac.security.domain.Department;
import com.asac.security.infrastructure.DepartmentRepository;
import com.asac.security.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    UserRepository userRepository; // no modifier means package private

    @Autowired
    DepartmentRepository departmentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void calculateExamMarks() {
        System.out.print("Marks calculated");
    }

    @Override
    public Account onBoardUser(Account user) {
        return userRepository.save(user);
    }

    @Override
    public void testUsers(List<Account> users) {
        System.out.println("Hand out exam booklets");
        System.out.println("Start timer");
        System.out.println("When timer is up");
        System.out.println("Take up exam booklets");
    }

    @Override
    public Account findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department createNewDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department findASingleDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow();
    }

    @Override
    public void deleteProfile(Long profileId) {
        userRepository.deleteById(profileId);
    }
}
