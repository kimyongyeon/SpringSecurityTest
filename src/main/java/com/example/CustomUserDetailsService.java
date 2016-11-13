package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by yongyeonkim on 2016. 7. 19..
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    static class SecurityUser extends org.springframework.security.core.userdetails.User {
        public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }

        public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorityList = buildUserAutority(user.getUserRoles());
        return buildUserForAuthentication(user, authorityList);
    }

    private SecurityUser buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new SecurityUser(user.getUsername(), user.getPassword(), true
                , true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAutority(Set<UserRole> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<>(0);
        for(UserRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getUserRoleName()));
        }
        return authorities;
    }
}
