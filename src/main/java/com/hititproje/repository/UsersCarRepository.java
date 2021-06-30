package com.hititproje.repository;

import com.hititproje.model.UsersCar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersCarRepository extends CrudRepository<UsersCar, Long> {

}
