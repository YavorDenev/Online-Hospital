package DigRz.OnlineHospital.unit;

import DigRz.OnlineHospital.constants.Role;
import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.PatientRepository;
import DigRz.OnlineHospital.repositories.UserRepository;
import DigRz.OnlineHospital.services.AppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    @InjectMocks
    private AppointmentService appointmentService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    AppointmentRepository appointmentRepository;

    @Test
    public void testGetCurrentPatient() {
        Patient patient = new Patient();

        Authentication auth = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String username = "test_user";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        user.setRole(Role.ROLE_PATIENT);

        when(auth.getName()).thenReturn(username);
        when(userRepository.getUserByUsername(username)).thenReturn(user);
        when(patientRepository.findByUser(user)).thenReturn(patient);

        Patient result = appointmentService.getCurrentPatient();

        Assertions.assertEquals(patient, result);
    }

    @Test
    public void testSaveNewAppointment() {
        Appointment mockAppointment = mock(Appointment.class);

        Authentication auth = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Patient patient = new Patient();
        when(appointmentService.getCurrentPatient()).thenReturn(patient);

        appointmentService.saveNewAppointment(mockAppointment);

        verify(appointmentRepository).save(any(Appointment.class));
    }

}
