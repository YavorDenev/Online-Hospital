package DigRz.OnlineHospital.services;

import DigRz.OnlineHospital.config.MyUserDetails;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
//        user.setRole(Role.ROLE_PATIENT);
        userRepo.save(user);
    }

    public Boolean isUserExist(User user) {
        User u = userRepo.getUserByUsername(user.getUsername());
        return (u != null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userRepo.getUserByUsername(username);
        if (user==null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return new MyUserDetails(user);
    }

}
