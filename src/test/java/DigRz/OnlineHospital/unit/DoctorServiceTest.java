package DigRz.OnlineHospital.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import DigRz.OnlineHospital.dto.DoctorReg;
import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Doctor;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    AppointmentRepository appointmentRepository;

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

    @Test
    public void testGetDoctorInfo() {
        Long doctorId = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String specialty = "Cardiology";
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setSpecialty(specialty);
        when(doctorRepository.findById(doctorId)).thenReturn(java.util.Optional.of(doctor));

        String doctorInfo = doctorService.getDoctorInfo(doctorId);
        String expectedDoctorInfo = " " + firstName + " " + lastName + " with specialty " + specialty + " and ID " + doctorId;

        assertEquals(expectedDoctorInfo, doctorInfo);
    }

    @Test
    public void testDeleteDoctorAndHisAppointments() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        User user = new User();
        user.setId(1L);
        doctor.setUser(user);
        List<Appointment> appointmentList = new ArrayList<>();
        when(appointmentRepository.findByDoctorId(doctorId)).thenReturn(appointmentList);
        when(doctorRepository.findById(doctorId)).thenReturn(java.util.Optional.of(doctor));

        doctorService.deleteDoctorAndHisAppointments(doctorId);

        verify(appointmentRepository).deleteAll(appointmentList);
        verify(doctorRepository).deleteById(doctorId);
        verify(userRepository).delete(user);
    }


}
