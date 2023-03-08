package DigRz.OnlineHospital.unit;
import DigRz.OnlineHospital.entities.Appointment;
import DigRz.OnlineHospital.entities.Doctor;
import DigRz.OnlineHospital.entities.Patient;
import DigRz.OnlineHospital.repositories.AppointmentRepository;
import DigRz.OnlineHospital.repositories.DoctorRepository;
import DigRz.OnlineHospital.services.GroupingService;
import DigRz.OnlineHospital.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupingServiceTests {
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        //this.initialSetup();
    }
    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    DoctorRepository doctorRepository;

    @Mock
    Utils utils;
    @Mock
    Doctor doctor1;
    @Mock
    Doctor doctor2;
    @Mock
    Patient patient1;
    @Mock
    Patient patient2;
    @Mock
    Patient patient3;
    @InjectMocks
    Appointment appointment1;
    @InjectMocks
    Appointment appointment2;
    @InjectMocks
    Appointment appointment3;
    @InjectMocks
    Appointment appointment4;
    @InjectMocks
    GroupingService groupingService;

    @Test
    public void testFindPatientsByDate() {
        // Arrange
        String today = "2023-03-08";
        List<Appointment> appointments = new ArrayList<>();
        appointment1.setMyDate(today);
        appointment2.setMyDate(today);
        appointment3.setMyDate(today);
        appointment4.setMyDate("2023-03-07");
        appointment1.setPatient(patient1);
        appointment2.setPatient(patient2);
        appointment3.setPatient(patient1);
        appointment4.setPatient(patient1);
        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);
        appointments.add(appointment4);
        when(appointmentRepository.findAll()).thenReturn(appointments);

        Set<Patient> patients = groupingService.findPatientsByDate(today.toString());

        assertEquals(2, patients.size());
    }

    @Test
    public void testFindPatientsCountByDate() {
        LocalDate day1 = LocalDate.parse("2023-03-08") ;
        LocalDate day2 = LocalDate.parse("2023-03-07");
        List<Appointment> appointments = new ArrayList<>();
        appointment1.setMyDate(day1.toString());
        appointment2.setMyDate(day1.toString());
        appointment3.setMyDate(day1.toString());
        appointment4.setMyDate(day2.toString());
        appointment1.setPatient(patient1);
        appointment2.setPatient(patient2);
        appointment3.setPatient(patient1);
        appointment4.setPatient(patient1);
        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);
        appointments.add(appointment4);
        when(appointmentRepository.findAll()).thenReturn(appointments);
        when(appointmentRepository.findAll()).thenReturn(appointments);
        List<LocalDate> days = new ArrayList<>();
        days.add(day1);
        days.add(day2);
        when(utils.generateListOfDays()).thenReturn(days);

        //Set<Patient> patients = groupingService.findPatientsByDate(today.toString());
        // Arrange


        //List<Appointment> appointments = new ArrayList<>();
        when(appointmentRepository.findAll()).thenReturn(appointments);

        Map<String, Long> patientsCount = groupingService.findPatientsCountByDate();

        // Assert
        assertEquals(2, patientsCount.size());
        assertEquals(2L, (long) patientsCount.get(day1.toString()));
    }
}
