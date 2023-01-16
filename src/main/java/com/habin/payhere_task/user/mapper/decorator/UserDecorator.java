package com.habin.payhere_task.user.mapper.decorator;

import com.habin.payhere_task.user.dto.SignUpRequestDto;
import com.habin.payhere_task.user.entity.User;
import com.habin.payhere_task.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

@Primary
public abstract class UserDecorator implements UserMapper {

    @Autowired
    @Qualifier("delegate")
    private UserMapper delegate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User toEntity(SignUpRequestDto signUpRequestDto) {
        User user = delegate.toEntity(signUpRequestDto);

        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        return user.toBuilder()
                .password(encodedPassword)
                .build();
    }
}
