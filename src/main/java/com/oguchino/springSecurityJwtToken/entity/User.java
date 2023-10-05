package com.oguchino.springSecurityJwtToken.entity;


import com.oguchino.springSecurityJwtToken.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nameSurname")
    private String nameSurname;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    Roles role;




    //user nesnesinin kullanicilarini dondurur
    // kullanicinin ne gibi rollere sahip olacagini gosterir
    //
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // kullanici hesabini suresinin dolup dolmadigin gosteren metod
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

    //
    @Override
    public boolean isEnabled() {
        return true;
    }
}
