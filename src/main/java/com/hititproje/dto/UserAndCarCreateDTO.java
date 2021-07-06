package com.hititproje.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserAndCarCreateDTO {

    @NotNull(message = "{hitit_proje.constraints.userName.NotNull.message}")
    @Size(min = 5, max = 20, message = "{hitit_proje.constraints.userName.Size.message}")
    private String userName;

    @NotNull(message = "{hitit_proje.constraints.firstName.NotNull.message}")
    @Size(min = 2, max = 25, message = "{hitit_proje.constraints.firstName.Size.message}")
    private String firstName;

    @Size(min = 2, max = 25, message = "{hitit_proje.constraints.lastName.Size.message}")
    private String lastName;

    @Min(value=18, message="{hitit_proje.constraints.ageMin.message}")
    @Max(value=150, message="{hitit_proje.constraints.ageMax.message}")
    private int age;

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
