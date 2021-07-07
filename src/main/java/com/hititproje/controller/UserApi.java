package com.hititproje.controller;

import com.hititproje.dto.*;
import com.hititproje.service.UserService;
import com.hititproje.service.UsersCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class UserApi {

    String mainPage = "redirect:/";
    String createUserPage = "new_user";
    String createCarPage = "new_car";
    String updateUserPage = "update_user";

    private final UserService userService;
    private final UsersCarService usersCarService;

    @GetMapping("/")
    public String getUsersViewPage(Model model){
        List<UserViewDTO> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "index";
    }

    @GetMapping("/newUser")
    public String createUserViewPage(Model model){
        UserAndCarCreateDTO userAndCarCreateDTO = new UserAndCarCreateDTO();
        model.addAttribute("userAndCar", userAndCarCreateDTO);
        return createUserPage;
    }

    @PostMapping("/newUser")
    public String createUser(@Valid @ModelAttribute("userAndCar") UserAndCarCreateDTO userAndCarCreateDTO , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return createUserPage;
        }
        userService.createUser(userAndCarCreateDTO);
        return mainPage;
    }

    @GetMapping("/update/newCar/{id}")
    public String createCarViewPage(@PathVariable("id") Long id, Model model){
        UsersCarCreateDTO car = new UsersCarCreateDTO();
        car.setUserId(id);
        model.addAttribute("car", car);
        return createCarPage;
    }

    @PostMapping("/update/newCar/{id}")
    public String createCar(@PathVariable("id") Long id, @Valid @ModelAttribute("car") UsersCarCreateDTO usersCarCreateDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return createCarPage;
        }
        usersCarCreateDTO.setUserId(id);
        usersCarService.createCar(usersCarCreateDTO);
        return mainPage;
    }

    @GetMapping("/update/{id}")
    public String updateUserViewPage(@PathVariable("id") Long id, Model model){
        UserViewDTO user = userService.getUserById(id);
        List<UsersCarViewDTO> listCars = userService.getUsersCars(id);
        model.addAttribute("userUpdate", user);
        model.addAttribute("listCars",listCars);
        return updateUserPage;
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid @ModelAttribute("userUpdate") UserUpdateDTO userUpdateDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return updateUserPage;
        }
            userService.updateUser(id, userUpdateDTO);
            return mainPage;
    }


    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return mainPage;
    }

    @PostMapping("/update/delete/{id}")
    public String usersCarDelete(@PathVariable("id") Long id){
        usersCarService.deleteCar(id);
        return mainPage;
    }



}
