package com.chariot.backend.userlicenseservice.service.user.authenticate;

import com.chariot.backend.userlicenseservice.persist.UserEntity;
import com.chariot.backend.userlicenseservice.persist.UserRepository;
import com.chariot.backend.userlicenseservice.persist.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity applicationUser = userRepository.findByName(username).get();
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getName(), applicationUser.getPassword(), emptyList());
    }
}