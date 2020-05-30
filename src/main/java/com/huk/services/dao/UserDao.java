package com.huk.services.dao;

import com.huk.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity, Long> {

    UserEntity findOneByEmail(String email);
}
