package com.huk.services;

import com.huk.entities.UserEntity;
import com.huk.services.dao.UserDao;
import com.huk.web.CreateUserDto;
import com.huk.web.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDao user,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = user;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto saveUser(CreateUserDto user) {
        UserEntity userEntity = transformDtoToEntity(user);
        return transformEntityToDto(userDao.save(userEntity));
    }

//    public void findUserByEmail(String email){
//        userDao.findOneByEmail(email);
//    }

    private UserEntity transformDtoToEntity(CreateUserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userEntity.getEmail());
        userEntity.setLogin((userEntity.getLogin()));
        String encodeStr = bCryptPasswordEncoder.encode(user.getPassword());
        userEntity.setPassword(encodeStr);
        return userEntity;
    }

    private UserDto transformEntityToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setLogin(userEntity.getLogin());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }
}
