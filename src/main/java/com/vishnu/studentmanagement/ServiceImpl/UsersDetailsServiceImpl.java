package com.vishnu.studentmanagement.ServiceImpl;

import com.vishnu.studentmanagement.Entity.Users;
import com.vishnu.studentmanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;


@Service
public class UsersDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid Username"));


        return User.withUsername(username).password(user.getPassword()).disabled(!user.isActive()).build();
    }
}
