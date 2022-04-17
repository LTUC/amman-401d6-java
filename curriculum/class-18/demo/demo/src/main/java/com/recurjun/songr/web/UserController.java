package com.recurjun.songr.web;

import com.recurjun.songr.data.Album;
import com.recurjun.songr.data.User;
import com.recurjun.songr.infrastructure.UserDAO;
import com.recurjun.songr.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDAO userDAO;

    @GetMapping("/users")
    String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "users";
    }

    @GetMapping("/users/hack")
    String getUser(@RequestParam String name, Model model) {
//        List<User> users = userRepository.getStuff(name);
        List<User> user = userDAO.unsafeFindAccountsByName(name);
        model.addAttribute("users", user);
//        model.addAttribute("users", userRepository.getStuff(name));

        return "users";

    }

    @PostMapping("/users")
    RedirectView saveUser(@RequestParam String name) {

        userRepository.save(new User(name));

        return new RedirectView("/users");
    }

    @Transactional
    @GetMapping("/follow/{id}")
    String showFollowSuccessScreen(@PathVariable("id") long id, Model model) {

        // user to follow
        User usertofollow = userRepository.findById(id).orElseThrow();

        // get current logged in user username

        // use the user name or ID to find the user by username or ID

        // once you have that object

        // add the curetn logged in user to the following of usertofollow

        // add usertofollow to current logged in user followers

        // update both the userToFollow and the current logged in User to the DB by calling save


        // HARD CODED EXAMPLE OF IT
        User currentLoggedInUser = userRepository.findById(1L).orElseThrow();
        currentLoggedInUser.getFollowers().add(usertofollow);

        usertofollow.getFollowing().add(currentLoggedInUser);

        userRepository.save(usertofollow);
        userRepository.save(currentLoggedInUser);

        return "success";
    }
}
