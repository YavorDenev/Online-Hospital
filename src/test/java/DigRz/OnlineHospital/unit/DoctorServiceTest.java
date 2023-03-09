package DigRz.OnlineHospital.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import DigRz.OnlineHospital.constants.Role;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Mock
    Appointment appointment1;
    @Mock
    Appointment appointment2;

    @Test
    public void testSaveDoctor() {
        DoctorReg doctorReg = new DoctorReg();
        doctorReg.setFirstName("Ivo");
        doctorReg.setLastName("Velev");
        doctorReg.setSpecialty("Cardiology");
        doctorReg.setUsername("ivo123");

        User user = new User();
        user.setUsername("ivo123");
        when(userRepository.getUserByUsername("ivo123")).thenReturn(user);

        doctorService.saveDoctor(doctorReg);

        verify(doctorRepository).save(any());
    }

    @Test
    public void testGetDoctorInfo() {
        Long doctorId = 1L;
        String firstName = "Ivo";
        String lastName = "Velev";
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

    @Test
    public void testGetDoctorById() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

        Doctor result = doctorService.getDoctorById(doctorId);

        assertNotNull(result);
        assertEquals(doctorId, result.getId());
        verify(doctorRepository, times(1)).findById(doctorId);
    }

    @Test
    void testGetDoctorByIdWithAuth() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFirstName("Ivo");
        doctor.setLastName("Velev");
        doctor.setSpecialty("Cardiology");

        Authentication auth = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setRole(Role.ROLE_DOCTOR);

        when(auth.getName()).thenReturn("user1");
        when(userRepository.getUserByUsername("user1")).thenReturn(user);
        when(doctorRepository.findByUser(user)).thenReturn(doctor);

        Doctor result = doctorService.getDoctorById(0L);

        assertEquals(doctor, result);
    }

    @Test
    void testGetSortedAppointmentsByFirstCriteria() {
        Long doctorId = 1L;
        int sortCriteria = 1;
        int sortMethod = 1;
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        List<Appointment> appointments = new ArrayList<>();
        when(appointment1.getPatientNames()).thenReturn("Ivanov Ivo");
        appointments.add(appointment1);
        when(appointment2.getPatientNames()).thenReturn("Petrov Asen");
        appointments.add(appointment2);

        when(doctorRepository.findById(doctorId)).thenReturn(java.util.Optional.of(doctor));
        when(appointmentRepository.findByDoctorOrderByPatientId(doctor)).thenReturn(appointments);

        List<Appointment> sortedAppointments = doctorService.getSortedAppointments(doctorId, sortCriteria, sortMethod);

        assertEquals(sortedAppointments.get(0), appointment1);
        assertEquals(sortedAppointments.get(1), appointment2);
        verify(doctorRepository, times(1)).findById(doctorId);
        verify(appointmentRepository, times(1)).findByDoctorOrderByPatientId(doctor);
    }

    @Test
    void testGetSortedAppointmentsBySecondCriteria() {
        Long doctorId = 1L;
        int sortCriteria = 2;
        int sortMethod = 1;
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        List<Appointment> appointments = new ArrayList<>();
        when(appointment1.getTimeComparingKey()).thenReturn("2023-03-27 10:30");
        appointments.add(appointment1);
        when(appointment2.getTimeComparingKey()).thenReturn("2023-03-20 15:30");
        appointments.add(appointment2);

        when(doctorRepository.findById(doctorId)).thenReturn(java.util.Optional.of(doctor));
        when(appointmentRepository.findByDoctorOrderByPatientId(doctor)).thenReturn(appointments);

        List<Appointment> sortedAppointments = doctorService.getSortedAppointments(doctorId, sortCriteria, sortMethod);

        assertEquals(sortedAppointments.get(0), appointment2);
        assertEquals(sortedAppointments.get(1), appointment1);
        verify(doctorRepository, times(1)).findById(doctorId);
        verify(appointmentRepository, times(1)).findByDoctorOrderByPatientId(doctor);
    }

    @Test
    void testGetSortedAppointmentsByThirdCriteria() {
        Long doctorId = 1L;
        int sortCriteria = 3;
        int sortMethod = 2;
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);

        when(doctorRepository.findById(doctorId)).thenReturn(java.util.Optional.of(doctor));
        when(appointmentRepository.findByDoctorOrderByPatientId(doctor)).thenReturn(appointments);

        List<Appointment> sortedAppointments = doctorService.getSortedAppointments(doctorId, sortCriteria, sortMethod);

        assertEquals(sortedAppointments.get(0), appointment2);
        assertEquals(sortedAppointments.get(1), appointment1);
        verify(doctorRepository, times(1)).findById(doctorId);
        verify(appointmentRepository, times(1)).findByDoctorOrderByPatientId(doctor);
    }

}
