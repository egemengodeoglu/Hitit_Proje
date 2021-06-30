package com.hititproje.service;

import com.hititproje.dto.UsersCarCreateDTO;
import com.hititproje.dto.UsersCarViewDTO;
import javassist.NotFoundException;

import java.util.List;

public interface UsersCarService {

    UsersCarViewDTO getUsersCarById(Long id) throws NotFoundException;

    List<UsersCarViewDTO> getUsersAllCars();

    UsersCarViewDTO updateCar(Long id, UsersCarCreateDTO usersCarUpdateDTO);

    UsersCarViewDTO createCar(UsersCarCreateDTO usersCarCreateDTO);

    void deleteCar(Long id);

    //List<UserViewDTO> slice(Pageable pageable);
}
