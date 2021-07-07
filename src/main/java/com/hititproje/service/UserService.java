package com.hititproje.service;

import com.hititproje.dto.*;

import java.util.List;


public interface UserService {

    UserViewDTO getUserById(Long id);

    List<UserViewDTO> getAllUsers();

    UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);

    UserViewDTO createUser(UserAndCarCreateDTO userAndCar);

    void deleteUser(Long id);

    List<UsersCarViewDTO> getUsersCars(Long id);

    //<button id="Dlt" onclick="deleteUser(`+{id}(id=${user.id}))+`)" class="btn btn-danger">Delete</button>

}
