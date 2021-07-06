package com.hititproje.controller;

import com.hititproje.dto.*;
import com.hititproje.service.UserService;
import com.hititproje.service.UsersCarService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class UserApi {

    String mainPage = "redirect:/";
    //Must add error handling

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
        return "new_user";
    }

    @PostMapping("/newUser")
    public String createUser(@ModelAttribute("userAndCar") UserAndCarCreateDTO userAndCarCreateDTO){
        userService.createUser(userAndCarCreateDTO);
        return mainPage;
    }

    @GetMapping("/update/newCar/{id}")
    public String createCarViewPage(@PathVariable("id") Long id, Model model){
        UsersCarCreateDTO car = new UsersCarCreateDTO();
        car.setUserId(id);
        model.addAttribute("car", car);
        return "new_car";
    }

    @PostMapping("/update/newCar/{id}")
    public String createCar(@PathVariable("id") Long id, @ModelAttribute("car") UsersCarCreateDTO usersCarCreateDTO){
        usersCarCreateDTO.setUserId(id);
        usersCarService.createCar(usersCarCreateDTO);
        return mainPage;
    }

    @GetMapping("/update/{id}")
    public String updateUserViewPage(@PathVariable("id") Long id, Model model) throws NotFoundException {
        UserViewDTO user = userService.getUserById(id);
        List<UsersCarViewDTO> listCars = userService.getUsersCars(id);
        UserUpdateDTO userUpdate = new UserUpdateDTO();
        BeanUtils.copyProperties(user, userUpdate);
        model.addAttribute("userUpdate", userUpdate);
        model.addAttribute("listCars",listCars);
        return "update_user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") UserUpdateDTO userUpdateDTO){
        userService.updateUser(id, userUpdateDTO);
        return mainPage;
    }


    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return mainPage;
    }

    //FOR CONTROL FROM DB
    @GetMapping("{id}/cars")
    public ResponseEntity<List<UsersCarViewDTO>> getUsersCars(@PathVariable("id") Long id) {
        List<UsersCarViewDTO> cars = userService.getUsersCars(id);
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/update/delete/{id}")
    public String usersCarDelete(@PathVariable("id") Long id){
        usersCarService.deleteCar(id);
        return mainPage;
    }



}
