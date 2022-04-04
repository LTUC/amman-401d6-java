package com.asac.security.web;

import com.asac.security.domain.Account;
import com.asac.security.infrastructure.services.DepartmentService;
import com.asac.security.infrastructure.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    SecurityService securityService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("department", departmentService.findUserByUsername(userDetails.getUsername()).getDepartment().getName());
        return "profile";
    }

    @PostMapping("/signup")
    public RedirectView attemptSignUp(@RequestParam String username, @RequestParam String password, @RequestParam Long department, @RequestParam Long role) {
        Account newAccount = new Account(username, encoder.encode(password));
        newAccount.setDepartment(departmentService.findASingleDepartment(department));
        newAccount.setRole(securityService.findRoleById(role));
        newAccount = departmentService.onBoardUser(newAccount);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newAccount, null, newAccount.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }

    @GetMapping("/delete")
    public String showdeleteForm() {
        return "delete";
    }

    @PostMapping("/delete")
    public RedirectView deleteProfile(@RequestParam Long profileId) {
        departmentService.deleteProfile(profileId);

        return new RedirectView("/delete");
    }

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "/403";
    }
}
