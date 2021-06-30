package com.hititproje.controller;

import com.hititproje.dto.*;
import com.hititproje.service.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class UserApi {

    //Must add error handling

    private final UserService userService;

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
        return "redirect:/";
    }


    @GetMapping("/update/{id}")
    public String updateUserViewPage(@PathVariable("id") Long id, Model model) throws NotFoundException {
        UserViewDTO user = userService.getUserById(id);
        List<UsersCarViewDTO> listCars = userService.getUsersCars(id);
        model.addAttribute("user", user);
        model.addAttribute("listCars",listCars);
        return "update_user";
    }

    //WILL UPDATE
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid @RequestBody @ModelAttribute("user") UserViewDTO userViewDTO){
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        BeanUtils.copyProperties(userViewDTO, userUpdateDTO);
        userService.updateUser(id, userUpdateDTO);
        return "redirect:/";
    }


    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/";
    }

    //FOR CONTROL FROM DB
    @GetMapping("{id}/cars")
    public ResponseEntity<List<UsersCarViewDTO>> getUsersCars(@PathVariable("id") Long id) throws NotFoundException {
        List<UsersCarViewDTO> cars = userService.getUsersCars(id);
        return ResponseEntity.ok(cars);
    }

}
