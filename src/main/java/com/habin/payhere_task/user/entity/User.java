package com.habin.payhere_task.user.entity;

import com.habin.payhere_task.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static com.habin.payhere_task.common.security.Role.USER;
import static java.util.Collections.singletonList;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @Column(length = 30)
    @Comment("사용자 이메일")
    private String email;

    @Column(length = 20, nullable = false)
    @Comment("사용자 패스워드")
    private String password;

    @Column(length = 20, nullable = false)
    @Comment("사용자 닉네임")
    private String nickname;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singletonList(USER);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
