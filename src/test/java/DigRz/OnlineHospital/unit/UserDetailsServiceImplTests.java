package DigRz.OnlineHospital.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import DigRz.OnlineHospital.constants.Role;
import DigRz.OnlineHospital.dto.DoctorReg;
import DigRz.OnlineHospital.dto.PatientReg;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.UserRepository;
import DigRz.OnlineHospital.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setPassword("password");
        user.setEnabled(true);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encoded_password");

        userDetailsService.saveUser(user);

        verify(userRepository).save(user);
        assertEquals("encoded_password", user.getPassword());
    }

    @Test
    public void testSaveUserAsAdmin() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encoded_password");

        userDetailsService.saveUserAsAdmin(user);

        verify(userRepository).save(user);
        assertEquals("encoded_password", user.getPassword());
        assertEquals(Role.ROLE_ADMIN, user.getRole());
    }

    @Test
    public void testSaveUserAsPatient() {
        PatientReg patientReg = new PatientReg();
        patientReg.setUsername("username");
        patientReg.setPassword("password");
        when(passwordEncoder.encode(patientReg.getPassword())).thenReturn("encoded_password");

        userDetailsService.saveUserAsPatient(patientReg);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User user = userArgumentCaptor.getValue();
        assertEquals("username", user.getUsername());
        assertEquals("encoded_password", user.getPassword());
        assertEquals(Role.ROLE_PATIENT, user.getRole());
    }

    @Test
    public void testSaveUserAsDoctor() {
        DoctorReg doctorReg = new DoctorReg();
        doctorReg.setUsername("username");
        doctorReg.setPassword("password");
        when(passwordEncoder.encode(doctorReg.getPassword())).thenReturn("encoded_password");

        userDetailsService.saveUserAsDoctor(doctorReg);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User user = userArgumentCaptor.getValue();
        assertEquals("username", user.getUsername());
        assertEquals("encoded_password", user.getPassword());
        assertEquals(Role.ROLE_DOCTOR, user.getRole());
    }

    @Test
    public void testIsUserExistWhenExists() {
        String username = "username";
        when(userRepository.getUserByUsername(username)).thenReturn(new User());

        assertTrue(userDetailsService.isUserExist(username));
    }

    @Test
    public void testIsUserExistWhenNotExists() {
        String username = "no_such_user";
        when(userRepository.getUserByUsername(username)).thenReturn(null);

        assertFalse(userDetailsService.isUserExist(username));
    }

    @Test
    void testLoadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {
        String username = "username";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        user.setRole(Role.ROLE_PATIENT);
        when(userRepository.getUserByUsername(username)).thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsername_ShouldThrowUsernameNotFoundException_WhenUserDoesNotExist() {
        String username = "username";
        when(userRepository.getUserByUsername(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
    }

}
