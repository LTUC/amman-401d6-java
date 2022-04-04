package com.asac.security.web;

import com.asac.security.domain.Department;
import com.asac.security.infrastructure.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/departments")
    ResponseEntity<Department> newDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.createNewDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping("/departments/exams")
    String showExamResults() {
        System.out.println("Hello world");
        departmentService.calculateExamMarks();
        System.out.println("Hello world Again");
        return "exams";
    }

    @GetMapping("/department")
    public String getDepartmentPage(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());

        return "department";
    }

    @PostMapping("/department")
    public RedirectView addDepartment(@RequestParam String department) {
        departmentService.createNewDepartment(new Department(department));

        return new RedirectView("/department");
    }
}
