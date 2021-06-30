package com.hititproje.service;

import com.hititproje.dto.*;
import javassist.NotFoundException;

import java.util.List;


public interface UserService {

    UserViewDTO getUserById(Long id) throws NotFoundException;

    List<UserViewDTO> getAllUsers();

    UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);

    UserViewDTO createUser(UserAndCarCreateDTO userAndCar);

    void deleteUser(Long id);

    List<UsersCarViewDTO> getUsersCars(Long id);

}
