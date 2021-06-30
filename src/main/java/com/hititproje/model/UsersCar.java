package com.hititproje.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Users_cars")
public class UsersCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_name", nullable = true, length = 30)
    private String carName;

    @Column(name = "car_model", nullable = true, length = 25)
    private String carModel;

    @Column(name = "car_age", nullable = true)
    private int carAge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    public UsersCar(String carName, String carModel, int carAge, User user) {
        this.carName = carName;
        this.carModel = carModel;
        this.carAge = carAge;
        this.user=user;
    }
}
