package com.dasser.ControlAccess.backend.security.Principal;

import com.dasser.ControlAccess.backend.domain.model.entity.Admin;
import com.dasser.ControlAccess.backend.domain.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PrincipalUser implements UserDetails {
    User user;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority>authorities;
   public PrincipalUser(String username,String password,Collection<? extends GrantedAuthority>authorities){
       this.username=username;
       this.password=password;
       this.authorities=authorities;
   }
    public static PrincipalUser build(User user){
        List<GrantedAuthority> authorities=
                user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .name())).collect(Collectors.toList());
        return new PrincipalUser(user.getUsername(), user.getPassword(),authorities );

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
