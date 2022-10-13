package uz.pdp.springbootdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootdemo.dto.UserDto;
import uz.pdp.springbootdemo.entity.User;
import uz.pdp.springbootdemo.payload.ApiResponse;
import uz.pdp.springbootdemo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userservice;


    @GetMapping("/{id}")
    public HttpEntity<?> getUserById(@PathVariable Integer id){
        User userById = userservice.getUserById(id);
        return ResponseEntity.ok(userById);
    }


    // @ModelAttribute

//    @GetMapping
//    public String getAllUsers(@RequestParam(name = "page", defaultValue = "1") int page,
//                             @RequestParam(name = "size", defaultValue = "5") int size,
//                             Model model
//    ) {
//
//        List<User> allUsersFromDb = userservice.getAllUsersFromDb(page, size);
//        model.addAttribute("users", allUsersFromDb);
//        return "view-users";
//    }

//    @GetMapping("/get-form")
//    public String getUserForm(@ModelAttribute(name = "userDto") UserDto userDto) {
//        return "register-form";
//    }


    @PostMapping
    public HttpEntity<?> saveUser(@Valid @RequestBody UserDto userDto,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            return "register-form";
            throw new IllegalStateException("Bad request");
        }
        userservice.saveUser(userDto);
        return ResponseEntity.ok(new ApiResponse(userDto.getFullName() + " is successfully registered!!!", true, null));
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userservice.deleteUserById(id);
        return "redirect:/users";
    }
}
