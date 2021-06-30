package com.hititproje.dto;

import com.hititproje.model.User;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public final class UserViewDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private final String userName;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final Long id;

    private UserViewDTO(String userName, String firstName, String lastName, int age, Long id) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }

    public static UserViewDTO of(User user){
        return new UserViewDTO(user.getUserName(), user.getFirstName(), user.getLastName(), user.getAge(), user.getId());
    }
}
