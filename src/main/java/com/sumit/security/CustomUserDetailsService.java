package com.sumit.security;

import com.sumit.entity.User;
import com.sumit.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(STR."\{username} user tried to login ...");
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.info(STR."\{username} does not exists in DB");
            throw new UsernameNotFoundException("User not found !!");
        }

        log.info(STR."\{username} found in DB, returning Spring UserDetails object to AuthenticationProvider");
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(String[]::new))
                .build();
    }


}