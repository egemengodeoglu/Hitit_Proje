package com.hititproje.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserUpdateDTO {

    @NotNull(message = "{hitit_proje.constraints.firstName.NotNull.message}")
    private Long id;

    @NotNull(message = "{hitit_proje.constraints.firstName.NotNull.message}")
    @Size(min = 2, max = 25, message = "{hitit_proje.constraints.firstName.Size.message}")
    private String firstName;

    @NotNull(message = "{hitit_proje.constraints.userName.NotNull.message}")
    @Size(min = 5, max = 20, message = "{hitit_proje.constraints.userName.Size.message}")
    private String userName; //Unique eklenecek

    @Size(min = 2, max = 25, message = "{hitit_proje.constraints.lastName.Size.message}")
    private String lastName;

    @Min(value=18, message="{hitit_proje.constraints.ageMin.message}")
    @Max(value=150, message="{hitit_proje.constraints.ageMax.message}")
    private int age;

}