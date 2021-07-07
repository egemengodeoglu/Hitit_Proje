package com.hititproje.service;

import com.hititproje.dto.UsersCarCreateDTO;
import com.hititproje.dto.UsersCarViewDTO;
import com.hititproje.model.User;
import com.hititproje.repository.UserRepository;
import com.hititproje.model.UsersCar;
import com.hititproje.repository.UsersCarRepository;
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
    private static final String CARNOTFOUNDSTRING = "Car Not Found Exception";
    private static final String USERNOTFOUNDSTRING = "User Not Found Exception";

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UsersCarViewDTO getUsersCarById(Long id) {
        UsersCar usersCar = usersCarRepository.findById(id).orElseThrow(() -> new com.hititproje.exception.NotFoundException(CARNOTFOUNDSTRING));
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
        final UsersCar usersCar = usersCarRepository.findById(id).orElseThrow(() -> new com.hititproje.exception.NotFoundException(CARNOTFOUNDSTRING));
        usersCar.setCarName(usersCarUpdateDTO.getCarName());
        usersCar.setCarModel(usersCarUpdateDTO.getCarModel());
        usersCar.setCarAge(usersCarUpdateDTO.getCarAge());
        final UsersCar updatedUsersCar = usersCarRepository.save(usersCar);
        return UsersCarViewDTO.of(updatedUsersCar);
    }



    @Override
    public UsersCarViewDTO createCar(UsersCarCreateDTO usersCarCreateDTO) {
        User user = userRepository.findById(usersCarCreateDTO.getUserId()).orElseThrow(() -> new com.hititproje.exception.NotFoundException(USERNOTFOUNDSTRING));
        UsersCar usersCar = new UsersCar();
        BeanUtils.copyProperties(usersCarCreateDTO, usersCar);
        usersCar.setUser(user);
        final UsersCar createdCar = usersCarRepository.save(usersCar);
        return UsersCarViewDTO.of(createdCar);

    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        final UsersCar usersCar = usersCarRepository.findById(id).orElseThrow(() -> new com.hititproje.exception.NotFoundException(CARNOTFOUNDSTRING));
        usersCarRepository.deleteById(usersCar.getId());
    }

}
