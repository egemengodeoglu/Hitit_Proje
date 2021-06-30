package com.hititproje.dto;
import com.hititproje.model.UsersCar;
import lombok.Getter;

import java.io.Serializable;

@Getter
public final class UsersCarViewDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private final String carName;
    private final String carModel;
    private final int carAge;
    private final Long id;

    private UsersCarViewDTO(String carName, String carModel, int carAge, Long id) {
        this.carName = carName;
        this.carModel = carModel;
        this.carAge = carAge;
        this.id = id;
    }

    public static UsersCarViewDTO of(UsersCar usersCar){
        return new UsersCarViewDTO(usersCar.getCarName(), usersCar.getCarModel(), usersCar.getCarAge(), usersCar.getId());
    }
}
