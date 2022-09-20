package uz.pdp.springbootdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootdemo.dto.UserDto;
import uz.pdp.springbootdemo.entity.User;
import uz.pdp.springbootdemo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userservice;


    // @ModelAttribute

    @GetMapping
    public String getAllUsers(@RequestParam(name = "page", defaultValue = "1") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size,
                             Model model
    ) {

        List<User> allUsersFromDb = userservice.getAllUsersFromDb(page, size);
        model.addAttribute("users", allUsersFromDb);
        return "view-users";
    }

    @GetMapping("/get-form")
    public String getUserForm(@ModelAttribute(name = "userDto") UserDto userDto) {
        return "register-form";
    }


    @PostMapping
    public String saveUser(@Valid UserDto userDto,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register-form";
        }
        userservice.saveUser(userDto);
        return "redirect:/users";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userservice.deleteUserById(id);
        return "redirect:/users";
    }
}
