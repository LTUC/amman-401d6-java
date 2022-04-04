package com.asac.demo12.Controllers;

import com.asac.demo12.Models.Student;
import com.asac.demo12.Repositries.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/addStudentTest") // just for testing
    public void addStudent(){
//        Student std1 = new Student("roaa",1234);
//        studentRepository.save(std1);

    }

    @PostMapping("/addstudent")
    public RedirectView createNewStudent(@ModelAttribute Student student){ //modelAttribute when working with forms
    studentRepository.save(student);
    return new RedirectView("allstudents");
    }

    @GetMapping("/allstudents")
    public String getAllStudents(Model model){
        System.out.println("*************************************************************");
        model.addAttribute("studentsList",studentRepository.findAll());
        return "home";
    }
}