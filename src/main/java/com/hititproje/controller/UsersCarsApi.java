package com.hititproje.controller;
import com.hititproje.dto.UserAndCarCreateDTO;
import com.hititproje.dto.UserViewDTO;
import com.hititproje.dto.UsersCarCreateDTO;
import com.hititproje.dto.UsersCarViewDTO;
import com.hititproje.service.UsersCarService;
import com.hititproje.shared.GenericResponse;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class UsersCarsApi {

    //Must add error handling

    private final UsersCarService usersCarService;

    //WILL UPDATE
    @GetMapping("/{id}")
    public ResponseEntity<UsersCarViewDTO> getUsersCarById(@PathVariable("id") Long id) throws NotFoundException {
        final UsersCarViewDTO usersCar = usersCarService.getUsersCarById(id);
        return ResponseEntity.ok(usersCar);
    }

    //WILL UPDATE
    @GetMapping("")
    public ResponseEntity<List<UsersCarViewDTO>> getUsersCar(){
        final List<UsersCarViewDTO> usersCar = usersCarService.getUsersAllCars();
        return ResponseEntity.ok(usersCar);
    }

    @GetMapping("/update/newCar")
    public String createCarViewPage(Model model, @ModelAttribute("user") UserViewDTO userViewDTO){
        UsersCarCreateDTO car = new UsersCarCreateDTO();
        car.setUserId(userViewDTO.getId());
        model.addAttribute("car", car);
        return "new_car";
    }

    @PostMapping("/update/newCar")
    public String createUsersCar(@ModelAttribute("car") UsersCarCreateDTO usersCarCreateDTO){
        usersCarService.createCar(usersCarCreateDTO);
        return "redirect:/";
    }

    //WILL UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UsersCarViewDTO> updateUser(@PathVariable("id") Long id, @RequestBody UsersCarCreateDTO usersCarUpdateDTO){
        final UsersCarViewDTO updatedUsersCar = usersCarService.updateCar(id, usersCarUpdateDTO);
        return ResponseEntity.ok(updatedUsersCar);
    }

    //WILL UPDATE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        usersCarService.deleteCar(id);
        return ResponseEntity.ok(new GenericResponse("Users Car Deleted!"));
    }

}

