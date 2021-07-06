package com.hititproje.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UsersCarCreateDTO {

    @NotNull(message = "{hitit_proje.constraints.carModel.NotNull.message}")
    private Long userId;

    @NotNull(message = "{hitit_proje.constraints.carName.NotNull.message}")
    @Size(min = 4, max = 30, message = "{hitit_proje.constraints.carName.Size.message}")
    private String carName;

    @NotNull(message = "{hitit_proje.constraints.carModel.NotNull.message}")
    @Size(min = 2, max = 25, message = "{hitit_proje.constraints.carModel.Size.message}")
    private String carModel;

    @Min(value=0, message="{hitit_proje.constraints.carAgeMin.message}")
    @Max(value=100, message="{hitit_proje.constraints.carAgeMax.message}")
    private int carAge;

}
