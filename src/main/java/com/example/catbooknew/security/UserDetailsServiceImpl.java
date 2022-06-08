package com.example.catbooknew.security;

import com.example.catbooknew.dto.User;
import com.example.catbooknew.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsServiceImpl")
@AllArgsConstructor
public class UserDetailsServiceImpl  implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("User doesn't exist"));
        return SecurityUser.fromUser(user);
    }
}
