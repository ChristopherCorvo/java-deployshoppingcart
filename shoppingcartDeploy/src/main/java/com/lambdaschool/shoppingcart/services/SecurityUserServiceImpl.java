package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.exceptions.ResourceFoundException;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "securityUserService")
public class SecurityUserServiceImpl
    implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws ResourceFoundException
    {
      User user = userRepository.findByUsername(s.toLowerCase());

        if(user == null)
        {
            throw new ResourceFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
            user.getPassword(),
            user.getAuthority());
    }
}
