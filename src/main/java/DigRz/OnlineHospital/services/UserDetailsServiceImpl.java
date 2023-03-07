package DigRz.OnlineHospital.services;

import DigRz.OnlineHospital.config.MyUserDetails;
import DigRz.OnlineHospital.constants.Role;
import DigRz.OnlineHospital.dto.DoctorReg;
import DigRz.OnlineHospital.dto.PatientReg;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        userRepo.save(user);
    }

    public void saveUserAsAdmin(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.setRole(Role.ROLE_ADMIN);
        userRepo.save(user);
    }

    public void saveUserAsPatient(PatientReg patientReg) {
        User user = new User();
        String encodedPassword = bCryptPasswordEncoder.encode(patientReg.getPassword());
        user.setPassword(encodedPassword);
        user.setUsername(patientReg.getUsername());
        user.setEnabled(true);
        user.setRole(Role.ROLE_PATIENT);
        userRepo.save(user);
    }

    public void saveUserAsDoctor(DoctorReg doctorReg) {
        User user = new User();
        String encodedPassword = bCryptPasswordEncoder.encode(doctorReg.getPassword());
        user.setPassword(encodedPassword);
        user.setUsername(doctorReg.getUsername());
        user.setEnabled(true);
        user.setRole(Role.ROLE_DOCTOR);
        userRepo.save(user);
    }
    public Boolean isUserExist(String username) {
        User u = userRepo.getUserByUsername(username);
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
