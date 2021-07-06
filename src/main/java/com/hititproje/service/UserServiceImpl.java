package com.hititproje.service;

import com.hititproje.dto.*;
import com.hititproje.model.UsersCar;
import com.hititproje.repository.UserRepository;
import com.hititproje.exception.NotFoundException;
import com.hititproje.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UsersCarService usersCarService;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS) //SOR
    public UserViewDTO getUserById(Long id){
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if(optional.isPresent()){
            user = optional.get();
        }else{
            throw new RuntimeException("User not found for id:"+id);
        }
        return UserViewDTO.of(user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserViewDTO> getAllUsers() {
        final List<UserViewDTO> usersList = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        for(User user : iterable){
            usersList.add(UserViewDTO.of(user));
        }
        return usersList;
    }

    @Override
    public UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Exception"));
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setAge(userUpdateDTO.getAge());
        final User updatedUser = userRepository.save(user);
        return UserViewDTO.of(updatedUser);
    }

    @Override
    public UserViewDTO createUser(UserAndCarCreateDTO userAndCar) {
        UsersCarCreateDTO createdCar = new UsersCarCreateDTO();
        UserCreateDTO createdUser = new UserCreateDTO();
        BeanUtils.copyProperties(userAndCar, createdCar);
        BeanUtils.copyProperties(userAndCar, createdUser);
        final User user = userRepository.save(new User(createdUser.getUserName(), createdUser.getFirstName(), createdUser.getLastName(), createdUser.getAge()));
        createdCar.setUserId(user.getId());
        usersCarService.createCar(createdCar);
        return UserViewDTO.of(user);
    }

    @Override
    public void deleteUser(Long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Exception"));
        userRepository.deleteById(user.getId());
    }

    @Override
    public List<UsersCarViewDTO> getUsersCars(Long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new com.hititproje.exception.NotFoundException("Object Found Exception"));
        List<UsersCarViewDTO> listCars = new ArrayList<>();
        for(UsersCar car : user.getUsersCar()){
            listCars.add(UsersCarViewDTO.of(car));
        }
        return listCars;
    }

}
