package DigRz.OnlineHospital.unit;

import static org.mockito.Mockito.*;
import DigRz.OnlineHospital.dto.DoctorReg;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.repositories.UserRepository;
import DigRz.OnlineHospital.services.DoctorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private DoctorService doctorService;

    @Test
    public void testSaveDoctor() {
        DoctorReg doctorReg = new DoctorReg();
        doctorReg.setFirstName("John");
        doctorReg.setLastName("Doe");
        doctorReg.setSpecialty("Cardiology");
        doctorReg.setUsername("johndoe");

        User user = new User();
        user.setUsername("johndoe");

        when(userRepository.getUserByUsername("johndoe")).thenReturn(user);

        doctorService.saveDoctor(doctorReg);

        verify(doctorRepository).save(any());
    }


}
