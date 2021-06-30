package com.hititproje.service;

import com.hititproje.dto.UsersCarCreateDTO;
import com.hititproje.dto.UsersCarViewDTO;
import com.hititproje.model.User;
import com.hititproje.repository.UserRepository;
import com.hititproje.model.UsersCar;
import com.hititproje.repository.UsersCarRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersCarServiceImpl implements UsersCarService{

    private final UsersCarRepository usersCarRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS) //SOR
    public UsersCarViewDTO getUsersCarById(Long id) throws NotFoundException {
        final UsersCar usersCar = usersCarRepository.findById(id).orElseThrow(() -> new com.hititproje.exception.NotFoundException("Not Found Exception"));
        return UsersCarViewDTO.of(usersCar);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UsersCarViewDTO> getUsersAllCars() {
        final List<UsersCarViewDTO> usersCarList = new ArrayList<>();
        Iterable<UsersCar> iterable = usersCarRepository.findAll();
        for(UsersCar car : iterable){
            usersCarList.add(UsersCarViewDTO.of(car));
        }
        return usersCarList;
    }

    @Override
    @Transactional
    public UsersCarViewDTO updateCar(Long id, UsersCarCreateDTO usersCarUpdateDTO) {
        final UsersCar usersCar = usersCarRepository.findById(id).orElseThrow(() -> new com.hititproje.exception.NotFoundException("Not Found Exception"));
        usersCar.setCarName(usersCarUpdateDTO.getCarName());
        usersCar.setCarModel(usersCarUpdateDTO.getCarModel());
        usersCar.setCarAge(usersCarUpdateDTO.getCarAge());
        final UsersCar updatedUsersCar = usersCarRepository.save(usersCar);
        return UsersCarViewDTO.of(updatedUsersCar);
    }



    @Override
    public UsersCarViewDTO createCar(UsersCarCreateDTO usersCarCreateDTO) {
        User user = userRepository.findById(usersCarCreateDTO.getUserId()).orElseThrow(() -> new com.hititproje.exception.NotFoundException("User Not Found Exception"));
        UsersCar usersCar = new UsersCar();
        BeanUtils.copyProperties(usersCarCreateDTO, usersCar);
        usersCar.setUser(user);
        final UsersCar createdCar = usersCarRepository.save(usersCar);
        return UsersCarViewDTO.of(createdCar);

    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        final UsersCar usersCar = usersCarRepository.findById(id).orElseThrow(() -> new com.hititproje.exception.NotFoundException("Not Found Exception"));
        usersCarRepository.deleteById(usersCar.getId());
    }

}
