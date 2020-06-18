package com.huk.services;

import com.huk.entities.UserEntity;
import com.huk.entities.UserRoleEntity;
import com.huk.enums.UserRole;
import com.huk.exception.ResourceNotFoundException;
import com.huk.services.dao.UserDao;
import com.huk.web.CreateUserDto;
import com.huk.web.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDao user,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = user;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto saveUser(CreateUserDto user) {
        UserEntity userEntity = transformDtoToEntity(user);

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRole.USER);
        userRoleEntity.setUser(userEntity);

        userEntity.setUserRoles(Collections.singletonList(userRoleEntity));
        return transformEntityToDto(userDao.save(userEntity));
    }

    private UserEntity transformDtoToEntity(CreateUserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setLogin((user.getLogin()));
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

    @Override
    //индификатор по которому будем определять юзера, это Email
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserEntity userEntity = userDao.findOneByEmail(email);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User with email not found: " + email);
        }

        List<GrantedAuthority> authorities = userEntity.getUserRoles()
                                                       .stream()
                                                       .map(this::toAuthority)
                                                       .collect(Collectors.toList());

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    private GrantedAuthority toAuthority(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority((userRole.getRole().name()));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public List<com.huk.services.User> getAllUsers() {
        return Collections.emptyList();
    }
}
