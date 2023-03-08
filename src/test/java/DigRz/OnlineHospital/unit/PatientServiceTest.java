package DigRz.OnlineHospital.unit;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import DigRz.OnlineHospital.dto.PatientReg;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.entities.User;
import DigRz.OnlineHospital.repositories.PatientRepository;
import DigRz.OnlineHospital.repositories.UserRepository;
import DigRz.OnlineHospital.services.PatientService;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PatientService patientService;

    private PatientReg patientReg;

    @Test
    public void testSavePatient() {
        patientReg = new PatientReg();
        patientReg.setFirstName("Ivan");
        patientReg.setLastName("Petrov");
        patientReg.setAge(25);
        patientReg.setUsername("ivan123");
        User user = new User();
        user.setUsername("ivan123");
        when(userRepository.getUserByUsername("ivan123")).thenReturn(user);

        patientService.savePatient(patientReg);

        verify(patientRepository).save(any(Patient.class));
    }
}